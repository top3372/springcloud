package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: t_member_pay_Info
 */
@Table(name = "`t_member_pay_Info`")
@Data
public class MemberPayInfo implements Serializable {
    /**
     * Table:     t_member_pay_Info
     * Column:    ID
     * Nullable:  false
     */
    @Id
    @Column(name = "`ID`")
    private String id;

    /**
     * Table:     t_member_pay_Info
     * Column:    MEMBER_ID
     * Nullable:  true
     */
    @Column(name = "`MEMBER_ID`")
    private String memberId;

    /**
     * Table:     t_member_pay_Info
     * Column:    PAY_PWD_TYPE
     * Nullable:  true
     */
    @Column(name = "`PAY_PWD_TYPE`")
    private String payPwdType;

    /**
     * Table:     t_member_pay_Info
     * Column:    PAY_PWD
     * Nullable:  true
     */
    @Column(name = "`PAY_PWD`")
    private String payPwd;

    /**
     * Table:     t_member_pay_Info
     * Column:    PAY_PWD_STRENGTH
     * Nullable:  true
     */
    @Column(name = "`PAY_PWD_STRENGTH`")
    private String payPwdStrength;

    /**
     * Table:     t_member_pay_Info
     * Column:    PAY_STATUS
     * Nullable:  true
     */
    @Column(name = "`PAY_STATUS`")
    private String payStatus;

    /**
     * Table:     t_member_pay_Info
     * Column:    LAST_PAY_TIME
     * Nullable:  true
     */
    @Column(name = "`LAST_PAY_TIME`")
    private Date lastPayTime;

    /**
     * Table:     t_member_pay_Info
     * Column:    LAST_PAY_IP
     * Nullable:  true
     */
    @Column(name = "`LAST_PAY_IP`")
    private String lastPayIp;

    /**
     * Table:     t_member_pay_Info
     * Column:    LAST_PAY_FAIL_TIME
     * Nullable:  true
     */
    @Column(name = "`LAST_PAY_FAIL_TIME`")
    private Date lastPayFailTime;

    /**
     * Table:     t_member_pay_Info
     * Column:    LOCK_TIMES
     * Nullable:  true
     */
    @Column(name = "`LOCK_TIMES`")
    private String lockTimes;

    /**
     * Table:     t_member_pay_Info
     * Column:    LOCK_DATE
     * Nullable:  true
     */
    @Column(name = "`LOCK_DATE`")
    private Date lockDate;

    /**
     * Table:     t_member_pay_Info
     * Column:    CREATE_DATE
     * Nullable:  true
     */
    @Column(name = "`CREATE_DATE`")
    private Date createDate;

    /**
     * Table:     t_member_pay_Info
     * Column:    CREATE_BY
     * Nullable:  true
     */
    @Column(name = "`CREATE_BY`")
    private String createBy;

    /**
     * Table:     t_member_pay_Info
     * Column:    UPDATE_DATE
     * Nullable:  true
     */
    @Column(name = "`UPDATE_DATE`")
    private Date updateDate;

    /**
     * Table:     t_member_pay_Info
     * Column:    UPDATE_BY
     * Nullable:  true
     */
    @Column(name = "`UPDATE_BY`")
    private String updateBy;

    private static final long serialVersionUID = 1L;
}