package com.haili.ins.common.spring.webflux.filter;

import com.haili.ins.common.spring.webflux.utils.ReactiveRequestContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@Order(-1)
@Slf4j
public class FluxFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        ServerHttpRequest request =  serverWebExchange.getRequest();
        //ServerHttpResponse response =  exchange.getResponse();'
        StringBuilder sb = new StringBuilder();
        sb.append("###FluxFilter[Thread-")
                .append((Thread.currentThread()).getId())
                .append("]");
        log.info(sb.toString());
        return webFilterChain.filter(serverWebExchange);
    }
}
