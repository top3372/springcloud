package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: T_ACC_ACCOUNT_ENTRY_HIS
 */
@Table(name = "`T_ACC_ACCOUNT_ENTRY_HIS`")
@Data
public class AccAccountEntryHis implements Serializable {
    /**
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    ENTRY_SEQ_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`ENTRY_SEQ_NO`")
    private String entrySeqNo;

    /**
     * 系统跟踪号
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    SYS_TRACE_NO
     * Nullable:  false
     */
    @Column(name = "`SYS_TRACE_NO`")
    private String sysTraceNo;

    /**
     * 套号
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    SUITE_NO
     * Nullable:  false
     */
    @Column(name = "`SUITE_NO`")
    private String suiteNo;

    /**
     * 账户号
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    ACCOUNT_NO
     * Nullable:  false
     */
    @Column(name = "`ACCOUNT_NO`")
    private String accountNo;

    /**
     * 科目号
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    TITLE_NO
     * Nullable:  true
     */
    @Column(name = "`TITLE_NO`")
    private String titleNo;

    /**
     * 分录账务类型：
     * 0:表内分录
     * 1:表外分录
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    ENTRY_TYPE
     * Nullable:  true
     */
    @Column(name = "`ENTRY_TYPE`")
    private String entryType;

    /**
     * 分录标志位：
     * 第一位：自动手工标志
     * 0:自动
     * 1:手工
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    ENTRY_BIT_MAP
     * Nullable:  true
     */
    @Column(name = "`ENTRY_BIT_MAP`")
    private String entryBitMap;

    /**
     * 交易币种
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    TXN_CURRENCY
     * Nullable:  true
     */
    @Column(name = "`TXN_CURRENCY`")
    private String txnCurrency;

    /**
     * 金额
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    AMOUNT
     * Nullable:  false
     */
    @Column(name = "`AMOUNT`")
    private BigDecimal amount;

    /**
     * 交易类型：
     * 0-正常
     * 1-红字
     * 2-蓝字
     * <p>
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    TXN_TYPE
     * Nullable:  false
     */
    @Column(name = "`TXN_TYPE`")
    private String txnType;

    /**
     * 借贷方向：
     * D:借
     * C:贷
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    DIRECTION
     * Nullable:  false
     */
    @Column(name = "`DIRECTION`")
    private String direction;

    /**
     * 状态标识位：
     * 第一位：红字标识
     * 0-未红字
     * 1-已红字
     * 第二位：蓝字标识
     * 0-未蓝字
     * 1-已蓝字
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    STATUS_MAP
     * Nullable:  true
     */
    @Column(name = "`STATUS_MAP`")
    private String statusMap;

    /**
     * 记账日期
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    CLEAR_DATE
     * Nullable:  false
     */
    @Column(name = "`CLEAR_DATE`")
    private Date clearDate;

    /**
     * 创建时间
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    CREATE_TIME
     * Nullable:  false
     */
    @Column(name = "`CREATE_TIME`")
    private Date createTime;

    /**
     * 余额修改时间
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    BAL_UPDATE_TIME
     * Nullable:  true
     */
    @Column(name = "`BAL_UPDATE_TIME`")
    private Date balUpdateTime;

    /**
     * 备注
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    REMARK
     * Nullable:  true
     */
    @Column(name = "`REMARK`")
    private String remark;

    /**
     * 是否缓冲入账：
     * 0:未缓冲
     * 1:缓冲
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    IS_BUFFER
     * Nullable:  true
     */
    @Column(name = "`IS_BUFFER`")
    private String isBuffer;

    /**
     * 原分录流水号
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    OLD_ENTRY_SEQ_NO
     * Nullable:  true
     */
    @Column(name = "`OLD_ENTRY_SEQ_NO`")
    private String oldEntrySeqNo;

    /**
     * 预留金额字段1
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    RSVD_AMT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT1`")
    private BigDecimal rsvdAmt1;

    /**
     * 预留金额字段2
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    RSVD_AMT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT2`")
    private BigDecimal rsvdAmt2;

    /**
     * 预留文本字段1
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    RSVD_TEXT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT1`")
    private String rsvdText1;

    /**
     * 预留文本字段2
     * <p>
     * Table:     T_ACC_ACCOUNT_ENTRY_HIS
     * Column:    RSVD_TEXT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT2`")
    private String rsvdText2;

    private static final long serialVersionUID = 1L;
}