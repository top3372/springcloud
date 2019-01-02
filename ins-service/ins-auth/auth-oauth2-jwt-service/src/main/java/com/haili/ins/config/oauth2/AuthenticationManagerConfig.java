package com.haili.ins.config.oauth2;

import com.haili.ins.config.oauth2.provider.AuthCodeTypeAuthenticationProvider;
import com.haili.ins.config.oauth2.provider.PasswordTypeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

/**
 * 将自定义的 用户名/密码 比对
 */
@Configuration
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    PasswordTypeAuthenticationProvider passwordTypeAuthenticationProvider;

    @Autowired
    AuthCodeTypeAuthenticationProvider authCodeTypeAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(passwordTypeAuthenticationProvider);
        auth.authenticationProvider(authCodeTypeAuthenticationProvider);
    }

}