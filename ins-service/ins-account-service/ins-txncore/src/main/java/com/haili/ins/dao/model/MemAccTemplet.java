package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: T_MEM_ACC_TEMPLET
 */
@Table(name = "`T_MEM_ACC_TEMPLET`")
@Data
public class MemAccTemplet implements Serializable {
    /**
     * Table:     T_MEM_ACC_TEMPLET
     * Column:    ORG_CODE
     * Nullable:  false
     */
    @Id
    @Column(name = "`ORG_CODE`")
    private String orgCode;

    /**
     * 模板属性
            会员
            100-普通会员
            
            支付机构
            200-第三方支付机构
            201-银行机构
            
            投资标
            400-高风投资标
            
            内部账户
            999-高风内部账户
            
     *
     * Table:     T_MEM_ACC_TEMPLET
     * Column:    TEMPLET_TYPE
     * Nullable:  true
     */
    @Column(name = "`TEMPLET_TYPE`")
    private String templetType;

    /**
     * 账户类型-具体见账户说明文档
            
     *
     * Table:     T_MEM_ACC_TEMPLET
     * Column:    ACC_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACC_TYPE`")
    private String accType;

    /**
     * Table:     T_MEM_ACC_TEMPLET
     * Column:    ACC_INNER_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACC_INNER_TYPE`")
    private String accInnerType;

    /**
     * 账户说明
     *
     * Table:     T_MEM_ACC_TEMPLET
     * Column:    ACC_DESP
     * Nullable:  true
     */
    @Column(name = "`ACC_DESP`")
    private String accDesp;

    private static final long serialVersionUID = 1L;
}