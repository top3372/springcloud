package com.haili.ins.common.security.access.prepost.permission;

import com.haili.ins.common.security.access.constants.RoleConstant;
import com.haili.ins.common.security.access.context.SecurityThreadLocalContext;
import com.haili.ins.common.security.access.vo.UserRolePermission;
import com.haili.ins.common.utils.CollectionUtil;
import com.haili.ins.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: leon
 * @Date: 2019/4/12 10:09
 * @Version 1.0
 */
@Slf4j
@Component("permissionService")
public class PermissionServiceImpl implements PermissionService {

    /**
     * 放行所有请求
     *
     * @return {boolean}
     */
    @Override
    public boolean permitAll() {
        return true;
    }

    /**
     * 只有超管角色才可访问
     *
     * @return {boolean}
     */
    @Override
    public boolean denyAll() {
        return false;
                //hasRole(RoleConstant.ADMIN);
    }

    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param permission 权限
     * @return {boolean}
     */
    @Override
    public boolean hasPermission(String permission) {
        return hasAnyPermission(permission);
    }

    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param role 角色
     * @return {boolean}
     */
    @Override
    public boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    @Override
    public boolean hasAnyRole(String... role) {
        UserRolePermission userRole = SecurityThreadLocalContext.getUserRolePermission();

        String[] roles = StringUtils.toStrArray(userRole.getRoles());
        for (String r : role) {
            if (CollectionUtil.contains(roles, r)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyPermission(String... permission) {
        UserRolePermission userPermission = SecurityThreadLocalContext.getUserRolePermission();

        String[] permissions = StringUtils.toStrArray(userPermission.getPermissions());
        for (String p : permission) {
            if (CollectionUtil.contains(permissions, p)) {
                return true;
            }
        }
        return false;
    }
}
