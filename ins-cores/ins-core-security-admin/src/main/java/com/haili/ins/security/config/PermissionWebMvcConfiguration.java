package com.haili.ins.security.config;

import com.haili.ins.common.spring.webmvc.filter.WebServletFilter;
import com.haili.ins.security.filter.PermissionMvcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: leon
 * @Date: 2019/4/12 16:37
 * @Version 1.0
 */
@Configuration
public class PermissionWebMvcConfiguration {

    @Bean
    public FilterRegistrationBean permissionMvcFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        PermissionMvcFilter filter = new PermissionMvcFilter();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("permission-filter");
        return registration;
    }

}
