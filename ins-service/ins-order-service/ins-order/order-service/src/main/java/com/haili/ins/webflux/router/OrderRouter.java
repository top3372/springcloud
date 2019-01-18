package com.haili.ins.webflux.router;

import com.haili.ins.webflux.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author MaTang
 */
@Configuration
public class OrderRouter {

    @Bean
    public RouterFunction<ServerResponse> routeOrderList(OrderHandler orderHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/order/list")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        orderHandler::orderList);
    }
}
