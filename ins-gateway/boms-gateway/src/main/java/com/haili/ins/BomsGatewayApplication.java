package com.haili.ins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class BomsGatewayApplication {


    public static void main(String[] args) {
        SpringApplication.run(BomsGatewayApplication.class, args);
    }
}
