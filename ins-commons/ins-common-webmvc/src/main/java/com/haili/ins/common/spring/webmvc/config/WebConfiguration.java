package com.haili.ins.common.spring.webmvc.config;

import com.haili.ins.common.spring.webmvc.filter.SecurityFilter;
import com.haili.ins.common.spring.webmvc.filter.WebServletFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;


@Configuration
public class WebConfiguration {


    @Bean
    public FilterRegistrationBean webFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        WebServletFilter filter = new WebServletFilter();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("web-filter");
        registration.setOrder(2);
        return registration;
    }

    /** 处理字符的 filter. */
    @Bean
    public FilterRegistrationBean encodingFilterRegistrationBean() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(StandardCharsets.UTF_8.displayName());
        encodingFilter.setForceEncoding(true);

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(encodingFilter);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean securityFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        SecurityFilter filter = new SecurityFilter();

        registration.setFilter(filter);
        registration.addUrlPatterns("/api/*");
        registration.setName("mic-service-security-filter");
        registration.setOrder(1);
        return registration;
    }



}
