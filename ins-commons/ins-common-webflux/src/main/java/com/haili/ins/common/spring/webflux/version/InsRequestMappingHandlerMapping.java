package com.haili.ins.common.spring.webflux.version;

import com.haili.ins.common.annotation.version.ApiVersion;
import com.haili.ins.common.utils.StringPool;
import com.haili.ins.common.utils.StringUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: leon
 * @Date: 2019/4/11 17:38
 * @Version 1.0
 */
public class InsRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Nullable
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappinginfo = super.getMappingForMethod(method, handlerType);
        if (mappinginfo != null) {
            RequestMappingInfo apiVersionMappingInfo = getApiVersionMappingInfo(method, handlerType);
            return apiVersionMappingInfo == null ? mappinginfo : apiVersionMappingInfo.combine(mappinginfo);
        }
        return null;
    }

    @Nullable
    private RequestMappingInfo getApiVersionMappingInfo(Method method, Class<?> handlerType) {
        // url 上的版本，优先获取方法上的版本
        ApiVersion apiVersion = AnnotatedElementUtils.findMergedAnnotation(method, ApiVersion.class);
        // 再次尝试类上的版本
        if (apiVersion == null || StringUtils.isBlank(apiVersion.value())) {
            apiVersion = AnnotatedElementUtils.findMergedAnnotation(handlerType, ApiVersion.class);
        }
        boolean nonApiVersionUrl = apiVersion == null || StringUtils.isBlank(apiVersion.value());

        RequestMappingInfo.Builder mappingInfoBuilder = null;
        if (nonApiVersionUrl) {
            mappingInfoBuilder = RequestMappingInfo.paths(StringPool.EMPTY);
        } else {
            mappingInfoBuilder = RequestMappingInfo.paths(apiVersion.value());
        }
        return mappingInfoBuilder.build();
    }

    @Override
    protected void handlerMethodsInitialized(Map<RequestMappingInfo, HandlerMethod> handlerMethods) {
        // 打印路由信息 spring boot 2.1 去掉了这个 日志的打印
        if (logger.isInfoEnabled()) {
            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
                RequestMappingInfo mapping = entry.getKey();
                HandlerMethod handlerMethod = entry.getValue();
                logger.info("Mapped \"" + mapping + "\" onto " + handlerMethod);
            }
        }
        super.handlerMethodsInitialized(handlerMethods);
    }
}