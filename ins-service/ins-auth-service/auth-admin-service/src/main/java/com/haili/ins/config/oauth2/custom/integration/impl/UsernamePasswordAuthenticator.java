package com.haili.ins.config.oauth2.custom.integration.impl;

import com.haili.ins.config.oauth2.custom.integration.AbstractPreparableIntegrationAuthenticator;

import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthentication;
import com.haili.ins.dto.Oauth2User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 默认登录处理
 *
 * @author LIQIU
 * @date 2018-3-31
 **/
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    private final static String PWD_AUTH_TYPE = "pwd";

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Oauth2User authenticate(IntegrationAuthentication integrationAuthentication) {
//        Oauth2User oauth2User = sysUserClient
//        .findUserByUsername(integrationAuthentication.getUsername());
//        return oauth2User;
        //获取密码，实际值是验证码
        String password = integrationAuthentication.getAuthParameter("password");
        //获取用户名，实际值是手机号
        String username = integrationAuthentication.getUsername();

        Oauth2User oauth2User = new Oauth2User();
        oauth2User.setId("11111111");
        oauth2User.setUsername("admin");
        oauth2User.setPassword(passwordEncoder.encode("123456"));
        oauth2User.setStatus("1");
        oauth2User.setName("找找");

        return oauth2User;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }
}
