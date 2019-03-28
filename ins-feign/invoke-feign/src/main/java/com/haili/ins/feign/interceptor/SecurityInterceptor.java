package com.haili.ins.feign.interceptor;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.common.utils.JWTUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: leon
 * @Date: 2019/3/21 15:32
 * @Version 1.0
 */
@Slf4j
public class SecurityInterceptor implements RequestInterceptor {

    private static final String IGNORE_URL = "/oauth";

    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            String url = request.getRequestURI();
            if(!url.contains(IGNORE_URL)) {

                String token = JWTUtils.getInstance().getToken("");
//                requestTemplate.header(HttpHeaders.AUTHORIZATION, value);
//                Enumeration<String> headerNames = request.getHeaderNames();
//                if (headerNames != null) {
//                    while (headerNames.hasMoreElements()) {
//                        String name = headerNames.nextElement();
//                        String value = request.getHeader(name);
//                        /**
//                         * 遍历请求头里面的属性字段，将logId和token添加到新的请求头中转发到下游服务
//                         * */
//                        if (HttpHeaders.AUTHORIZATION.equalsIgnoreCase(name)) {
//                            log.debug("添加自定义请求头key:" + name + ",value:" + value);
//                            requestTemplate.header(name, value);
//                        }
//                    }
//                } else {
//                    log.warn("SecurityInterceptor", "获取请求头失败！");
//                }
            }
        }

    }
}
