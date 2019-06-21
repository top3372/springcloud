package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: t_member_invitation
 */
@Table(name = "`t_member_invitation`")
@Data
public class MemberInvitation implements Serializable {
    /**
     * Table:     t_member_invitation
     * Column:    ID
     * Nullable:  false
     */
    @Id
    @Column(name = "`ID`")
    private String id;

    /**
     * Table:     t_member_invitation
     * Column:    MEMBER_ID
     * Nullable:  true
     */
    @Column(name = "`MEMBER_ID`")
    private String memberId;

    /**
     * Table:     t_member_invitation
     * Column:    INVITATION_CODE
     * Nullable:  true
     */
    @Column(name = "`INVITATION_CODE`")
    private String invitationCode;

    /**
     * Table:     t_member_invitation
     * Column:    ALREADY_INVITED_NUM
     * Nullable:  true
     */
    @Column(name = "`ALREADY_INVITED_NUM`")
    private BigDecimal alreadyInvitedNum;

    /**
     * Table:     t_member_invitation
     * Column:    EFFECTIVE_NUM
     * Nullable:  true
     */
    @Column(name = "`EFFECTIVE_NUM`")
    private BigDecimal effectiveNum;

    /**
     * Table:     t_member_invitation
     * Column:    BONUS_AMOUNT
     * Nullable:  true
     */
    @Column(name = "`BONUS_AMOUNT`")
    private BigDecimal bonusAmount;

    /**
     * Table:     t_member_invitation
     * Column:    CREATE_DATE
     * Nullable:  true
     */
    @Column(name = "`CREATE_DATE`")
    private Date createDate;

    /**
     * Table:     t_member_invitation
     * Column:    CREATE_BY
     * Nullable:  true
     */
    @Column(name = "`CREATE_BY`")
    private String createBy;

    /**
     * Table:     t_member_invitation
     * Column:    UPDATE_DATE
     * Nullable:  true
     */
    @Column(name = "`UPDATE_DATE`")
    private Date updateDate;

    /**
     * Table:     t_member_invitation
     * Column:    UPDATE_BY
     * Nullable:  true
     */
    @Column(name = "`UPDATE_BY`")
    private String updateBy;

    private static final long serialVersionUID = 1L;
}