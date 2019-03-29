package com.haili.ins.feign.filter;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.utils.JWTUtils;
import com.haili.ins.enums.RequestSourceEnum;
import com.haili.ins.feign.EncryptFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: leon
 * @Date: 2019/3/21 15:07
 * @Version 1.0
 */
@Slf4j
public class SecurityFilter implements Filter {

//    @Resource
//    private EncryptFeign encryptFeign;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" 开始SecurityFilter调用 : ");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletResponse.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        log.info("请求uri: " + url);

        String token = request.getHeader(HttpHeaderConstant.SECURITY_TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            log.info("接收到的token: " + token);
            ResponseCodeEnum responseCodeEnum = JWTUtils.verifyToken(token);
            if (ResponseCodeEnum.VERIFY_SUCCESS.getCode().equals(responseCodeEnum.getCode())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletResponse.getWriter().print("服务调用token验证失败");
            }
        } else {
            servletResponse.getWriter().print("服务调用token不存在");

        }


    }

}
