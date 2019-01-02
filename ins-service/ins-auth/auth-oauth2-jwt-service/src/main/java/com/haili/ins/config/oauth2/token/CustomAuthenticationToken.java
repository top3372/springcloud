package com.haili.ins.config.oauth2.token;

import com.haili.ins.dto.CustomUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by keets on 2017/8/5.
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private CustomUserDetails userDetails;

    public CustomAuthenticationToken(CustomUserDetails userDetails) {
        super(null);
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    @Override
    public Object getCredentials() {
        return this.userDetails.getPassword();
    }

}