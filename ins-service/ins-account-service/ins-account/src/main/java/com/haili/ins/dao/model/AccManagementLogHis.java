package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: T_ACC_MANAGEMENT_LOG_HIS
 */
@Table(name = "`T_ACC_MANAGEMENT_LOG_HIS`")
@Data
public class AccManagementLogHis implements Serializable {
    /**
     * 内部流水号
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    ACC_SEQ_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`ACC_SEQ_NO`")
    private String accSeqNo;

    /**
     * 系统跟踪号
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    SYS_TRACE_NO
     * Nullable:  true
     */
    @Column(name = "`SYS_TRACE_NO`")
    private String sysTraceNo;

    /**
     * 交易时间
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    TXN_TIME
     * Nullable:  true
     */
    @Column(name = "`TXN_TIME`")
    private Date txnTime;

    @Column(name = "`OWNER_ORG`")
    private String ownerOrg;

    /**
     * 账户号码
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    ACCOUNT_NO
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_NO`")
    private String accountNo;

    /**
     * 交易类型：
     * 1:开户
     * 2:销户
     * 3:账户冻结
     * 4:账户解冻
     * 5:锁定
     * 6:解锁
     * <p>
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    TXN_TYPE
     * Nullable:  true
     */
    @Column(name = "`TXN_TYPE`")
    private String txnType;

    /**
     * 交易代码
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    TXN_CODE
     * Nullable:  true
     */
    @Column(name = "`TXN_CODE`")
    private String txnCode;

    /**
     * 交易摘要
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    TXN_DSCPT
     * Nullable:  true
     */
    @Column(name = "`TXN_DSCPT`")
    private String txnDscpt;

    /**
     * 原状态
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    BEFORE_STATUS
     * Nullable:  true
     */
    @Column(name = "`BEFORE_STATUS`")
    private String beforeStatus;

    /**
     * 现状态
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    AFTER_STATUS
     * Nullable:  true
     */
    @Column(name = "`AFTER_STATUS`")
    private String afterStatus;

    /**
     * 备注
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    REMARK
     * Nullable:  true
     */
    @Column(name = "`REMARK`")
    private String remark;

    /**
     * 预留金额字段1
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    RSVD_AMT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT1`")
    private BigDecimal rsvdAmt1;

    /**
     * 预留金额字段2
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    RSVD_AMT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT2`")
    private BigDecimal rsvdAmt2;

    /**
     * 预留文本字段1
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    RSVD_TEXT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT1`")
    private String rsvdText1;

    /**
     * 预留文本字段2
     * <p>
     * Table:     T_ACC_MANAGEMENT_LOG_HIS
     * Column:    RSVD_TEXT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT2`")
    private String rsvdText2;

    private static final long serialVersionUID = 1L;
}