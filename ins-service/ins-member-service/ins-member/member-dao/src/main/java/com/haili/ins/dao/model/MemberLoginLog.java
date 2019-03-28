package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: t_member_login_log
 */
@Table(name = "`t_member_login_log`")
@Data
public class MemberLoginLog implements Serializable {
    /**
     * Table:     t_member_login_log
     * Column:    ID
     * Nullable:  false
     */
    @Id
    @Column(name = "`ID`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * Table:     t_member_login_log
     * Column:    MEMBER_ID
     * Nullable:  true
     */
    @Column(name = "`MEMBER_ID`")
    private String memberId;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_TYPE
     * Nullable:  true
     */
    @Column(name = "`LOGIN_TYPE`")
    private String loginType;

    /**
     * Table:     t_member_login_log
     * Column:    THIRD_SOURCE
     * Nullable:  true
     */
    @Column(name = "`THIRD_SOURCE`")
    private String thirdSource;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_NAME_TYPE
     * Nullable:  true
     */
    @Column(name = "`LOGIN_NAME_TYPE`")
    private String loginNameType;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_NAME
     * Nullable:  true
     */
    @Column(name = "`LOGIN_NAME`")
    private String loginName;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_PWD_TYPE
     * Nullable:  true
     */
    @Column(name = "`LOGIN_PWD_TYPE`")
    private String loginPwdType;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_STATUS
     * Nullable:  true
     */
    @Column(name = "`LOGIN_STATUS`")
    private String loginStatus;

    /**
     * Table:     t_member_login_log
     * Column:    FAIL_CAUSE
     * Nullable:  true
     */
    @Column(name = "`FAIL_CAUSE`")
    private String failCause;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_IP
     * Nullable:  true
     */
    @Column(name = "`LOGIN_IP`")
    private String loginIp;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_DEVICE_ID
     * Nullable:  true
     */
    @Column(name = "`LOGIN_DEVICE_ID`")
    private String loginDeviceId;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_TERMINAL
     * Nullable:  true
     */
    @Column(name = "`LOGIN_TERMINAL`")
    private String loginTerminal;

    /**
     * Table:     t_member_login_log
     * Column:    LOGIN_DESC
     * Nullable:  true
     */
    @Column(name = "`LOGIN_DESC`")
    private String loginDesc;

    /**
     * Table:     t_member_login_log
     * Column:    CREATE_DATE
     * Nullable:  true
     */
    @Column(name = "`CREATE_DATE`")
    private Date createDate;

    /**
     * Table:     t_member_login_log
     * Column:    CREATE_BY
     * Nullable:  true
     */
    @Column(name = "`CREATE_BY`")
    private String createBy;

    /**
     * Table:     t_member_login_log
     * Column:    UPDATE_DATE
     * Nullable:  true
     */
    @Column(name = "`UPDATE_DATE`")
    private Date updateDate;

    /**
     * Table:     t_member_login_log
     * Column:    UPDATE_BY
     * Nullable:  true
     */
    @Column(name = "`UPDATE_BY`")
    private String updateBy;

    private static final long serialVersionUID = 1L;
}