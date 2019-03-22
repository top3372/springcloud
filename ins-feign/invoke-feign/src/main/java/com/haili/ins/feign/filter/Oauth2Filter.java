package com.haili.ins.feign.filter;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.enums.RequestSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: leon
 * @Date: 2019/3/21 15:07
 * @Version 1.0
 */
@Slf4j
public class Oauth2Filter implements Filter {
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" 开始Oauth2Filter调用 : " );
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String url = request.getRequestURL().toString();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String requestSource = request.getHeader(HttpHeaderConstant.REQUEST_SOURCE);
        log.info(requestSource + token);

        if(RequestSourceEnum.isRequestSourceEnum(requestSource)){

        }


        filterChain.doFilter(servletRequest, servletResponse);

    }
}
