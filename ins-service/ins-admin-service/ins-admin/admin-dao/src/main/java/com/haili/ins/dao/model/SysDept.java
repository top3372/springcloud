package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: sys_dept
 */
@Table(name = "`sys_dept`")
@Data
public class SysDept implements Serializable {
    /**
     * 部门id
     *
     * Table:     sys_dept
     * Column:    dept_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`dept_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    /**
     * 父部门id
     *
     * Table:     sys_dept
     * Column:    parent_id
     * Nullable:  true
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 祖级列表
     *
     * Table:     sys_dept
     * Column:    ancestors
     * Nullable:  true
     */
    @Column(name = "`ancestors`")
    private String ancestors;

    /**
     * 部门名称
     *
     * Table:     sys_dept
     * Column:    dept_name
     * Nullable:  true
     */
    @Column(name = "`dept_name`")
    private String deptName;

    /**
     * 显示顺序
     *
     * Table:     sys_dept
     * Column:    order_num
     * Nullable:  true
     */
    @Column(name = "`order_num`")
    private Integer orderNum;

    /**
     * 负责人
     *
     * Table:     sys_dept
     * Column:    leader
     * Nullable:  true
     */
    @Column(name = "`leader`")
    private String leader;

    /**
     * 联系电话
     *
     * Table:     sys_dept
     * Column:    phone
     * Nullable:  true
     */
    @Column(name = "`phone`")
    private String phone;

    /**
     * 邮箱
     *
     * Table:     sys_dept
     * Column:    email
     * Nullable:  true
     */
    @Column(name = "`email`")
    private String email;

    /**
     * 部门状态（0正常 1停用）
     *
     * Table:     sys_dept
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     *
     * Table:     sys_dept
     * Column:    del_flag
     * Nullable:  true
     */
    @Column(name = "`del_flag`")
    private String delFlag;

    /**
     * 创建者
     *
     * Table:     sys_dept
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     *
     * Table:     sys_dept
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新者
     *
     * Table:     sys_dept
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * 更新时间
     *
     * Table:     sys_dept
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}