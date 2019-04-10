package com.haili.ins.webflux.handler;


import com.haili.ins.common.dto.ResultInfo;
import com.haili.ins.common.enums.ResponseCodeEnum;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author MaTang
 */
@Component
public class OrderHandler {

    public Mono<ServerResponse> orderList(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new ResultInfo(ResponseCodeEnum.SUCCESS)));
    }
}