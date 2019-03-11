package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: T_ACC_ACCOUNT_FROZEN
 */
@Table(name = "`T_ACC_ACCOUNT_FROZEN`")
@Data
public class AccAccountFrozen implements Serializable {
    /**
     * 内部流水号
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    ACC_SEQ_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`ACC_SEQ_NO`")
    private String accSeqNo;

    /**
     * 系统跟踪号
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    SYS_TRACE_NO
     * Nullable:  true
     */
    @Column(name = "`SYS_TRACE_NO`")
    private String sysTraceNo;

    /**
     * 记账日期
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    CLEAR_DATE
     * Nullable:  true
     */
    @Column(name = "`CLEAR_DATE`")
    private String clearDate;

    /**
     * 交易时间
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    TXN_TIME
     * Nullable:  true
     */
    @Column(name = "`TXN_TIME`")
    private Date txnTime;

    /**
     * 账户号码
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    ACCOUNT_NO
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_NO`")
    private String accountNo;

    /**
     * 交易类型:
            0:正常
            1:红字
            2:蓝字
            
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    TXN_TYPE
     * Nullable:  true
     */
    @Column(name = "`TXN_TYPE`")
    private String txnType;

    /**
     * 交易代码
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    TXN_CODE
     * Nullable:  true
     */
    @Column(name = "`TXN_CODE`")
    private String txnCode;

    /**
     * 交易摘要
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    TXN_DSCPT
     * Nullable:  true
     */
    @Column(name = "`TXN_DSCPT`")
    private String txnDscpt;

    /**
     * 币种代码
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    TXN_CURRENCY_CODE
     * Nullable:  true
     */
    @Column(name = "`TXN_CURRENCY_CODE`")
    private String txnCurrencyCode;

    /**
     * 交易金额
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    TXN_AMT
     * Nullable:  true
     */
    @Column(name = "`TXN_AMT`")
    private BigDecimal txnAmt;

    /**
     * 账户余额
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    BALANCE
     * Nullable:  true
     */
    @Column(name = "`BALANCE`")
    private BigDecimal balance;

    /**
     * 交易前可用余额
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    BEFORE_AVAILABLE
     * Nullable:  true
     */
    @Column(name = "`BEFORE_AVAILABLE`")
    private BigDecimal beforeAvailable;

    /**
     * 交易后可用余额
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    AFTER_AVAILABLE
     * Nullable:  true
     */
    @Column(name = "`AFTER_AVAILABLE`")
    private BigDecimal afterAvailable;

    /**
     * 原流水号
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    OLD_ACC_SEQ_NO
     * Nullable:  true
     */
    @Column(name = "`OLD_ACC_SEQ_NO`")
    private String oldAccSeqNo;

    /**
     * 状态标识位:
            第一位：冲正标识
            0-未冲正
            1-已冲正
            第二位：撤销标识
            0-未撤销
            1-已撤销
            第三位：完成标识
            0-未完成
            1-已完成
            第四位：金额解冻冻结
            0-冻结
            1-解冻
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    STATUS_MAP
     * Nullable:  true
     */
    @Column(name = "`STATUS_MAP`")
    private String statusMap;

    /**
     * 备注
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    REMARK
     * Nullable:  true
     */
    @Column(name = "`REMARK`")
    private String remark;

    /**
     * 预留金额字段1
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    RSVD_AMT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT1`")
    private BigDecimal rsvdAmt1;

    /**
     * 预留金额字段2
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    RSVD_AMT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT2`")
    private BigDecimal rsvdAmt2;

    /**
     * 预留文本字段1
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    RSVD_TEXT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT1`")
    private String rsvdText1;

    /**
     * 预留文本字段2
     *
     * Table:     T_ACC_ACCOUNT_FROZEN
     * Column:    RSVD_TEXT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT2`")
    private String rsvdText2;

    private static final long serialVersionUID = 1L;
}