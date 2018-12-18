package com.haili.ins.common.spring.web.config;

import com.haili.ins.common.spring.web.filter.WebServletFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;


@Configuration
@ConditionalOnProperty(value = "spring.web.filter.enabled", havingValue ="true")
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
    public FilterRegistrationBean filterRegistrationBean() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(StandardCharsets.UTF_8.displayName());
        encodingFilter.setForceEncoding(true);

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(encodingFilter);
        return registrationBean;
    }



}
