package com.haili.ins.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: leon
 * @Date: 2019/4/4 16:03
 * @Version 1.0
 */
@Slf4j
public class WebUtils {


    public static void responseOutJson(HttpServletResponse response, String responseMessage) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseMessage);
            log.debug("返回是\n");
            log.debug(responseMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
