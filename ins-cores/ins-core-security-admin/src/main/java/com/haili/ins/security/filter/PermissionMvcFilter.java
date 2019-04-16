package com.haili.ins.security.filter;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.common.security.access.context.SecurityThreadLocalContext;
import com.haili.ins.common.security.access.vo.UserRolePermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: leon
 * @Date: 2019/4/12 16:39
 * @Version 1.0
 */
@Slf4j
@Order(1)
public class PermissionMvcFilter implements Filter {


    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" 开始PermissionMvcFilter调用 : ");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String url = request.getRequestURI();
        log.info("请求uri: " + url);

        String userId = request.getHeader(HttpHeaderConstant.USER_ID);
        String roles = request.getHeader(HttpHeaderConstant.ROLES);
        String permissions = request.getHeader(HttpHeaderConstant.PERMISSIONS);
        log.info("userId: " + userId + ",roles: " + roles + ",permissions: " + permissions);
        UserRolePermission urp = new UserRolePermission(userId,roles,permissions);
        SecurityThreadLocalContext.setUserRolePermission(urp);

        log.info(" 结束PermissionMvcFilter调用 : ");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
