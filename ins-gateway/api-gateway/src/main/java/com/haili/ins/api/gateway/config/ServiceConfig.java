package com.haili.ins.api.gateway.config;

import com.haili.ins.api.gateway.properties.PermitAllUrlProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author keets
 * @date 2017/9/29
 */
@Configuration
public class ServiceConfig {


    @Value("${server.port}")
    private int securePort;


    @Bean("permitAllUrlProperties")
    @ConfigurationProperties(prefix = "auth")
    public PermitAllUrlProperties getPermitAllUrlProperties() {
        return new PermitAllUrlProperties();
    }

}