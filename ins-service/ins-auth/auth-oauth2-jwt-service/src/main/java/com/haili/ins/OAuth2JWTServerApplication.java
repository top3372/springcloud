package com.haili.ins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class OAuth2JWTServerApplication  extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2JWTServerApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //将默认指向的确认页,改成自定义的页面
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }
}
