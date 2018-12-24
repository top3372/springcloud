package com.haili.ins.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {

        StringBuilder sb = new StringBuilder();
        sb.append("###Feign[Thread-")
                .append((Thread.currentThread()).getId())
                .append("]");
        log.info(sb.toString());
    }
}
