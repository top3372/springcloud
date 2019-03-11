package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: T_ACCOUNT_CUSTOM_REF
 */
@Table(name = "`T_ACCOUNT_CUSTOM_REF`")
@Data
public class AccountCustomRef implements Serializable {
    /**
     * Table:     T_ACCOUNT_CUSTOM_REF
     * Column:    SEQ_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`SEQ_NO`")
    private String seqNo;

    /**
     * Table:     T_ACCOUNT_CUSTOM_REF
     * Column:    CUSTMER_CODE
     * Nullable:  true
     */
    @Column(name = "`CUSTMER_CODE`")
    private String custmerCode;

    /**
     * Table:     T_ACCOUNT_CUSTOM_REF
     * Column:    ACCOUNT_NO
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_NO`")
    private String accountNo;

    /**
     * 账户类型：
            1-现金账户
            2-体验金账户
            3-红包账户
            4-积分账户
            9-其他
            
     *
     * Table:     T_ACCOUNT_CUSTOM_REF
     * Column:    ACCOUNT_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_TYPE`")
    private String accountType;

    /**
     * 绑定标志
            0-解绑
            1-绑定
     *
     * Table:     T_ACCOUNT_CUSTOM_REF
     * Column:    IS_BINDING
     * Nullable:  true
     */
    @Column(name = "`IS_BINDING`")
    private String isBinding;

    private static final long serialVersionUID = 1L;
}