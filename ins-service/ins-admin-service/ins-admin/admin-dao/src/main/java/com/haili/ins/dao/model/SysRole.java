package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: sys_role
 */
@Table(name = "`sys_role`")
@Data
public class SysRole implements Serializable {
    /**
     * 角色ID
     *
     * Table:     sys_role
     * Column:    role_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`role_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    /**
     * 角色名称
     *
     * Table:     sys_role
     * Column:    role_name
     * Nullable:  false
     */
    @Column(name = "`role_name`")
    private String roleName;

    /**
     * 角色权限字符串
     *
     * Table:     sys_role
     * Column:    role_key
     * Nullable:  false
     */
    @Column(name = "`role_key`")
    private String roleKey;

    /**
     * 显示顺序
     *
     * Table:     sys_role
     * Column:    role_sort
     * Nullable:  false
     */
    @Column(name = "`role_sort`")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限）
     *
     * Table:     sys_role
     * Column:    data_scope
     * Nullable:  true
     */
    @Column(name = "`data_scope`")
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     *
     * Table:     sys_role
     * Column:    status
     * Nullable:  false
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     *
     * Table:     sys_role
     * Column:    del_flag
     * Nullable:  true
     */
    @Column(name = "`del_flag`")
    private String delFlag;

    /**
     * 创建者
     *
     * Table:     sys_role
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     *
     * Table:     sys_role
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新者
     *
     * Table:     sys_role
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * 更新时间
     *
     * Table:     sys_role
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 备注
     *
     * Table:     sys_role
     * Column:    remark
     * Nullable:  true
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;
}