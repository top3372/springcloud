package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: T_TXN_PAYMENT
 */
@Table(name = "`T_TXN_PAYMENT`")
@Data
public class TxnPayment implements Serializable {
    /**
     * Table:     T_TXN_PAYMENT
     * Column:    SEQ_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`SEQ_NO`")
    private String seqNo;

    /**
     * Table:     T_TXN_PAYMENT
     * Column:    TXN_LOG_SEQ_NO
     * Nullable:  true
     */
    @Column(name = "`TXN_LOG_SEQ_NO`")
    private String txnLogSeqNo;

    /**
     * Table:     T_TXN_PAYMENT
     * Column:    ACCOUNT_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_TYPE`")
    private String accountType;

    /**
     * Table:     T_TXN_PAYMENT
     * Column:    TXN_AMOUNT
     * Nullable:  true
     */
    @Column(name = "`TXN_AMOUNT`")
    private BigDecimal txnAmount;

    private static final long serialVersionUID = 1L;
}