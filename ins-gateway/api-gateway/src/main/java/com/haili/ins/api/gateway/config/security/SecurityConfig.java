package com.haili.ins.api.gateway.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.servlet.Filter;

//@Configuration
//@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    OAuth2ClientContext oauth2ClientContext;

//    @Autowired
//    private ResourceServerProperties resourceServerProperties;

//    @Autowired
//    private Oauth2RequestSecurityProperties requestSecurityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

    }
//
//    private Filter ssoFilter() {
//        OAuth2ClientAuthenticationProcessingFilter ssoFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
//        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(, oauth2ClientContext);
//        ssoFilter.setRestTemplate(oAuth2RestTemplate);
//        UserInfoTokenServices tokenServices = new UserInfoTokenServices( resourceServerProperties.getUserInfoUri(),
//                resourceServerProperties.getClientId());
//        tokenServices.setRestTemplate(oAuth2RestTemplate);
//        ssoFilter.setTokenServices(tokenServices);
//        return ssoFilter;
//    }

}