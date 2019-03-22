package com.haili.ins.feign.config;

import com.haili.ins.feign.Strategy.FeignHystrixConcurrencyStrategy;
import com.haili.ins.feign.filter.Oauth2Filter;
import com.haili.ins.feign.interceptor.Oauth2Interceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: leon
 * @Date: 2019/3/21 15:30
 * @Version 1.0
 */
@Configuration
public class FeignConfig {

    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }

    @Bean
    public Oauth2Interceptor catFeignInterceptor() {
        return new Oauth2Interceptor();
    }

    @Bean
    public FilterRegistrationBean Oauth2Filter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        Oauth2Filter filter = new Oauth2Filter();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("oauth2-filter");
        registration.setOrder(1);
        return registration;
    }
}
