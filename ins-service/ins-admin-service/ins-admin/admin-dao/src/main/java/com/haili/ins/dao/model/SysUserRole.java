package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Table(name = "`sys_user_role`")
@Data
public class SysUserRole implements Serializable {
    /**
     * 用户ID
     *
     * Table:     sys_user_role
     * Column:    user_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`user_id`")
    private Long userId;

    /**
     * 角色ID
     *
     * Table:     sys_user_role
     * Column:    role_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`role_id`")
    private Long roleId;

    private static final long serialVersionUID = 1L;
}