package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: sys_user
 */
@Table(name = "`sys_user`")
@Data
public class SysUser implements Serializable {
    /**
     * 用户ID
     * <p>
     * Table:     sys_user
     * Column:    user_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`user_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 部门ID
     * <p>
     * Table:     sys_user
     * Column:    dept_id
     * Nullable:  true
     */
    @Column(name = "`dept_id`")
    private Long deptId;

    /**
     * 登录账号
     * <p>
     * Table:     sys_user
     * Column:    login_name
     * Nullable:  false
     */
    @Column(name = "`login_name`")
    private String loginName;

    /**
     * 用户昵称
     * <p>
     * Table:     sys_user
     * Column:    user_name
     * Nullable:  false
     */
    @Column(name = "`user_name`")
    private String userName;

    /**
     * 用户类型（00系统用户）
     * <p>
     * Table:     sys_user
     * Column:    user_type
     * Nullable:  true
     */
    @Column(name = "`user_type`")
    private String userType;

    /**
     * 用户邮箱
     * <p>
     * Table:     sys_user
     * Column:    email
     * Nullable:  true
     */
    @Column(name = "`email`")
    private String email;

    /**
     * 手机号码
     * <p>
     * Table:     sys_user
     * Column:    phone
     * Nullable:  true
     */
    @Column(name = "`phone`")
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     * <p>
     * Table:     sys_user
     * Column:    sex
     * Nullable:  true
     */
    @Column(name = "`sex`")
    private String sex;

    /**
     * 头像路径
     * <p>
     * Table:     sys_user
     * Column:    avatar
     * Nullable:  true
     */
    @Column(name = "`avatar`")
    private String avatar;

    /**
     * 密码
     * <p>
     * Table:     sys_user
     * Column:    password
     * Nullable:  true
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 盐加密
     * <p>
     * Table:     sys_user
     * Column:    salt
     * Nullable:  true
     */
    @Column(name = "`salt`")
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     * <p>
     * Table:     sys_user
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     * <p>
     * Table:     sys_user
     * Column:    del_flag
     * Nullable:  true
     */
    @Column(name = "`del_flag`")
    private String delFlag;

    /**
     * 最后登陆IP
     * <p>
     * Table:     sys_user
     * Column:    login_ip
     * Nullable:  true
     */
    @Column(name = "`login_ip`")
    private String loginIp;

    /**
     * 最后登陆时间
     * <p>
     * Table:     sys_user
     * Column:    login_date
     * Nullable:  true
     */
    @Column(name = "`login_date`")
    private Date loginDate;

    /**
     * 创建者
     * <p>
     * Table:     sys_user
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     * <p>
     * Table:     sys_user
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新者
     * <p>
     * Table:     sys_user
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * 更新时间
     * <p>
     * Table:     sys_user
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 备注
     * <p>
     * Table:     sys_user
     * Column:    remark
     * Nullable:  true
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;
}