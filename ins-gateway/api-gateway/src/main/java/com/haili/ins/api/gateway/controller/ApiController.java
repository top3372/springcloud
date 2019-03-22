package com.haili.ins.api.gateway.controller;

//import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {


//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    public String find() {
//        //如果已经认证通过，直接跳转到首页
//        if (SecurityUtils.getSubject().isAuthenticated()) {
//            return "success";
//        }
//        return "login";
//
//    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal user(Principal user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        System.out.println(details.getTokenType() + "" + details.getTokenValue());
        return user;
    }
}