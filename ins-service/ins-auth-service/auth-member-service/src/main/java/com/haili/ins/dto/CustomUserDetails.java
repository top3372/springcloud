package com.haili.ins.dto;


import com.haili.ins.dto.auth.Oauth2User;
import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: LeonMa
 * @date: 2018/12/28 12:28
 */
@Data
public class CustomUserDetails implements UserDetails, CredentialsContainer {


    private static final long serialVersionUID = -8414051577561674553L;
    private final Oauth2User oauth2User;
    private final User user;
    private Collection<String> resources = new ArrayList<>();
    private Collection<String> roles = new ArrayList<>();
    private Collection<GrantedAuthority> grantedAuthorities;
    private String clientId;

    public CustomUserDetails(Oauth2User oauth2User,User user){
        this.oauth2User = oauth2User;
        this.user = user;
    }

    @Override
    public void eraseCredentials() {
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

}
