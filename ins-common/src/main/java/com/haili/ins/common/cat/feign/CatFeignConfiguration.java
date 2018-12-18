package com.haili.ins.common.cat.feign;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "spring.cat.feign.enabled", havingValue ="true")
public class CatFeignConfiguration {

    @Bean
    public CatFeignInterceptor catFeignInterceptor() {
        return new CatFeignInterceptor();
    }
}
