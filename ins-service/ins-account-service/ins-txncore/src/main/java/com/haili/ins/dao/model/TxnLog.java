package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: T_TXN_LOG
 */
@Table(name = "`T_TXN_LOG`")
@Data
public class TxnLog implements Serializable {
    /**
     * Table:     T_TXN_LOG
     * Column:    TXN_SEQ_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`TXN_SEQ_NO`")
    private String txnSeqNo;

    /**
     * 系统跟踪号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    SYS_TRACE_NO
     * Nullable:  false
     */
    @Column(name = "`SYS_TRACE_NO`")
    private String sysTraceNo;

    /**
     * 交易开始时间
     * <p>
     * Table:     T_TXN_LOG
     * Column:    TXN_TIME_FROM
     * Nullable:  true
     */
    @Column(name = "`TXN_TIME_FROM`")
    private Date txnTimeFrom;

    /**
     * 交易完成时间
     * <p>
     * Table:     T_TXN_LOG
     * Column:    TXN_TIME_TO
     * Nullable:  true
     */
    @Column(name = "`TXN_TIME_TO`")
    private Date txnTimeTo;

    /**
     * 服务号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    SERVICE_CODE
     * Nullable:  true
     */
    @Column(name = "`SERVICE_CODE`")
    private String serviceCode;

    /**
     * 0-处理中
     * 1-成功
     * 2-失败
     * 3-滞留
     * 4-超时
     * <p>
     * Table:     T_TXN_LOG
     * Column:    TXN_STATUS
     * Nullable:  true
     */
    @Column(name = "`TXN_STATUS`")
    private String txnStatus;

    /**
     * 交易号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    TXN_CODE
     * Nullable:  true
     */
    @Column(name = "`TXN_CODE`")
    private String txnCode;

    /**
     * 返回码
     * <p>
     * Table:     T_TXN_LOG
     * Column:    RESP_CODE
     * Nullable:  true
     */
    @Column(name = "`RESP_CODE`")
    private String respCode;

    /**
     * 业务流水号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    BUS_SEQ_NO
     * Nullable:  true
     */
    @Column(name = "`BUS_SEQ_NO`")
    private String busSeqNo;

    /**
     * 转出会员号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    OUT_CUSTMER_CODE
     * Nullable:  true
     */
    @Column(name = "`OUT_CUSTMER_CODE`")
    private String outCustmerCode;

    /**
     * 转入会员号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    IN_CUSTMER_CODE
     * Nullable:  true
     */
    @Column(name = "`IN_CUSTMER_CODE`")
    private String inCustmerCode;

    /**
     * 机构号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    ORG_CODE
     * Nullable:  true
     */
    @Column(name = "`ORG_CODE`")
    private String orgCode;

    /**
     * 支付机构号-支付渠道
     * <p>
     * Table:     T_TXN_LOG
     * Column:    PAY_ORG_CODE
     * Nullable:  true
     */
    @Column(name = "`PAY_ORG_CODE`")
    private String payOrgCode;

    /**
     * 支付机构流水号
     * <p>
     * Table:     T_TXN_LOG
     * Column:    PAY_ORG_SEQ_NO
     * Nullable:  true
     */
    @Column(name = "`PAY_ORG_SEQ_NO`")
    private String payOrgSeqNo;

    /**
     * 订单金额
     * <p>
     * Table:     T_TXN_LOG
     * Column:    ORDER_AMOUNT
     * Nullable:  true
     */
    @Column(name = "`ORDER_AMOUNT`")
    private BigDecimal orderAmount;

    /**
     * 交易金额
     * <p>
     * Table:     T_TXN_LOG
     * Column:    TXN_AMOUNT
     * Nullable:  true
     */
    @Column(name = "`TXN_AMOUNT`")
    private BigDecimal txnAmount;

    /**
     * 156-人民币
     * <p>
     * Table:     T_TXN_LOG
     * Column:    CURRENCY_CODE
     * Nullable:  true
     */
    @Column(name = "`CURRENCY_CODE`")
    private String currencyCode;

    /**
     * 手续费
     * <p>
     * Table:     T_TXN_LOG
     * Column:    FEE_AMT
     * Nullable:  true
     */
    @Column(name = "`FEE_AMT`")
    private BigDecimal feeAmt;

    /**
     * 分润费
     * <p>
     * Table:     T_TXN_LOG
     * Column:    PROFIT_AMT
     * Nullable:  true
     */
    @Column(name = "`PROFIT_AMT`")
    private BigDecimal profitAmt;

    /**
     * 利息
     * <p>
     * Table:     T_TXN_LOG
     * Column:    INTEREST_AMT
     * Nullable:  true
     */
    @Column(name = "`INTEREST_AMT`")
    private BigDecimal interestAmt;

    /**
     * 溢价
     * <p>
     * Table:     T_TXN_LOG
     * Column:    PREMIUM_AMT
     * Nullable:  true
     */
    @Column(name = "`PREMIUM_AMT`")
    private BigDecimal premiumAmt;

    /**
     * 1-参与
     * 0-不参与
     * <p>
     * Table:     T_TXN_LOG
     * Column:    IS_CLEARING
     * Nullable:  true
     */
    @Column(name = "`IS_CLEARING`")
    private String isClearing;

    /**
     * Table:     T_TXN_LOG
     * Column:    RSVD_AMT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT1`")
    private BigDecimal rsvdAmt1;

    /**
     * Table:     T_TXN_LOG
     * Column:    RSVD_AMT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_AMT2`")
    private BigDecimal rsvdAmt2;

    /**
     * Table:     T_TXN_LOG
     * Column:    RSVD_TEXT1
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT1`")
    private String rsvdText1;

    /**
     * Table:     T_TXN_LOG
     * Column:    RSVD_TEXT2
     * Nullable:  true
     */
    @Column(name = "`RSVD_TEXT2`")
    private String rsvdText2;

    private static final long serialVersionUID = 1L;
}