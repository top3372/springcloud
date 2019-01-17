package com.haili.ins.config.oauth2;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;


/**
 * 将自定义的 用户名/密码 比对
 */
@Configuration
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

    }


}