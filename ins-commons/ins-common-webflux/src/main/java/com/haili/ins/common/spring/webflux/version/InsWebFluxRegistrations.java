package com.haili.ins.common.spring.webflux.version;

import org.springframework.boot.autoconfigure.web.reactive.WebFluxRegistrations;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

/**
 * @Author: leon
 * @Date: 2019/4/11 19:35
 * @Version 1.0
 */
public class InsWebFluxRegistrations implements WebFluxRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new InsRequestMappingHandlerMapping();
    }
}
