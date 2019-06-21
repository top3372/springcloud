package com.haili.ins.boms.gateway.config;

import com.haili.ins.boms.gateway.filter.GatewayGlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public GatewayGlobalFilter gatewayGlobalFilter() {
        return new GatewayGlobalFilter();
    }
}
