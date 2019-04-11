package com.haili.ins.common.cloud.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

@Slf4j
public class FeignInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {

        StringBuilder sb = new StringBuilder();
        sb.append("###Feign[Thread-")
                .append((Thread.currentThread()).getId())
                .append("]");
        log.info(sb.toString());
//        HttpHeaders headers = BladeHttpHeadersContextHolder.get();
//        if (headers != null && !headers.isEmpty()) {
//            headers.forEach((key, values) -> {
//                values.forEach(value -> requestTemplate.header(key, value));
//            });
//        }
    }
}
