package com.haili.ins.common.spring.webmvc.config;

import com.haili.ins.common.spring.webmvc.filter.SecurityFilter;
import com.haili.ins.common.spring.webmvc.filter.WebServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

import java.nio.charset.StandardCharsets;

/**
 * @author Leon
 */
@Configuration
public class WebMvcConfiguration {


    @Bean
    public FilterRegistrationBean webServletFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        WebServletFilter filter = new WebServletFilter();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("web-filter");
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
        return registration;
    }


    /**
     * 跨域, 开发环境使用 vue-cli 代理，正式用nginx
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean =  new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }



}
