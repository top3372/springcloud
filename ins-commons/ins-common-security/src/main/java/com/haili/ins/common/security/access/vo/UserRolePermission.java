package com.haili.ins.common.security.access.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: leon
 * @Date: 2019/4/12 10:38
 * @Version 1.0
 */
@Data
public class UserRolePermission implements Serializable {

    private static final long serialVersionUID = -6196668386612721595L;
    private String userId;
    private String roles ;
    private String permissions;

    public UserRolePermission(){

    }

    public UserRolePermission(String userId,String roles,String permissions){
        this.userId = userId;
        this.roles = roles;
        this.permissions = permissions;
    }

}
