package com.haili.ins.common.cloud.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Leon
 */
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
