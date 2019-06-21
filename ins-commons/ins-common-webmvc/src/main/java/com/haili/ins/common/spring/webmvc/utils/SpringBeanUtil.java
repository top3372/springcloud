package com.haili.ins.common.spring.webmvc.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * Created by marver on 2017/5/29 0:45.
 */
public class SpringBeanUtil {
    private static ServletContext sc = null;

    public static void setServletContext(ServletContext sc) {
        SpringBeanUtil.sc = sc;
    }

    public static Object getBean(String serviceName) {
        if (SpringBeanUtil.sc == null) {
            throw new RuntimeException("请在系统启动时加载InitializeServlet");
        }
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(SpringBeanUtil.sc);
        Object service = wac.getBean(serviceName);
        return service;
    }

    public static String getContextName() {
        return null;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (SpringBeanUtil.sc == null) {
            throw new RuntimeException("请在系统启动时加载InitializeServlet");
        }
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(SpringBeanUtil.sc);
        return wac.getBean(clazz);
    }
}
