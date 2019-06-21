package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: sys_dict_type
 */
@Table(name = "`sys_dict_type`")
@Data
public class SysDictType implements Serializable {
    /**
     * 字典主键
     * <p>
     * Table:     sys_dict_type
     * Column:    dict_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`dict_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dictId;

    /**
     * 字典名称
     * <p>
     * Table:     sys_dict_type
     * Column:    dict_name
     * Nullable:  true
     */
    @Column(name = "`dict_name`")
    private String dictName;

    /**
     * 字典类型
     * <p>
     * Table:     sys_dict_type
     * Column:    dict_type
     * Nullable:  true
     */
    @Column(name = "`dict_type`")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     * <p>
     * Table:     sys_dict_type
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 创建者
     * <p>
     * Table:     sys_dict_type
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     * <p>
     * Table:     sys_dict_type
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新者
     * <p>
     * Table:     sys_dict_type
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * 更新时间
     * <p>
     * Table:     sys_dict_type
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 备注
     * <p>
     * Table:     sys_dict_type
     * Column:    remark
     * Nullable:  true
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;
}