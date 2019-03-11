package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: T_ACCOUNT_ORG_REF
 */
@Table(name = "`T_ACCOUNT_ORG_REF`")
@Data
public class AccountOrgRef implements Serializable {
    /**
     * Table:     T_ACCOUNT_ORG_REF
     * Column:    SEQ_NO
     * Nullable:  false
     */
    @Column(name = "`SEQ_NO`")
    private String seqNo;

    /**
     * Table:     T_ACCOUNT_ORG_REF
     * Column:    ORG_CODE
     * Nullable:  true
     */
    @Column(name = "`ORG_CODE`")
    private String orgCode;

    /**
     * Table:     T_ACCOUNT_ORG_REF
     * Column:    ACCOUNT_NO
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_NO`")
    private String accountNo;

    /**
     * 账户类型：
            
     *
     * Table:     T_ACCOUNT_ORG_REF
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
     * Table:     T_ACCOUNT_ORG_REF
     * Column:    IS_BINDING
     * Nullable:  true
     */
    @Column(name = "`IS_BINDING`")
    private String isBinding;

    private static final long serialVersionUID = 1L;
}