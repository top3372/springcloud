package com.haili.ins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: leon
 * @Date: 2019/4/11 9:53
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EncryptServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncryptServiceApplication.class, args);
    }
}
