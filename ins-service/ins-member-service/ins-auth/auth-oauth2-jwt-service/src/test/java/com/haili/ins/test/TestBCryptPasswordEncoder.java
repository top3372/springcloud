package com.haili.ins.test;

/**
 * @author: LeonMa
 * @date: 2018/12/28 15:23
 */

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TestBCryptPasswordEncoder {

    @Test
    public void test89() throws UnsupportedEncodingException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
// 加密
        String encodedPassword = passwordEncoder.encode("123456");

        System.out.println(encodedPassword);

        String creds = String.format("%s:%s", "frontend", "frontend");
        System.out.println(new StringBuilder("Basic ").append(Base64.getEncoder()
                .encodeToString(creds.getBytes("utf-8"))).toString());
    }

}

