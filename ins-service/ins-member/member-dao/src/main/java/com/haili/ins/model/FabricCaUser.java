package com.haili.ins.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: fabric_ca_user
 */
@Table(name = "`fabric_ca_user`")
@Data
public class FabricCaUser implements Serializable {
    /**
     * Table:     fabric_ca_user
     * Column:    id
     * Nullable:  false
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     *
     * Table:     fabric_ca_user
     * Column:    name
     * Nullable:  true
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限
     *
     * Table:     fabric_ca_user
     * Column:    roles
     * Nullable:  true
     */
    @Column(name = "`roles`")
    private String roles;

    /**
     * Table:     fabric_ca_user
     * Column:    account
     * Nullable:  true
     */
    @Column(name = "`account`")
    private String account;

    /**
     * Table:     fabric_ca_user
     * Column:    affiliation
     * Nullable:  true
     */
    @Column(name = "`affiliation`")
    private String affiliation;

    /**
     * 所属机构
     *
     * Table:     fabric_ca_user
     * Column:    organization
     * Nullable:  true
     */
    @Column(name = "`organization`")
    private String organization;

    /**
     * Table:     fabric_ca_user
     * Column:    enrollmentSecret
     * Nullable:  true
     */
    @Column(name = "`enrollmentSecret`")
    private String enrollmentsecret;

    /**
     * Table:     fabric_ca_user
     * Column:    msp_Id
     * Nullable:  true
     */
    @Column(name = "`msp_Id`")
    private String mspId;

    /**
     * Table:     fabric_ca_user
     * Column:    exp_date
     * Nullable:  true
     */
    @Column(name = "`exp_date`")
    private Date expDate;

    /**
     * Table:     fabric_ca_user
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * Table:     fabric_ca_user
     * Column:    create_date
     * Nullable:  true
     */
    @Column(name = "`create_date`")
    private Date createDate;

    /**
     * Table:     fabric_ca_user
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * Table:     fabric_ca_user
     * Column:    update_date
     * Nullable:  true
     */
    @Column(name = "`update_date`")
    private Date updateDate;

    /**
     * 证书
     *
     * Table:     fabric_ca_user
     * Column:    enrollment
     * Nullable:  true
     */
    @Column(name = "`enrollment`")
    private String enrollment;

    private static final long serialVersionUID = 1L;
}