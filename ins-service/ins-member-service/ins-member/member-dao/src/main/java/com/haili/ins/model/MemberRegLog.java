package com.haili.ins.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: t_member_reg_log
 */
@Table(name = "`t_member_reg_log`")
@Data
public class MemberRegLog implements Serializable {
    /**
     * Table:     t_member_reg_log
     * Column:    ID
     * Nullable:  false
     */
    @Id
    @Column(name = "`ID`")
    private String id;

    /**
     * Table:     t_member_reg_log
     * Column:    MEMBER_ID
     * Nullable:  true
     */
    @Column(name = "`MEMBER_ID`")
    private String memberId;

    /**
     * Table:     t_member_reg_log
     * Column:    REG_TYPE
     * Nullable:  true
     */
    @Column(name = "`REG_TYPE`")
    private String regType;

    /**
     * Table:     t_member_reg_log
     * Column:    THIRD_SOURCE
     * Nullable:  true
     */
    @Column(name = "`THIRD_SOURCE`")
    private String thirdSource;

    /**
     * Table:     t_member_reg_log
     * Column:    REG_IP
     * Nullable:  true
     */
    @Column(name = "`REG_IP`")
    private String regIp;

    /**
     * Table:     t_member_reg_log
     * Column:    SOURCE_CODE
     * Nullable:  true
     */
    @Column(name = "`SOURCE_CODE`")
    private String sourceCode;

    /**
     * Table:     t_member_reg_log
     * Column:    REG_TERMINAL
     * Nullable:  true
     */
    @Column(name = "`REG_TERMINAL`")
    private String regTerminal;

    /**
     * Table:     t_member_reg_log
     * Column:    DEVICE_ID
     * Nullable:  true
     */
    @Column(name = "`DEVICE_ID`")
    private String deviceId;

    /**
     * Table:     t_member_reg_log
     * Column:    STATUS
     * Nullable:  true
     */
    @Column(name = "`STATUS`")
    private String status;

    /**
     * Table:     t_member_reg_log
     * Column:    ERR_MSG
     * Nullable:  true
     */
    @Column(name = "`ERR_MSG`")
    private String errMsg;

    /**
     * Table:     t_member_reg_log
     * Column:    CREATE_DATE
     * Nullable:  true
     */
    @Column(name = "`CREATE_DATE`")
    private Date createDate;

    /**
     * Table:     t_member_reg_log
     * Column:    CREATE_BY
     * Nullable:  true
     */
    @Column(name = "`CREATE_BY`")
    private String createBy;

    /**
     * Table:     t_member_reg_log
     * Column:    UPDATE_DATE
     * Nullable:  true
     */
    @Column(name = "`UPDATE_DATE`")
    private Date updateDate;

    /**
     * Table:     t_member_reg_log
     * Column:    UPDATE_BY
     * Nullable:  true
     */
    @Column(name = "`UPDATE_BY`")
    private String updateBy;

    /**
     * Table:     t_member_reg_log
     * Column:    REG_DESC
     * Nullable:  true
     */
    @Column(name = "`REG_DESC`")
    private String regDesc;

    private static final long serialVersionUID = 1L;
}