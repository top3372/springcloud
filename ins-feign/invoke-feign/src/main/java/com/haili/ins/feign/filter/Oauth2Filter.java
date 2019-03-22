package com.haili.ins.feign.filter;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.enums.RequestSourceEnum;
import com.haili.ins.feign.EncryptFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.annotation.Resource;
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

    @Resource
    private EncryptFeign encryptFeign;
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" 开始Oauth2Filter调用 : " );
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String url = request.getRequestURL().toString();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String requestSource = request.getHeader(HttpHeaderConstant.REQUEST_SOURCE);
        log.info(requestSource + token);

        if(RequestSourceEnum.isRequestSourceEnum(requestSource)){
            String flag = encryptFeign.jwtCheck(token);
            if(Boolean.parseBoolean(flag)){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                servletResponse.getWriter().print("验证失败");
                return;
            }
        }


        filterChain.doFilter(servletRequest, servletResponse);

    }
}
