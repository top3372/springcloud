package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: T_ACCOUNT_ENTRY
 */
@Table(name = "`T_ACCOUNT_ENTRY`")
@Data
public class AccountEntry implements Serializable {
    /**
     * Table:     T_ACCOUNT_ENTRY
     * Column:    SEQ_NO
     * Nullable:  true
     */
    @Column(name = "`SEQ_NO`")
    private String seqNo;

    /**
     * Table:     T_ACCOUNT_ENTRY
     * Column:    SERVICE_CODE
     * Nullable:  true
     */
    @Column(name = "`SERVICE_CODE`")
    private String serviceCode;

    /**
     * 交易代码
     * 交易代码
     * <p>
     * Table:     T_ACCOUNT_ENTRY
     * Column:    TXN_CODE
     * Nullable:  false
     */
    @Column(name = "`TXN_CODE`")
    private String txnCode;

    /**
     * Table:     T_ACCOUNT_ENTRY
     * Column:    DEAL_CODE
     * Nullable:  true
     */
    @Column(name = "`DEAL_CODE`")
    private String dealCode;

    /**
     * 交易类型
     * 0-管理类交易
     * 1-交易类交易
     * 2-授权类交易
     * 3-清算类交易
     * <p>
     * Table:     T_ACCOUNT_ENTRY
     * Column:    TXN_TYPE
     * Nullable:  true
     */
    @Column(name = "`TXN_TYPE`")
    private String txnType;

    /**
     * 交易名称
     * 交易名称
     * <p>
     * Table:     T_ACCOUNT_ENTRY
     * Column:    TXN_NAME
     * Nullable:  true
     */
    @Column(name = "`TXN_NAME`")
    private String txnName;

    /**
     * 交易摘要
     * 交易摘要
     * <p>
     * Table:     T_ACCOUNT_ENTRY
     * Column:    TXN_ABSTRACT
     * Nullable:  true
     */
    @Column(name = "`TXN_ABSTRACT`")
    private String txnAbstract;

    /**
     * 付款方借贷标志
     * D-借
     * C-贷
     * <p>
     * Table:     T_ACCOUNT_ENTRY
     * Column:    PAYER_DC_FLAG
     * Nullable:  true
     */
    @Column(name = "`PAYER_DC_FLAG`")
    private String payerDcFlag;

    /**
     * 收款方借贷标志
     * D-借
     * C-贷
     * <p>
     * Table:     T_ACCOUNT_ENTRY
     * Column:    PAYEE_DC_FLAG
     * Nullable:  true
     */
    @Column(name = "`PAYEE_DC_FLAG`")
    private String payeeDcFlag;

    /**
     * 是否参与清算
     * 0-不参与
     * 1-参与
     * <p>
     * Table:     T_ACCOUNT_ENTRY
     * Column:    CLEARING_FLAG
     * Nullable:  true
     */
    @Column(name = "`CLEARING_FLAG`")
    private String clearingFlag;

    private static final long serialVersionUID = 1L;
}