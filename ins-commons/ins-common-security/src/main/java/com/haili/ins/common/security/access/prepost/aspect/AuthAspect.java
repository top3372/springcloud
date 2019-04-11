package com.haili.ins.common.security.access.prepost.aspect;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.security.access.prepost.annotations.PreAuth;
import com.haili.ins.common.security.access.prepost.exception.SecureException;
import com.haili.ins.common.security.access.prepost.utils.ExpressionUtils;
import com.haili.ins.common.utils.ClassUtil;
import com.haili.ins.common.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.MethodParameter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @Author: leon
 * @Date: 2019/4/11 14:19
 * @Version 1.0
 */
@Aspect
public class AuthAspect  implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 切 方法 和 类上的 @PreAuth 注解
     *
     * @param point 切点
     * @return Object
     * @throws Throwable 没有权限的异常
     */
    @Around(
            "@annotation(com.haili.ins.common.security.access.prepost.annotations.PreAuth) || " +
                    "@within(com.haili.ins.common.security.access.prepost.annotations.PreAuth)"
    )
    public Object preAuth(ProceedingJoinPoint point) throws Throwable {
        if (handleAuth(point)) {
            return point.proceed();
        }
        throw new SecureException(ResponseCodeEnum.NO_AUTH_CODE);
    }

    /**
     * 切 方法 和 类上的 @PostAuth 注解
     *
     * @param point 切点
     * @return Object
     * @throws Throwable 没有权限的异常
     */
    @Around(
            "@annotation(com.haili.ins.common.security.access.prepost.annotations.PostAuth) || " +
                    "@within(com.haili.ins.common.security.access.prepost.annotations.PostAuth)"
    )
    public Object postAuth(ProceedingJoinPoint point) throws Throwable {
        if (handleAuth(point)) {
            return point.proceed();
        }
        throw new SecureException(ResponseCodeEnum.NO_AUTH_CODE);
    }

    /**
     * 处理权限
     *
     * @param point 切点
     */
    private boolean handleAuth(ProceedingJoinPoint point) {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        // 读取权限注解，优先方法上，没有则读取类
        PreAuth preAuth = ClassUtil.getAnnotation(method, PreAuth.class);
        // 判断表达式
        String condition = preAuth.value();
        if (StringUtils.isNotBlank(condition)) {
            Expression expression = SPEL_PARSER.parseExpression(condition);
            // 方法参数值
            Object[] args = point.getArgs();
            StandardEvaluationContext context = getEvaluationContext(method, args);
            return ExpressionUtils.evaluateAsBoolean(expression, context);
        }
        return false;
    }

    /**
     * 获取方法上的参数
     *
     * @param method 方法
     * @param args   变量
     * @return {SimpleEvaluationContext}
     */
    private StandardEvaluationContext getEvaluationContext(Method method, Object[] args) {
        // 初始化Sp el表达式上下文，并设置 AuthFun
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 设置表达式支持spring bean
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        for (int i = 0; i < args.length; i++) {
            // 读取方法参数
            MethodParameter methodParam = ClassUtil.getMethodParameter(method, i);
            // 设置方法 参数名和值 为sp el变量
            context.setVariable(methodParam.getParameterName(), args[i]);
        }
        return context;
    }

}
