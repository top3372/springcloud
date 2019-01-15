package com.haili.ins.config.oauth2.custom.filter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author: LeonMa
 * @date: 2019/01/15 10:20
 *
 *
 * 自定义登陆filter，新增登陆方式：验证码、二维码扫码、账号密码；
 * 手机短信验证码登陆：
 *    post: /login?type=phone&phone=13000000000&verifyCode=1000
 * 图形验证码登录
 *    post: /login?type=image@username=username&
 * 二维码登陆：
 *      post: /login?type=qr&qrCode=token
 * 账号密码登陆：
 *       post: /login?username=username&password=password
 * 此filter 为生成自定义的 IntegrationAuthenticationFilter
 *
 */
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
