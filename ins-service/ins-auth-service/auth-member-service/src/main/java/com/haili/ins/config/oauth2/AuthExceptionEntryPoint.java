package com.haili.ins.config.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haili.ins.common.dto.ResultInfo;
import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws  ServletException {

        Map map = new HashMap();
        map.put("error", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("code", ResponseCodeEnum.NO_AUTH_CODE.getCode());
        map.put("desc", ResponseCodeEnum.NO_AUTH_CODE.getDesc());
        map.put("message", authException.getMessage());
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setResponseCode(ResponseCodeEnum.NO_AUTH_CODE.getCode());
        resultInfo.setResponseDesc(ResponseCodeEnum.NO_AUTH_CODE.getDesc());
        resultInfo.setResponseData(map);

        log.info(JSONUtil.toJson(resultInfo));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), resultInfo);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}