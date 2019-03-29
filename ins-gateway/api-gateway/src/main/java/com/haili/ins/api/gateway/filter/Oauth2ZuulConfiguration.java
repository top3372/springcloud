package com.haili.ins.api.gateway.filter;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Oauth2ZuulConfiguration {

    @Bean
    public Oauth2ZuulFilter oauth2ZuulFilter() {
        return new Oauth2ZuulFilter();
    }

    @Bean
    public JwtZuulFilter jwtZuulFilter() {
        return new JwtZuulFilter();
    }
}
