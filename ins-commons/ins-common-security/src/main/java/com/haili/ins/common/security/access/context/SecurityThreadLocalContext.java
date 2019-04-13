package com.haili.ins.common.security.access.context;

import com.haili.ins.common.security.access.vo.UserRolePermission;

/**
 * @Author: leon
 * @Date: 2019/4/12 10:27
 * @Version 1.0
 */
public class SecurityThreadLocalContext {

    private static ThreadLocal<UserRolePermission> userRolePermissionThreadLocal = new ThreadLocal<>();

    public static void setUserRolePermission(UserRolePermission userRolePermission){
        userRolePermissionThreadLocal.set(userRolePermission);
    }

    public static UserRolePermission getUserRolePermission(){
        return userRolePermissionThreadLocal.get();
    }

    public static void clearUserRolePermission(){
        userRolePermissionThreadLocal.remove();
    }



}
