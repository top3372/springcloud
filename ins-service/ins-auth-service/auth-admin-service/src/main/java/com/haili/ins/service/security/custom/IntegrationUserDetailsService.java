package com.haili.ins.service.security.custom;


import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthentication;
import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthenticationContext;
import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthenticator;
import com.haili.ins.dto.CustomUserDetails;
import com.haili.ins.dto.auth.BaseRole;
import com.haili.ins.dto.auth.Oauth2User;
import com.haili.ins.feign.MemberFeign;
import com.haili.ins.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 集成认证用户服务
 *
 * @author LIQIU
 * @date 2018-3-7
 **/
@Service
public class IntegrationUserDetailsService implements UserDetailsService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private MemberFeign memberFeign;

    private List<IntegrationAuthenticator> authenticators;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
            integrationAuthentication.setAuthType("authorization_code");
        }
        integrationAuthentication.setUsername(username);
        Oauth2User oauth2User = this.authenticate(integrationAuthentication);

        if(oauth2User == null){
            throw new UsernameNotFoundException("用户获取失败");
        }

        // 调用FeignClient查询角色
        List<BaseRole> roles = new ArrayList<>();
        List<String> rolesIds = new ArrayList<>();
        roles.forEach(e -> {
            rolesIds.add(e.getRoleCode());
        });

        //查询资源


        // 获取用户权限列表 放入Security的User中
        List<GrantedAuthority> authorities = convertToAuthorities(oauth2User, roles);

        User user = new User(oauth2User.getUsername(),
                oauth2User.getPassword(), isActive(oauth2User.getStatus()),
                true, true,
                isActive(oauth2User.getStatus()), authorities);
        CustomUserDetails custom=  new CustomUserDetails(oauth2User,user);
        custom.setRoles(rolesIds);
//        custom.setResources();
        return custom;

    }

    private boolean isActive(String active){
        return "1".equals(active) ? true : false;
    }

    private List<GrantedAuthority> convertToAuthorities(Oauth2User baseUser, List<BaseRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        //redisUtil.del(baseUser.getId());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleCode());
            authorities.add(authority);
            //存储角色到redis
            //redisUtil.listRightPush(baseUser.getId(), e);
        });
        return authorities;
    }

    private Oauth2User authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}
