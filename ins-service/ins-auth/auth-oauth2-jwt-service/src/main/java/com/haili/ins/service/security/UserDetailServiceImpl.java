package com.haili.ins.service.security;

import com.haili.ins.feign.MemberFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: LeonMa
 * @date: 2018/12/27 13:34
 */
@Service("oauth2UserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private MemberFeign memberFeign;


    /**
     *  实现Spring Security OAuth2 中的 UserDetailsService方法
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
