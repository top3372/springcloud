package com.haili.ins.service.rabbitmq;

import com.haili.ins.common.utils.RandomUtil;
import com.haili.ins.config.message.MessageCorrelationData;
import com.haili.ins.config.message.RabbitmqMessage;
import com.haili.ins.config.properties.RabbitConfProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: LeonMa
 * @date: 2019/01/08 09:53
 */
@Service
public class SenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private RabbitConfProperties rabbitConfProperties;

    /**
     * 给hello队列发送消息
     */
    public void send() {
        for (int i =0; i< 10; i++) {
            String msg = "hello, 序号: " + i;
            System.out.println("Producer, " + msg);
            RabbitmqMessage<String> message = new RabbitmqMessage<>();
            String id = RandomUtil.generateSeqNo();
            message.setId(id);
            message.setSender(rabbitConfProperties.getSender());
            message.setSendDate(new Date());
            message.setExchange(rabbitConfProperties.getMessageExchange());
            message.setRoutingKey(rabbitConfProperties.getMessageRoutingKey());
            message.setTimestamp(System.currentTimeMillis());
            message.setBody(msg);
            MessageCorrelationData data = new MessageCorrelationData(id,message);
            data.setId(id);
            data.setExchange(rabbitConfProperties.getMessageExchange());
            //data.setRoutingKey(rabbitConfProperties.getMessageRoutingKey());

            rabbitTemplate.convertAndSend(data.getExchange(),null, message,data);


        }

    }
}
