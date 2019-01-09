package com.haili.ins.config.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.annotation.Resource;

/**
 * @author: LeonMa
 * @date: 2019/01/08 15:02
 */
@Configuration
public class RabbitListenerConfig implements RabbitListenerConfigurer {

    @Resource
    private DefaultMessageHandlerMethodFactory messageHandlerMethodFactory;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}
