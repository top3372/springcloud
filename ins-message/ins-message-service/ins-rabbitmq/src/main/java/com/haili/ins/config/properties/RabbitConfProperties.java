package com.haili.ins.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: LeonMa
 * @date: 2019/01/09 11:52
 */
@Component
@Data
public class RabbitConfProperties {

    @Value("${mq.message.exchange.name}")
    private String messageExchange;

    @Value("${mq.message.exchange.queue.name}")
    private String messageQueue;

    @Value("${mq.message.exchange.routing-key.name}")
    private String messageRoutingKey;

    @Value("${mq.message.dead.exchange.name}")
    private String deadExchange;

    @Value("${mq.message.dead.exchange.queue.name}")
    private String deadQueue;

    @Value("${mq.message.dead.exchange.routing-key.name}")
    private String deadRoutingKey;

    @Value("${mq.retry.count:5}")
    private int mqRetryCount;

    @Value("${spring.application.name}")
    private String sender;


}
