package com.haili.ins.api.gateway.config.oauth2;

import lombok.Data;

/**
 * @Author: leon
 * @Date: 2019/3/28 15:03
 * @Version 1.0
 */
@Data
public class CustomPrincipal {

    private String userName;
    private String userId;
    private String status;
}
