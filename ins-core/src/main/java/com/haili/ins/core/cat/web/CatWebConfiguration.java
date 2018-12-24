package com.haili.ins.core.cat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

@Configuration
@ConditionalOnProperty(value = "spring.haili.cat.web.enabled", havingValue ="true")
public class CatWebConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RestTemplate.class);

    @Bean
    public FilterRegistrationBean catFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        CatServletFilter filter = new CatServletFilter();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(2000);
        clientHttpRequestFactory.setReadTimeout(3000);
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        // 保存和传递调用链上下文
        restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override public boolean hasError(ClientHttpResponse response)
                    throws IOException {
                try {
                    return super.hasError(response);
                } catch (Exception e) {
                    return true;
                }
            }

            @Override public void handleError(ClientHttpResponse response)
                    throws IOException {
                try {
                    super.handleError(response);
                } catch (Exception e) {
                    log.error("Exception [" + e.getMessage() + "] occurred while trying to send the request", e);
                    throw e;
                }
            }
        });
        return restTemplate;
    }

}
