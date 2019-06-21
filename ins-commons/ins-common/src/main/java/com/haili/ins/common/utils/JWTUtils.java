package com.haili.ins.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.haili.ins.common.constants.HailiInsConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * API调用认证工具类，采用RSA加密
 *
 * @author yinjihuan
 */
public class JWTUtils {


    /**
     * token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj
     */
    public static final String SECRET = "haili.ins.service";
    /**
     * token 过期时间: 10天
     */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     *
     * @param sequenceNo 登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(String sequenceNo) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
//                .withClaim("iss", "Service") // payload
////                .withClaim("aud", "APP")
                .withClaim("sequence_no", sequenceNo)
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature

        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static String verifyToken(String token) {

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            return HailiInsConstant.SUCCESS;
        } catch (JWTVerificationException je) {
            je.printStackTrace();
            return HailiInsConstant.VERIFY_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return HailiInsConstant.VERIFY_ERROR;
        }
    }

    public static Map<String, Claim> praseClaim(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);

        } catch (JWTVerificationException je) {
            je.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return user_id
     */
    public static String getAppUID(String token) {
        Map<String, Claim> claims = praseClaim(token);
        Claim user_id_claim = claims.get("sequence_no");
        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return user_id_claim.asString();
    }


    public static void main(String[] args) throws Exception {
        System.out.println(JWTUtils.createToken("111111L"));
    }
}