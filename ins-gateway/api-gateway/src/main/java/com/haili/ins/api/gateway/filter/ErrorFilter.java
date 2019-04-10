package com.haili.ins.api.gateway.filter;

import com.haili.ins.common.dto.ResultInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import com.haili.ins.common.spring.webmvc.utils.WebUtils;


import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;


/**
 * @author Leon
 */
@Slf4j
public class ErrorFilter extends ZuulFilter {

    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";


    public static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_ERROR_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {

            Object e = ctx.get("throwable");

            if (e instanceof ZuulException) {
                ZuulException zuulException = (ZuulException) e;
                zuulException.printStackTrace();
                // Remove error code to prevent further error handling in follow up filters
                // 删除该异常信息,不然在下一个过滤器中还会被执行处理
                ctx.remove("throwable");
                // 根据具体的业务逻辑来处理
//                ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

                WebUtils.renderJson(ctx.getResponse(), new ResultInfo(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        zuulException.getMessage(), zuulException.getCause().getMessage()));

            }else{
                Exception exception = (Exception) e;
                ((Exception) e).printStackTrace();
                WebUtils.renderJson(ctx.getResponse(), new ResultInfo(String.valueOf(HttpStatus.NOT_FOUND.value()),
                        exception.getMessage(),exception.getCause().getMessage()));
            }
        } catch (Exception ex) {
            String error = "Error during filtering[ErrorFilter]";
            log.error("Exception filtering in custom error filter", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
            WebUtils.renderJson(ctx.getResponse(), new ResultInfo(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), error,error));
        }
        return null;
    }

}