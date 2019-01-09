package com.haili.ins.config.listener;

import com.alibaba.fastjson.JSON;
import com.haili.ins.config.properties.RabbitConfProperties;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * @author: LeonMa
 * @date: 2019/01/08 16:41
 */
@Slf4j
@Component
public class DeadMessageListener {


    @RabbitListener(queues = "${mq.message.dead.exchange.queue.name}")
    @RabbitHandler
    public void process(Message message, Channel channel) throws IOException {
        log.info("处理死信队列消息队列接收数据，消息体：{}", JSON.toJSONString(message));

        System.out.println(message.getMessageProperties().getDeliveryTag());

        try {
            // 参数校验
            Assert.notNull(message, "Message 消息体不能为NULL");

            // TODO 处理消息

            // 确认消息已经消费成功
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("MQ消息处理异常，消息体:{}", message.getMessageProperties().getCorrelationId(), JSON.toJSONString(message), e);

            try {
                // TODO 保存消息到数据库

                // 确认消息已经消费成功
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception dbe) {
                log.error("保存异常MQ消息到数据库异常，放到死信队列，消息体：{}", JSON.toJSONString(message), dbe);
                // 确认消息将消息放到死信队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        }
    }
}
