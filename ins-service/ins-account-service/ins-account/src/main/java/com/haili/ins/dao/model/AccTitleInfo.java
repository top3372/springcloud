package com.haili.ins.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: T_ACC_TITLE_INFO
 */
@Table(name = "`T_ACC_TITLE_INFO`")
@Data
public class AccTitleInfo implements Serializable {
    /**
     * 记录号
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    RECORD_NO
     * Nullable:  false
     */
    @Id
    @Column(name = "`RECORD_NO`")
    private String recordNo;

    /**
     * 科目号
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_NO
     * Nullable:  true
     */
    @Column(name = "`TITLE_NO`")
    private String titleNo;

    /**
     * 汇总科目号
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    SUM_TITLE_NO
     * Nullable:  true
     */
    @Column(name = "`SUM_TITLE_NO`")
    private String sumTitleNo;

    /**
     * 科目名称:
            1:对公
            2:对私
            9:内部
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_NAME
     * Nullable:  true
     */
    @Column(name = "`TITLE_NAME`")
    private String titleName;

    /**
     * 科目级别:
            0:汇总科目
            1:一级科目
            2:二级科目
            3:三级科目
            9:明细科目
            
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    GRADE
     * Nullable:  true
     */
    @Column(name = "`GRADE`")
    private String grade;

    /**
     * 所属一级科目
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_CLASS_A
     * Nullable:  true
     */
    @Column(name = "`TITLE_CLASS_A`")
    private String titleClassA;

    /**
     * 所属一级科目号
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_CLASS_A_CODE
     * Nullable:  true
     */
    @Column(name = "`TITLE_CLASS_A_CODE`")
    private String titleClassACode;

    /**
     * 所属二级科目
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_CLASS_B
     * Nullable:  true
     */
    @Column(name = "`TITLE_CLASS_B`")
    private String titleClassB;

    /**
     * 所属二级科目号
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_CLASS_B_CODE
     * Nullable:  true
     */
    @Column(name = "`TITLE_CLASS_B_CODE`")
    private String titleClassBCode;

    /**
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_CLASS_C
     * Nullable:  true
     */
    @Column(name = "`TITLE_CLASS_C`")
    private String titleClassC;

    /**
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_CLASS_C_CODE
     * Nullable:  true
     */
    @Column(name = "`TITLE_CLASS_C_CODE`")
    private String titleClassCCode;

    /**
     * 币种代码:
            156:人民币
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    CURRENCY_CODE
     * Nullable:  true
     */
    @Column(name = "`CURRENCY_CODE`")
    private String currencyCode;

    /**
     * 借贷标识:
            D:借
            C:贷
            0:双向
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    DIRECTION
     * Nullable:  true
     */
    @Column(name = "`DIRECTION`")
    private String direction;

    /**
     * 单多账户标识:
            0:汇总科目 (不开户)
            1:单帐户科目
            2:多帐户科目
            
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    ACCOUNT_NUM_FLAG
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_NUM_FLAG`")
    private String accountNumFlag;

    /**
     * 科目属性:
            1:对公
            2:对私
            3:贷款
            4:清算
            5:待清算
            9:内部帐
            
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_ATTRIBUTE
     * Nullable:  true
     */
    @Column(name = "`TITLE_ATTRIBUTE`")
    private String titleAttribute;

    /**
     * 科目类型:
            1:资产
            2:负债
            3:共同
            4:所有者权益
            5:损益
            
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    TITLE_TYPE
     * Nullable:  true
     */
    @Column(name = "`TITLE_TYPE`")
    private String titleType;

    /**
     * 内部账户类型
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    ACCOUNT_INNER_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_INNER_TYPE`")
    private String accountInnerType;

    /**
     * 科目账户基本类型:
            1:基本户
            2:一般户
            3:专用户
            4:临时户
            5:保证金户
            6:备付金户
            
            
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    ACCOUNT_BASE_TYPE
     * Nullable:  true
     */
    @Column(name = "`ACCOUNT_BASE_TYPE`")
    private String accountBaseType;

    /**
     * 状态:
            A-新建待审核
            B-更新待审核
            C-停用待审核
            Z-注销待审核
            1-正常
            2-停用
            9-注销
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    STATUS
     * Nullable:  true
     */
    @Column(name = "`STATUS`")
    private String status;

    /**
     * 录入人
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    CREATE_UID
     * Nullable:  true
     */
    @Column(name = "`CREATE_UID`")
    private String createUid;

    /**
     * 录入时间
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    CREATE_TIME
     * Nullable:  true
     */
    @Column(name = "`CREATE_TIME`")
    private Date createTime;

    /**
     * 审核人
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    CHECK_UID
     * Nullable:  true
     */
    @Column(name = "`CHECK_UID`")
    private String checkUid;

    /**
     * 审核时间
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    CHECK_TIME
     * Nullable:  true
     */
    @Column(name = "`CHECK_TIME`")
    private Date checkTime;

    /**
     * 最后修改人
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    LAST_MODIFY_UID
     * Nullable:  true
     */
    @Column(name = "`LAST_MODIFY_UID`")
    private String lastModifyUid;

    /**
     * 最后修改时间
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    LAST_MODIFY_TIME
     * Nullable:  true
     */
    @Column(name = "`LAST_MODIFY_TIME`")
    private Date lastModifyTime;

    /**
     * 归档标志:
            1-未归档
            2-已归档
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    ARCHIVE_FLAG
     * Nullable:  true
     */
    @Column(name = "`ARCHIVE_FLAG`")
    private String archiveFlag;

    /**
     * 归档人
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    ARCHIVE_UID
     * Nullable:  true
     */
    @Column(name = "`ARCHIVE_UID`")
    private String archiveUid;

    /**
     * 归档时间
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    ARCHIVE_TIME
     * Nullable:  true
     */
    @Column(name = "`ARCHIVE_TIME`")
    private Date archiveTime;

    /**
     * 原记录编号
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    L_RECORD_NO
     * Nullable:  true
     */
    @Column(name = "`L_RECORD_NO`")
    private BigDecimal lRecordNo;

    /**
     * 备注
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    REMARK
     * Nullable:  true
     */
    @Column(name = "`REMARK`")
    private String remark;

    /**
     * 保留域1
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    RESV_FLD1
     * Nullable:  true
     */
    @Column(name = "`RESV_FLD1`")
    private String resvFld1;

    /**
     * 保留域2
     *
     * Table:     T_ACC_TITLE_INFO
     * Column:    RESV_FLD2
     * Nullable:  true
     */
    @Column(name = "`RESV_FLD2`")
    private String resvFld2;

    private static final long serialVersionUID = 1L;
}