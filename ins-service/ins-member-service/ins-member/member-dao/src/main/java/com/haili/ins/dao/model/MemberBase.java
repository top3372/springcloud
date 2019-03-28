package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: t_member_base
 */
@Table(name = "`t_member_base`")
@Data
public class MemberBase implements Serializable {
    /**
     * Table:     t_member_base
     * Column:    MEMBER_ID
     * Nullable:  false
     */
    @Id
    @Column(name = "`MEMBER_ID`")
    private String memberId;

    /**
     * 用户名
     *
     * Table:     t_member_base
     * Column:    REG_ACCOUNT
     * Nullable:  true
     */
    @Column(name = "`REG_ACCOUNT`")
    private String regAccount;

    /**
     * 用户名掩码
     *
     * Table:     t_member_base
     * Column:    REG_ACCOUNT_MASK
     * Nullable:  true
     */
    @Column(name = "`REG_ACCOUNT_MASK`")
    private String regAccountMask;

    /**
     * 会员类型
     *
     * Table:     t_member_base
     * Column:    MEMBER_TYPE
     * Nullable:  true
     */
    @Column(name = "`MEMBER_TYPE`")
    private String memberType;

    /**
     * 会员等级
     *
     * Table:     t_member_base
     * Column:    MEMBER_LEVEL
     * Nullable:  true
     */
    @Column(name = "`MEMBER_LEVEL`")
    private String memberLevel;

    /**
     * 会员成长值
     *
     * Table:     t_member_base
     * Column:    MEMBER_GROW_VALUE
     * Nullable:  true
     */
    @Column(name = "`MEMBER_GROW_VALUE`")
    private String memberGrowValue;

    /**
     * 会员风险等级
     *
     * Table:     t_member_base
     * Column:    RISK_LEVEL
     * Nullable:  true
     */
    @Column(name = "`RISK_LEVEL`")
    private String riskLevel;

    /**
     * 积分
     *
     * Table:     t_member_base
     * Column:    POINT
     * Nullable:  true
     */
    @Column(name = "`POINT`")
    private String point;

    /**
     * 状态
     *
     * Table:     t_member_base
     * Column:    STATUS
     * Nullable:  true
     */
    @Column(name = "`STATUS`")
    private String status;

    /**
     * 交易状态
     *
     * Table:     t_member_base
     * Column:    TXN_STATUS
     * Nullable:  true
     */
    @Column(name = "`TXN_STATUS`")
    private String txnStatus;

    /**
     * 头像设置状态
     *
     * Table:     t_member_base
     * Column:    HEADER_FLAG
     * Nullable:  true
     */
    @Column(name = "`HEADER_FLAG`")
    private String headerFlag;

    /**
     * 是否设置过用户名
     *
     * Table:     t_member_base
     * Column:    USER_NAME_FLAG
     * Nullable:  true
     */
    @Column(name = "`USER_NAME_FLAG`")
    private String userNameFlag;

    /**
     * Table:     t_member_base
     * Column:    EMAIL
     * Nullable:  true
     */
    @Column(name = "`EMAIL`")
    private String email;

    /**
     * Table:     t_member_base
     * Column:    EMAIL_MASK
     * Nullable:  true
     */
    @Column(name = "`EMAIL_MASK`")
    private String emailMask;

    /**
     * Table:     t_member_base
     * Column:    MOBILE
     * Nullable:  true
     */
    @Column(name = "`MOBILE`")
    private String mobile;

    /**
     * Table:     t_member_base
     * Column:    MOBILE_MASK
     * Nullable:  true
     */
    @Column(name = "`MOBILE_MASK`")
    private String mobileMask;

    /**
     * Table:     t_member_base
     * Column:    NICK_NAME
     * Nullable:  true
     */
    @Column(name = "`NICK_NAME`")
    private String nickName;

    /**
     * Table:     t_member_base
     * Column:    NICK_NAME_MASK
     * Nullable:  true
     */
    @Column(name = "`NICK_NAME_MASK`")
    private String nickNameMask;

    /**
     * Table:     t_member_base
     * Column:    AUTH_STATUS
     * Nullable:  true
     */
    @Column(name = "`AUTH_STATUS`")
    private String authStatus;

    /**
     * Table:     t_member_base
     * Column:    CARD_AUTH_TYPE
     * Nullable:  true
     */
    @Column(name = "`CARD_AUTH_TYPE`")
    private String cardAuthType;

    /**
     * Table:     t_member_base
     * Column:    PAY_PWD_STATUS
     * Nullable:  true
     */
    @Column(name = "`PAY_PWD_STATUS`")
    private String payPwdStatus;

    /**
     * Table:     t_member_base
     * Column:    EMAIL_AUTH_STATUS
     * Nullable:  true
     */
    @Column(name = "`EMAIL_AUTH_STATUS`")
    private String emailAuthStatus;

    /**
     * Table:     t_member_base
     * Column:    CURRENT_CERTIFICATION_STEP
     * Nullable:  true
     */
    @Column(name = "`CURRENT_CERTIFICATION_STEP`")
    private String currentCertificationStep;

    /**
     * 营销来源
     *
     * Table:     t_member_base
     * Column:    SOURCE_CODE
     * Nullable:  true
     */
    @Column(name = "`SOURCE_CODE`")
    private String sourceCode;

    /**
     * Table:     t_member_base
     * Column:    CREATE_DATE
     * Nullable:  true
     */
    @Column(name = "`CREATE_DATE`")
    private Date createDate;

    /**
     * Table:     t_member_base
     * Column:    CREATE_BY
     * Nullable:  true
     */
    @Column(name = "`CREATE_BY`")
    private String createBy;

    /**
     * Table:     t_member_base
     * Column:    UPDATE_DATE
     * Nullable:  true
     */
    @Column(name = "`UPDATE_DATE`")
    private Date updateDate;

    /**
     * Table:     t_member_base
     * Column:    UPDATE_BY
     * Nullable:  true
     */
    @Column(name = "`UPDATE_BY`")
    private String updateBy;

    private static final long serialVersionUID = 1L;
}