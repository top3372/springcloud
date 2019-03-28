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

    private static final Pattern[] IGNORE_PATTERNS = new Pattern[]{Pattern.compile("/oauth/*"),Pattern.compile("/auth/*")};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" 开始SecurityFilter调用 : " );
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletResponse.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        log.info("请求uri: " + url );
        boolean flag = false;
        for(Pattern pattern : IGNORE_PATTERNS){

            if(pattern.matcher(url).find()){
                flag = true;
                break;
            }
        }



//        if(!flag){
//            String token = request.getHeader(HttpHeaderConstant.SECURITY_TOKEN);
//            if (StringUtils.isNotEmpty(token)) {
//                log.info(token);
//                JWTUtils.JWTResult result = JWTUtils.getInstance().checkToken(token);
//                if (result.isStatus()) {
//                    filterChain.doFilter(servletRequest, servletResponse);
//                } else {
//                    servletResponse.getWriter().print("验证失败");
//                    return;
//                }
//            } else {
//                servletResponse.getWriter().print("token不存在");
//                return;
//            }
//        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

}
