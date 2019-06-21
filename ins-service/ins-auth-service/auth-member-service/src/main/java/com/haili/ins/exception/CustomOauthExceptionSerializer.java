package com.haili.ins.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.haili.ins.common.utils.JSONUtil;
import com.haili.ins.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/5/24 0024.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Slf4j
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        log.info("CustomOauthExceptionSerializer start...");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Map map = new HashMap();
        map.put("error", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("code", ResponseCodeEnum.NO_AUTH_CODE.getCode());
        map.put("desc", ResponseCodeEnum.NO_AUTH_CODE.getDesc());
        map.put("message", value.getMessage());
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));

        gen.writeStartObject();
        gen.writeStringField("responseCode", ResponseCodeEnum.NO_AUTH_CODE.getCode());
        gen.writeStringField("responseDesc", ResponseCodeEnum.NO_AUTH_CODE.getDesc());
        gen.writeStringField("responseData", JSONUtil.toJson(map));

        if (value.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
        log.info("CustomOauthExceptionSerializer end...");
    }
}