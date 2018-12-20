package com.haili.ins.boms.gateway.filter;


import com.haili.ins.common.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.Charset;


@Slf4j
public class GatewayGlobalFilter  implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //打印输出response
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String result = new String(content, Charset.forName("UTF-8"));
                        log.info(">======返回值: " + result);
                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };

        ServerHttpRequest originalRequest = exchange.getRequest();
        String method = originalRequest.getMethodValue();
        log.info("<======请求值方法: {}" , method);
        URI originalRequestUrl = originalRequest.getURI();
        //只记录http的请求
        String scheme = originalRequestUrl.getScheme();
        if (("http".equals(scheme) || "https".equals(scheme))) {

            HttpHeaders headers = originalRequest.getHeaders();
            headers.forEach((name, values) -> {
                values.forEach(value -> {
                    log.info("<======header: {}: {}" , name,value);
                });
            });
        }
        //暂时没有好办法取到 body内容.... http://blog.51cto.com/thinklili/2329184 可以取到,但路由参数有问题,webflux找不到handler
//        Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
//        log.info("<======请求值: {}" , JSONUtil.toJson(requestBody));



        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }



    @Override
    public int getOrder() {
        return -100;
    }

}
