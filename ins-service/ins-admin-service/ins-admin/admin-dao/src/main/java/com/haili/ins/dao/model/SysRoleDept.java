package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Table(name = "`sys_role_dept`")
@Data
public class SysRoleDept implements Serializable {
    /**
     * 角色ID
     * <p>
     * Table:     sys_role_dept
     * Column:    role_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`role_id`")
    private Long roleId;

    /**
     * 部门ID
     * <p>
     * Table:     sys_role_dept
     * Column:    dept_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`dept_id`")
    private Long deptId;

    private static final long serialVersionUID = 1L;
}