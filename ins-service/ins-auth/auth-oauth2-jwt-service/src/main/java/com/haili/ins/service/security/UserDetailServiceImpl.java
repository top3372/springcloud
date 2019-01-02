package com.haili.ins.service.security;

import com.haili.ins.dto.CustomUserDetails;
import com.haili.ins.feign.MemberFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LeonMa
 * @date: 2018/12/27 13:34
 */
@Service("oauth2UserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

//    @Autowired
//    private MemberFeign memberFeign;


    /**
     *  实现Spring Security OAuth2 中的 UserDetailsService方法
     *  根据用户名获取登录用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户姓名 获取用户信息

        //根据用户id 获取Role信息


        CustomUserDetails customUserDetails = new CustomUserDetails();
        //customUserDetails.setClientId("frontend");
        customUserDetails.setUsername("admin");
        customUserDetails.setPassword("123456");
        customUserDetails.setEnabled(true);
        customUserDetails.setUserId("111");
//        customUserDetails.setAuthorities(null);
//        customUserDetails.setRoles(};

        return customUserDetails;
    }
}
