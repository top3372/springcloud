package com.haili.ins.common.cat.zuul;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "spring.hailicat.zuul.enabled", havingValue ="true")
public class CatZuulConfiguration {

    @Bean
    public CatZuulFilter catZuulFilter() {
        return new CatZuulFilter();
    }
}
