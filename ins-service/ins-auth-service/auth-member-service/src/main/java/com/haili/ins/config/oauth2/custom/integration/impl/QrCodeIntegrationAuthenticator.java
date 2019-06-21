package com.haili.ins.config.oauth2.custom.integration.impl;

import com.haili.ins.config.oauth2.custom.integration.AbstractPreparableIntegrationAuthenticator;
import com.haili.ins.config.oauth2.custom.integration.IntegrationAuthentication;
import com.haili.ins.dto.Oauth2User;
import org.springframework.stereotype.Component;

/**
 * 二维码登录
 *
 * @author: LeonMa
 * @date: 2019/01/17 12:03
 */
@Component
public class QrCodeIntegrationAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    private final static String QRCODE_AUTH_TYPE = "qr";

    @Override
    public Oauth2User authenticate(IntegrationAuthentication integrationAuthentication) {
        //根据二维码查询用户  redis中查询

        return null;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        //验证二维码是否已经过期


    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return QRCODE_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }
}
