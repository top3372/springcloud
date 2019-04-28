package com.haili.ins.common.cloud.feign.version;

import com.haili.ins.common.annotation.version.ApiVersion;
import com.haili.ins.common.utils.StringPool;
import com.haili.ins.common.utils.StringUtils;
import feign.MethodMetadata;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: leon
 * @Date: 2019/4/11 17:23
 * @Version 1.0
 */
public class InsSpringMvcContract extends SpringMvcContract {

    public InsSpringMvcContract(List<AnnotatedParameterProcessor> annotatedParameterProcessors, ConversionService conversionService) {
        super(annotatedParameterProcessors, conversionService);
    }

    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {
        if (RequestMapping.class.isInstance(methodAnnotation) || methodAnnotation.annotationType().isAnnotationPresent(RequestMapping.class)) {
            Class<?> targetType = method.getDeclaringClass();
            // url 上的版本，优先获取方法上的版本
            ApiVersion apiVersion = AnnotatedElementUtils.findMergedAnnotation(method, ApiVersion.class);
            // 再次尝试类上的版本
            if (apiVersion == null || StringUtils.isBlank(apiVersion.value())) {
                apiVersion = AnnotatedElementUtils.findMergedAnnotation(targetType, ApiVersion.class);
            }
            if (apiVersion != null && StringUtils.isNotBlank(apiVersion.value())) {
                String apiVersionUrl = "/" + apiVersion.value();
                data.template().uri(apiVersionUrl);
            }

            // 注意：在父类之前 添加 url版本，在父类之后，处理 Media Types 版本
            super.processAnnotationOnMethod(data, methodAnnotation, method);
        }
    }

    /**
     * 参考：https://gist.github.com/rmfish/0ed59a9af6c05157be2a60c9acea2a10
     * @param annotations 注解
     * @param paramIndex 参数索引
     * @return 是否 http 注解
     */
    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata data, Annotation[] annotations, int paramIndex) {
        boolean httpAnnotation = super.processAnnotationsOnParameter(data, annotations, paramIndex);
        // 在 springMvc 中如果是 Get 请求且参数中是对象 没有声明为@RequestBody 则默认为 Param
        if (!httpAnnotation && StringPool.GET.equals(data.template().method().toUpperCase())) {
            for (Annotation parameterAnnotation : annotations) {
                if (!(parameterAnnotation instanceof RequestBody)) {
                    return false;
                }
            }
            data.queryMapIndex(paramIndex);
            return true;
        }
        return httpAnnotation;
    }

}
