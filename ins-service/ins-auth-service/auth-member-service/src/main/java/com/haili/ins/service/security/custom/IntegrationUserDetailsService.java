package com.haili.ins.service.security.custom;


import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthentication;
import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthenticationContext;
import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthenticator;
import com.haili.ins.dto.CustomUserDetails;
import com.haili.ins.dto.Oauth2User;
import com.haili.ins.enums.ResponseCodeEnum;
import com.haili.ins.feign.MemberFeign;
import com.haili.ins.utils.RedisUtil;
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

        if (oauth2User == null) {
            throw new UsernameNotFoundException(ResponseCodeEnum.LOGIN_FAILURE.getDesc());
        }

        // 调用FeignClient查询角色
        List<String> rolesIdList = new ArrayList<>();
        rolesIdList.add("administrator");

        //查询资源
        List<String> resourcesList = new ArrayList<>();
        resourcesList.add("user::add");
        resourcesList.add("user::edit");

        // 获取用户权限列表 放入Security的User中
        List<GrantedAuthority> authorities = convertToAuthorities(oauth2User, rolesIdList);

        User user = new User(oauth2User.getUsername(),
                oauth2User.getPassword(), isActive(oauth2User.getStatus()),
                true, true,
                isActive(oauth2User.getStatus()), authorities);
        CustomUserDetails custom = new CustomUserDetails(oauth2User, user);
        custom.setRoles(rolesIdList);
        custom.setResources(resourcesList);
        return custom;

    }

    private boolean isActive(String active) {
        return "1".equals(active) ? true : false;
    }

    private List<GrantedAuthority> convertToAuthorities(Oauth2User baseUser, List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        //redisUtil.del(baseUser.getId());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e);
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
