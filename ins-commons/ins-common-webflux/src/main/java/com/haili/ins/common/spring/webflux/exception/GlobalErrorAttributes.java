package com.haili.ins.common.spring.webflux.exception;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);

        if (getError(request) instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);

            Map<String, Object> errorMap = new ConcurrentHashMap<>(10);
            errorMap.put("exception", ex.getClass().getSimpleName());
            errorMap.put("error", ex.getStatus().getReasonPhrase());

            map.put("result", "false");
            map.put("data", errorMap);
            map.put("code", ex.getStatus().value());
            map.put("msg", ex.getMessage());


            return map;
        }

        Map<String, Object> errorMap = new ConcurrentHashMap<>(10);
        errorMap.put("exception", "SystemException");
        errorMap.put("error", " System Error ");

        map.put("result", "false");
        map.put("data", errorMap);
        map.put("code", "500");
        map.put("msg", "System Error , Check logs!");

        return map;
    }
}