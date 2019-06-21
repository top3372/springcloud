package com.haili.ins.api.gateway.config.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomPrincipalExtractor implements PrincipalExtractor {
    @Override
    public Object extractPrincipal(Map<String, Object> map) {


        if (!map.isEmpty()) {

            CustomPrincipal customPrincipal = new CustomPrincipal();
            boolean result = (Boolean) map.get("result");
            Map<String, String> dataMap = (Map) map.get("data");
            String code = (String) map.get("code");
            String msg = (String) map.get("msg");


            customPrincipal.setUserName((String) dataMap.get("userName"));
            customPrincipal.setUserId((String) dataMap.get("userId"));
            customPrincipal.setStatus((String) dataMap.get("status"));
            customPrincipal.setRoles((String) dataMap.get("roles"));
            customPrincipal.setResources((String) dataMap.get("resources"));
            return customPrincipal;
        }
        return null;
    }
}