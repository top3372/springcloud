package com.haili.ins.core.cat.feign;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "spring.haili.cat.feign.enabled", havingValue ="true")
public class CatFeignConfiguration {

    @Bean
    public CatFeignInterceptor catFeignInterceptor() {
        return new CatFeignInterceptor();
    }
}
