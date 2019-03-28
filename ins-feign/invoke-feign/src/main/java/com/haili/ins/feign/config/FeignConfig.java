package com.haili.ins.feign.config;

import com.haili.ins.feign.Strategy.FeignHystrixConcurrencyStrategy;
import com.haili.ins.feign.filter.SecurityFilter;
import com.haili.ins.feign.interceptor.SecurityInterceptor;
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
    public SecurityInterceptor catFeignInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public FilterRegistrationBean Oauth2Filter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        SecurityFilter filter = new SecurityFilter();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("mic-service-security-filter");
        registration.setOrder(1);
        return registration;
    }
}
