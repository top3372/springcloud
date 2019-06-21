package com.haili.ins.api.gateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leon
 */
@Configuration
public class ZuulFilterConfiguration {

    @Bean
    public Oauth2ZuulFilter oauth2ZuulFilter() {
        return new Oauth2ZuulFilter();
    }

    @Bean
    public JwtZuulFilter jwtZuulFilter() {
        return new JwtZuulFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }


}
