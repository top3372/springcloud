package com.haili.ins.controller;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

/**
 * @Author: leon
 * @Date: 2019/3/22 23:43
 * @Version 1.0
 */
@RestController
public class JwtController {

    private String securityKey;

    @RequestMapping("encrypt/jwt/check")
    public void checkJwt(String jwt){
        SecretKey generateKey = JwtUtil.generateKey(SignatureAlgorithm.HS256,securityKey);
        JwtUtil.checkJWT(generateKey,jwt,"");
    }
}
