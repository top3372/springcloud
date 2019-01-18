package com.haili.ins.service.rabbitmq;

import com.haili.ins.config.constant.RabbitmqConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ComsumerService {

     @RabbitListener(queues = "${mq.message.exchange.queue.name}")
     public void process(Message message, Channel channel) throws IOException {

         log.info("receive: " + new String(message.getBody()));
         // 采用手动应答模式, 手动确认应答更为安全稳定
         channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
         //拒绝
         //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
         //退回
         //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);

     }
}