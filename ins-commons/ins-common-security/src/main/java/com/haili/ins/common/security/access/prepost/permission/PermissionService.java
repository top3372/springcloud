package com.haili.ins.common.security.access.prepost.permission;

/**
 * @Author: leon
 * @Date: 2019/4/12 0:09
 * @Version 1.0
 */
public interface PermissionService {

    /**
     * 放行所有请求
     *
     * @return {boolean}
     */
    public boolean permitAll();

    /**
     * 只有超管角色才可访问
     *
     * @return {boolean}
     */
    public boolean denyAll();

    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param permission 权限
     * @return {boolean}
     */
    public boolean hasPermission(String permission);

    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param role 角色
     * @return {boolean}
     */
    public boolean hasRole(String role);

    public boolean hasAnyRole(String... role);

    public boolean hasAnyPermission(String... permission);
}
