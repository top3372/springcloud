package com.haili.ins.config.oauth2.provider;

import com.haili.ins.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 授权模式下 验证用户名/密码
 */
@Component
public class AuthCodeTypeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService oauth2UserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
                CustomUserDetails userDetails = (CustomUserDetails) oauth2UserDetailsService.loadUserByUsername(token.getName());
                if (userDetails == null) {
                    throw new UsernameNotFoundException("找不到该用户");
                }
                if (userDetails.getPassword().equals(token.getCredentials().toString())) {

                    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                }else{
                    throw new BadCredentialsException("密码错误");
                }

            } else {
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }


    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}