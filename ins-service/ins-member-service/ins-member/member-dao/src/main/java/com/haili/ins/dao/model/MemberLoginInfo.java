package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: t_member_login_info
 */
@Table(name = "`t_member_login_info`")
@Data
public class MemberLoginInfo implements Serializable {
    /**
     * Table:     t_member_login_info
     * Column:    ID
     * Nullable:  false
     */
    @Id
    @Column(name = "`ID`")
    private String id;

    /**
     * Table:     t_member_login_info
     * Column:    MEMBER_ID
     * Nullable:  true
     */
    @Column(name = "`MEMBER_ID`")
    private String memberId;

    /**
     * Table:     t_member_login_info
     * Column:    LOGIN_TYPE
     * Nullable:  true
     */
    @Column(name = "`LOGIN_TYPE`")
    private String loginType;

    /**
     * Table:     t_member_login_info
     * Column:    THIRD_SOURCE_CODE
     * Nullable:  true
     */
    @Column(name = "`THIRD_SOURCE_CODE`")
    private String thirdSourceCode;

    /**
     * Table:     t_member_login_info
     * Column:    LOGIN_NAME_TYPE
     * Nullable:  true
     */
    @Column(name = "`LOGIN_NAME_TYPE`")
    private String loginNameType;

    /**
     * Table:     t_member_login_info
     * Column:    LOGIN_NAME
     * Nullable:  true
     */
    @Column(name = "`LOGIN_NAME`")
    private String loginName;

    /**
     * Table:     t_member_login_info
     * Column:    LOGIN_PWD_TYPE
     * Nullable:  true
     */
    @Column(name = "`LOGIN_PWD_TYPE`")
    private String loginPwdType;

    /**
     * Table:     t_member_login_info
     * Column:    LOGIN_PWD
     * Nullable:  true
     */
    @Column(name = "`LOGIN_PWD`")
    private String loginPwd;

    /**
     * Table:     t_member_login_info
     * Column:    LOGIN_PWD_STRENGTH
     * Nullable:  true
     */
    @Column(name = "`LOGIN_PWD_STRENGTH`")
    private String loginPwdStrength;

    /**
     * Table:     t_member_login_info
     * Column:    LOGIN_STATUS
     * Nullable:  true
     */
    @Column(name = "`LOGIN_STATUS`")
    private String loginStatus;

    /**
     * Table:     t_member_login_info
     * Column:    LAST_LOGIN_TIME
     * Nullable:  true
     */
    @Column(name = "`LAST_LOGIN_TIME`")
    private Date lastLoginTime;

    /**
     * Table:     t_member_login_info
     * Column:    LAST_LOGIN_IP
     * Nullable:  true
     */
    @Column(name = "`LAST_LOGIN_IP`")
    private String lastLoginIp;

    /**
     * Table:     t_member_login_info
     * Column:    LAST_LOGIN_FAIL_TIME
     * Nullable:  true
     */
    @Column(name = "`LAST_LOGIN_FAIL_TIME`")
    private Date lastLoginFailTime;

    /**
     * Table:     t_member_login_info
     * Column:    FAIL_TIMES
     * Nullable:  true
     */
    @Column(name = "`FAIL_TIMES`")
    private String failTimes;

    /**
     * Table:     t_member_login_info
     * Column:    LOCK_DATE
     * Nullable:  true
     */
    @Column(name = "`LOCK_DATE`")
    private Date lockDate;

    /**
     * Table:     t_member_login_info
     * Column:    CREATE_DATE
     * Nullable:  true
     */
    @Column(name = "`CREATE_DATE`")
    private Date createDate;

    /**
     * Table:     t_member_login_info
     * Column:    CREATE_BY
     * Nullable:  true
     */
    @Column(name = "`CREATE_BY`")
    private String createBy;

    /**
     * Table:     t_member_login_info
     * Column:    UPDATE_DATE
     * Nullable:  true
     */
    @Column(name = "`UPDATE_DATE`")
    private Date updateDate;

    /**
     * Table:     t_member_login_info
     * Column:    UPDATE_BY
     * Nullable:  true
     */
    @Column(name = "`UPDATE_BY`")
    private String updateBy;

    private static final long serialVersionUID = 1L;
}