package com.haili.ins.feign.interceptor;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.common.utils.JWTUtils;
import com.haili.ins.common.utils.RandomUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author: leon
 * @Date: 2019/3/21 15:32
 * @Version 1.0
 */
@Slf4j
public class SecurityInterceptor implements RequestInterceptor {



    @Override
    public void apply(RequestTemplate requestTemplate) {

        String routeName = requestTemplate.url();
        log.info("routeName: " + routeName);
        String jwtToken = JWTUtils.createToken(routeName + "&" + RandomUtil.generateSeqNo());
        log.info("jwtToken: " + jwtToken);
        requestTemplate.header(HttpHeaderConstant.SECURITY_TOKEN, jwtToken);




    }
}
