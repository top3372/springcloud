package com.haili.ins.config.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.util.Assert;

@Slf4j
public class RedisAuthenticationCodeServices extends RandomValueAuthorizationCodeServices {

    private static final String AUTH_CODE_KEY = "auth_code";
    private RedisConnectionFactory connectionFactory;

    public RedisAuthenticationCodeServices(RedisConnectionFactory connectionFactory) {
        Assert.notNull(connectionFactory, "RedisConnectionFactory required");
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        RedisConnection conn = getConnection();
        try {
            OAuth2Authentication authentication = null;

            try {
                authentication = SerializationUtils
                        .deserialize(conn.hGet(AUTH_CODE_KEY.getBytes("utf-8"), code.getBytes("utf-8")));
            } catch (Exception e) {
                return null;
            }

            if (null != authentication) {
                conn.hDel(AUTH_CODE_KEY.getBytes("utf-8"), code.getBytes("utf-8"));
            }

            return authentication;
        } catch (Exception e) {
            return null;
        } finally {
            conn.close();
        }
    }

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        RedisConnection conn = getConnection();
        try {
            conn.hSet(AUTH_CODE_KEY.getBytes("utf-8"), code.getBytes("utf-8"),
                    SerializationUtils.serialize(authentication));
        } catch (Exception e) {
            log.error("保存authentication code 失败", e);
        } finally {
            conn.close();
        }

    }

    private RedisConnection getConnection() {
        return connectionFactory.getConnection();
    }

}
