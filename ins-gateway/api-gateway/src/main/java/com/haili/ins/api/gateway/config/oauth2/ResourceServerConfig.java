package com.haili.ins.api.gateway.config.oauth2;

import com.haili.ins.api.gateway.properties.PermitAllUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;


/**
 * @author: LeonMa
 * @date: 2019/01/03 16:02
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    @Autowired
    private ResourceServerProperties sso;

    @Bean
    public ResourceServerTokenServices customUserInfoTokenServices() {
        return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())
                .and()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(permitAllUrlProperties.getPermitallPatterns()).permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(customUserInfoTokenServices())
                .authenticationEntryPoint(new AuthExceptionEntryPoint());;
    }
}
