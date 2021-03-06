package com.haili.ins.config;

import com.alibaba.fastjson.JSON;
import com.haili.ins.config.message.MessageCorrelationData;
import com.haili.ins.config.properties.RabbitConfProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: LeonMa
 * @date: 2019/01/08 09:40
 */
@Configuration
@ConditionalOnClass({RabbitConfProperties.class})
@Slf4j
public class RabbitMqConfig {

    @Autowired
    private RabbitConfProperties rabbitConfProperties;


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        //声明死信队列（Fanout类型的exchange）
        Queue deadQueue = new Queue(rabbitConfProperties.getDeadQueue());
        // 死信队列交换机
        FanoutExchange deadExchange = new FanoutExchange(rabbitConfProperties.getDeadExchange());
        rabbitAdmin.declareQueue(deadQueue);
        rabbitAdmin.declareExchange(deadExchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(deadQueue).to(deadExchange));


        FanoutExchange exchange = new FanoutExchange(rabbitConfProperties.getMessageExchange());
        //DirectExchange exchange = new DirectExchange(rabbitConfProperties.getMessageExchange());
        //TopicExchange exchange = new TopicExchange(rabbitConfProperties.getMessageExchange());

        //声明发送优惠券的消息队列（Direct类型的exchange）
        Queue couponQueue = queue(rabbitConfProperties.getMessageQueue());
        //Queue couponQueue = new Queue(rabbitConfProperties.getMessageQueue());
        rabbitAdmin.declareQueue(couponQueue);
        rabbitAdmin.declareExchange(exchange);
        //rabbitAdmin.declareBinding(BindingBuilder.bind(couponQueue).to(exchange).with(rabbitConfProperties.getMessageRoutingKey()));
        rabbitAdmin.declareBinding(BindingBuilder.bind(couponQueue).to(exchange));
        return rabbitAdmin;
    }


    /**
     * 为普通Queue 绑定死信队列
     *
     * @param name
     * @return
     */
    public Queue queue(String name) {
        Map<String, Object> args = new HashMap<>();
        // 设置死信队列
        args.put("x-dead-letter-exchange", rabbitConfProperties.getDeadExchange());
        args.put("x-dead-letter-routing-key", rabbitConfProperties.getDeadRoutingKey());
        // 设置消息的过期时间， 单位是毫秒
        args.put("x-message-ttl", 5000);

        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(name, durable, exclusive, autoDelete, args);
    }


    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //消息转换为json格式
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);

        // 消息返回, yml需要配置 publisher-returns: true
        //用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。
        //在脑裂的情况下会出现这种情况
        //
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);

            // TODO 保存消息到数据库
        });

        // 消息确认, yml需要配置 publisher-confirms: true
        //用于实现消息发送到RabbitMQ交换器后接收ack回调。
        //如果消息发送确认失败就进行重试。
        //
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack && correlationData instanceof CorrelationData) {
                log.info("消息发送到exchange失败,原因: {}", cause);
                MessageCorrelationData correlationDataExtends = (MessageCorrelationData) correlationData;
                //重试次数
                if (correlationDataExtends.getRetryCount() < rabbitConfProperties.getMqRetryCount()) {

                    log.info("MQ消息发送失败，消息重发，消息ID：{}，重发次数：{}，消息体:{}", correlationDataExtends.getId(),
                            correlationDataExtends.getRetryCount(), JSON.toJSONString(correlationDataExtends.getRabbitmqMessage()));
                    // 将重试次数加一
                    correlationDataExtends.setRetryCount(correlationDataExtends.getRetryCount() + 1);

                    // 重发发消息
                    this.convertAndSend(rabbitTemplate, correlationDataExtends.getExchange(), correlationDataExtends.getRoutingKey(),
                            correlationDataExtends.getRabbitmqMessage(), correlationDataExtends);
                } else {
                    //消息重试发送失败,将消息放到数据库等待补发
                    log.warn("MQ消息重发失败，消息入库，消息ID：{}，消息体:{}", correlationData.getId(),
                            JSON.toJSONString(correlationDataExtends.getRabbitmqMessage()));

                    // TODO 保存消息到数据库
                }

            } else {


                log.info("消息发送到exchange成功,id: {}", correlationData.toString());
            }
        });
        return rabbitTemplate;
    }

    /**
     * 发送消息
     *
     * @param exchange        交换机名称
     * @param routingKey      路由key
     * @param message         消息内容
     * @param correlationData 消息相关数据（消息ID）
     * @throws AmqpException
     */
    private void convertAndSend(RabbitTemplate rabbitTemplate, String exchange, String routingKey, final Object message, CorrelationData correlationData) throws AmqpException {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
        } catch (Exception e) {
            log.error("MQ消息发送异常，消息ID：{}，消息体:{}, exchangeName:{}, routingKey:{}",
                    correlationData.getId(), JSON.toJSONString(message), exchange, routingKey, e);

            // TODO 保存消息到数据库
        }
    }
}
/**
 * Fanout 广播交换机 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上。
 * 1.可以理解为路由表的模式
 * <p>
 * 2.这种模式不需要RouteKey
 * <p>
 * 3.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定。
 * <p>
 * 4.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃。
 * <p>
 * Direct 任何发送到Direct Exchange的消息都会被转发到RouteKey中指定的Queue。
 * <p>
 * 1.一般情况可以使用rabbitMQ自带的Exchange：”"(该Exchange的名字为空字符串，下文称其为default Exchange)。
 * <p>
 * 2.这种模式下不需要将Exchange进行任何绑定(binding)操作
 * <p>
 * 3.消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
 * <p>
 * 4.如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
 * <p>
 * Topic 任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上
 * <p>
 * 1.这种模式较为复杂，简单来说，就是每个队列都有其关心的主题，所有的消息都带有一个“标题”(RouteKey)，
 * Exchange会将消息转发到所有关注主题能与RouteKey模糊匹配的队列。
 * <p>
 * 2.这种模式需要RouteKey，也许要提前绑定Exchange与Queue。
 * <p>
 * 3.在进行绑定时，要提供一个该队列关心的主题，如“#.log.#”表示该队列关心所有涉及log的消息(一个RouteKey为”MQ.log.error”的消息会被转发到该队列)。
 * <p>
 * 4.“#”表示0个或若干个关键字，“*”表示一个关键字。如“log.*”能与“log.warn”匹配，无法与“log.warn.timeout”匹配；但是“log.#”能与上述两者匹配。
 * <p>
 * 5.同样，如果Exchange没有发现能够与RouteKey匹配的Queue，则会抛弃此消息。
 * ======================== 定制一些处理策略 =============================
 * 定制化amqp模版
 * <p>
 * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
 * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
 * 使用场景：
 * <p>
 * 如果消息没有到exchange,则confirm回调,ack=false
 * <p>
 * 如果消息到达exchange,则confirm回调,ack=true
 * <p>
 * exchange到queue成功,则不回调return
 * <p>
 * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
 */


/** ======================== 定制一些处理策略 =============================*/

/**
 * 定制化amqp模版
 *
 * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
 * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
 * 使用场景：
 *
 * 如果消息没有到exchange,则confirm回调,ack=false
 *
 * 如果消息到达exchange,则confirm回调,ack=true
 *
 * exchange到queue成功,则不回调return
 *
 * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
 */