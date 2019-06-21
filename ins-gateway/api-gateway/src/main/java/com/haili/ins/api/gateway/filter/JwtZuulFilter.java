package com.haili.ins.api.gateway.filter;


import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.common.utils.JWTUtils;
import com.haili.ins.common.utils.RandomUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.RIBBON_ROUTING_FILTER_ORDER;


public class JwtZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(JwtZuulFilter.class);


    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return RIBBON_ROUTING_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info(" JwtZuul路由调用开始 : ");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String routeName = request.getRequestURI();
        logger.info(" routeName调用 : " + routeName);
        String jwtToken =
                JWTUtils.createToken(routeName + "&" + RandomUtil.generateSeqNo());
        logger.info(" routeName调用jwtToken: " + jwtToken);
//        ResponseCodeEnum responseCodeEnum = JWTUtils.verifyToken(jwtToken);
        requestContext.addZuulRequestHeader(HttpHeaderConstant.SECURITY_TOKEN, jwtToken);


        logger.info(" JwtZuulZuul路由调用结束: ");
        return null;

    }


}
