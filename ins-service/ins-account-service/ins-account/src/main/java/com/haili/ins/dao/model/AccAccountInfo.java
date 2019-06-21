package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: T_ACC_ACCOUNT_INFO
 */
@Table(name = "`T_ACC_ACCOUNT_INFO`")
@Data
public class AccAccountInfo implements Serializable {

    private BigDecimal tmpAvailableAmt;
    /**
     * 账户号
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`ACCOUNT_NO`")
    private String accountNo;

    /**
     * 属主账户号
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    MASTER_ACCOUNT_NO
     * Nullable:  true
     */
    @Column(name = "`MASTER_ACCOUNT_NO`")
    private String masterAccountNo;

    /**
     * 科目号
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_TITLE_NO
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_TITLE_NO`")
    private String accountTitleNo;

    /**
     * 账户名称
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_NAME
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_NAME`")
    private String accountName;

    /**
     * 开户日期
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    OPEN_DATE
     * Nullable:  true
     */
    @Column(name = "`OPEN_DATE`")
    private Date openDate;

    /**
     * 销户日期
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    CLOSE_DATE
     * Nullable:  true
     */
    @Column(name = "`CLOSE_DATE`")
    private Date closeDate;

    /**
     * 有效日期
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    VALID_DATE
     * Nullable:  true
     */
    @Column(name = "`VALID_DATE`")
    private Date validDate;

    /**
     * 锁定截止日期
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    LOCK_END_DATE
     * Nullable:  true
     */
    @Column(name = "`LOCK_END_DATE`")
    private Date lockEndDate;

    /**
     * 账户归属机构
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    OWNER_ORG
     * Nullable:  true
     */
    @Column(name = "`OWNER_ORG`")
    private String ownerOrg;

    /**
     * 账户开户机构
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    OPEN_ORG
     * Nullable:  true
     */
    @Column(name = "`OPEN_ORG`")
    private String openOrg;

    /**
     * 账户状态位图：
     * 第一位:激活状态
     * 0:未激活
     * 1:已激活
     * 第二位:锁定状态
     * 0:未锁定
     * 1:已锁定
     * 第三位:冻结状态
     * 0:未冻结
     * 1:限额冻结
     * 2:借方冻结
     * 3:贷方冻结
     * 4:双向冻结
     * 第四位:销户状态
     * 0:未销户
     * 1:已销户
     * 2:已结转长期不动户
     * <p>
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    STATUS_MAP
     * Nullable:  true
     */
    @Column(name = "`STATUS_MAP`")
    private String statusMap;

    /**
     * 账户属性：
     * 1:对公
     * 2:对私
     * 3:贷款
     * 4:清算
     * 5:待清算
     * 9:内部帐
     * <p>
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_ATTRIBUTE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_ATTRIBUTE`")
    private String accountAttribute;

    /**
     * 账户类型：
     * 11-现金账户
     * 12-积分（营销实物兑换）
     * 13-体验金（虚拟币）
     * 14-红包
     * 15-预付卡券积分-xx卡（福利卡）
     * 19-其他
     * <p>
     * MV账户类型：
     * 待结算账户
     * 结算账户
     * <p>
     * 系统内部账户类型：
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_TYPE`")
    private String accountType;

    /**
     * 账户基本类型：
     * 1:基本户
     * 2:一般户
     * 3:专用户
     * 4:临时户
     * 5:保证金户
     * 6:备付金户
     * <p>
     * <p>
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_BASE_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_BASE_TYPE`")
    private String accountBaseType;

    /**
     * 内部账户类型
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_INNER_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_INNER_TYPE`")
    private String accountInnerType;

    /**
     * 账户等级：
     * 1-普通账户
     * 9-VIP账户
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ACCOUNT_GRADE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_GRADE`")
    private String accountGrade;

    /**
     * 余额方向：
     * D:借
     * C:贷
     * 0:双向
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    BAL_DIRECTION
     * Nullable:  true
     */
    @Column(name = "`BAL_DIRECTION`")
    private String balDirection;

    /**
     * 币种代码：
     * 156-人民币
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    CURRENCY_CODE
     * Nullable:  true
     */
    @Column(name = "`CURRENCY_CODE`")
    private String currencyCode;

    /**
     * 账户余额
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    BALANCE
     * Nullable:  true
     */
    @Column(name = "`BALANCE`")
    private BigDecimal balance;

    /**
     * 可用余额
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    AVAILABLE_BALANCE
     * Nullable:  true
     */
    @Column(name = "`AVAILABLE_BALANCE`")
    private BigDecimal availableBalance;

    /**
     * 冻结金额
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    FROZEN_AMOUNT
     * Nullable:  true
     */
    @Column(name = "`FROZEN_AMOUNT`")
    private BigDecimal frozenAmount;

    /**
     * 账户关键数据密文
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    ENCRYPTED_MSG
     * Nullable:  true
     */
    @Column(name = "`ENCRYPTED_MSG`")
    private String encryptedMsg;

    /**
     * 最后更新时间
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    LAST_UPDATE_TIME
     * Nullable:  true
     */
    @Column(name = "`LAST_UPDATE_TIME`")
    private Date lastUpdateTime;

    /**
     * 最后交易时间
     * <p>
     * Table:     T_ACC_ACCOUNT_INFO
     * Column:    LAST_TXN_TIME
     * Nullable:  true
     */
    @Column(name = "`LAST_TXN_TIME`")
    private Date lastTxnTime;

    private static final long serialVersionUID = 1L;
}