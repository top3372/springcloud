package com.haili.ins.config.oauth2;

import com.haili.ins.config.oauth2.custom.exception.CustomOauth2ExceptionTranslator;
import com.haili.ins.config.oauth2.custom.filter.IntegrationAuthenticationFilter;
import com.haili.ins.config.oauth2.custom.token.generate.CustomJwtAccessTokenConverter;
import com.haili.ins.service.oauth2.CustomClientDetailsService;
import com.haili.ins.service.security.custom.IntegrationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * 认证服务器配置
 *
 * @author lxg
 * <p>
 * 2017年2月17日上午10:50:04
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器，当你选择了资源所有者密码（password）授权类型的时候，
     * 请设置这个属性注入一个 AuthenticationManager 对象
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CustomClientDetailsService customClientDetailsService;

    @Autowired
    private IntegrationUserDetailsService integrationUserDetailsService;

    @Autowired
    private CustomOauth2ExceptionTranslator customOauth2ExceptionTranslator;

    @Autowired
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    @Autowired
    private WebResponseExceptionTranslator customWebResponseExceptionTranslator;


    /**
     * 配置令牌端点(Token Endpoint)的安全约束.
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);


    }

    /**
     * 配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsService);
    }

    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     * AuthorizationServerEndpointsConfigurer 这个配置对象(AuthorizationServerConfigurer 的一个回调配置项，见上的概述)
     * 有一个叫做 pathMapping() 的方法用来配置端点URL链接，它有两个参数：
     * 第一个参数：String 类型的，这个端点URL的默认链接。
     * 第二个参数：String 类型的，你要进行替代的URL链接。
     * 以上的参数都将以 "/" 字符为开始的字符串，框架的默认URL链接如下列表，可以作为这个 pathMapping() 方法的第一个参数：
     * /oauth/authorize：授权端点。
     * /oauth/token：令牌端点。
     * /oauth/confirm_access：用户确认授权提交端点。
     * /oauth/error：授权服务错误信息端点。
     * /oauth/check_token：用于资源服务访问的令牌解析端点。
     * /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                //.exceptionTranslator(customOauth2ExceptionTranslator)
                .userDetailsService(integrationUserDetailsService)
                .authorizationCodeServices(redisAuthenticationCodeServices())
                .accessTokenConverter(accessTokenConverter())
                //自定义OAuth2返回格式
                .exceptionTranslator(customWebResponseExceptionTranslator)
        ;
    }


    /**
     * 使用Jwt的方式生成token
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        CustomJwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();

        converter.setSigningKey("secret");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


    /**
     * AuthenticationCode 存入redis中
     *
     * @return
     */
    @Bean
    public RedisAuthenticationCodeServices redisAuthenticationCodeServices() {
        return new RedisAuthenticationCodeServices(redisConnectionFactory);
    }

}