package com.haili.ins.common.utils;



import com.haili.ins.common.constants.HailiInsConstant;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * API调用认证工具类，采用RSA加密
 *
 * @author yinjihuan
 */
public class JWTUtils {


    /**
     * token秘钥，请勿泄露，请勿随便修改
     */
    public static final String SECRET = "haili.ins.service";
    /**
     * token 过期时间: 10天
     */
    public static final int calendarField = Calendar.HOUR;
    public static final int calendarInterval = 1;

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     *
     * @param sub 登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(String sub) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Map<String, Object> claim = new ConcurrentHashMap<>();
        claim.put("sequence_no", sub);
        claim.put("ccc", "aaa");

        // build token
        // param backups {iss:Service, aud:APP}
        String token =
                Jwts.builder()
                        .setSubject(sub)
                        .setHeader(map)
                        .addClaims(claim)
                        .setId(UUID.randomUUID().toString())
                        .setIssuedAt(iatDate)
                        .setExpiration(expiresDate)
                        .signWith(SignatureAlgorithm.HS256,SECRET)
                        .compact();
//
//        JWT.create().withHeader(map) // header
////                .withClaim("iss", "Service") // payload
//////                .withClaim("aud", "APP")
//                .withClaim("sequence_no", sequenceNo)
//                .withIssuedAt(iatDate) // sign time
//                .withExpiresAt(expiresDate) // expire time
//                .sign(Algorithm.HMAC256(SECRET)); // signature

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
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return HailiInsConstant.SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return HailiInsConstant.VERIFY_ERROR;
        }
    }

    /**
     * 解析Token
     *
     * @param token
     * @return 返回subject
     * @throws Exception
     */
    public static String parserToken(String token) {

        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);

        return claims.getBody().getSubject();

    }


    public static void main(String[] args) throws Exception {
        System.out.println(JWTUtils.createToken("111111L"));
        System.out.println(JWTUtils.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJzdWIiOiIxMTExMTFMIiwianRpIjoiM2FhMGNiNWUtZGNhNS00MzFiLWE3YWItY2JkYmRjNjg4ZTVjIiwiaWF0IjoxNTY2ODEzNzU4LCJleHAiOjE1NjY4MTczNTh9.fUCjCCEly7SfHEzBjFo6MFKowLQWV5xdelkScInLJhQ"));
        System.out.println(JWTUtils.parserToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJzdWIiOiIxMTExMTFMIiwianRpIjoiM2FhMGNiNWUtZGNhNS00MzFiLWE3YWItY2JkYmRjNjg4ZTVjIiwiaWF0IjoxNTY2ODEzNzU4LCJleHAiOjE1NjY4MTczNTh9.fUCjCCEly7SfHEzBjFo6MFKowLQWV5xdelkScInLJhQ"));

    }
}