package com.haili.ins.feign.interceptor;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.common.utils.JWTUtils;
import com.haili.ins.common.utils.RandomUtil;
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



    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            String routeName = request.getRequestURI();

            String jwtToken = JWTUtils.createToken(routeName + "&" + RandomUtil.generateSeqNo());
            requestTemplate.header(HttpHeaderConstant.SECURITY_TOKEN, jwtToken);


        }

    }
}
