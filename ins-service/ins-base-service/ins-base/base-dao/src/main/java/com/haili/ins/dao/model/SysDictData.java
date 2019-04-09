package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: sys_dict_data
 */
@Table(name = "`sys_dict_data`")
@Data
public class SysDictData implements Serializable {
    /**
     * 字典编码
     *
     * Table:     sys_dict_data
     * Column:    dict_code
     * Nullable:  false
     */
    @Id
    @Column(name = "`dict_code`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dictCode;

    /**
     * 字典排序
     *
     * Table:     sys_dict_data
     * Column:    dict_sort
     * Nullable:  true
     */
    @Column(name = "`dict_sort`")
    private Integer dictSort;

    /**
     * 字典标签
     *
     * Table:     sys_dict_data
     * Column:    dict_label
     * Nullable:  true
     */
    @Column(name = "`dict_label`")
    private String dictLabel;

    /**
     * 字典键值
     *
     * Table:     sys_dict_data
     * Column:    dict_value
     * Nullable:  true
     */
    @Column(name = "`dict_value`")
    private String dictValue;

    /**
     * 字典类型
     *
     * Table:     sys_dict_data
     * Column:    dict_type
     * Nullable:  true
     */
    @Column(name = "`dict_type`")
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     *
     * Table:     sys_dict_data
     * Column:    css_class
     * Nullable:  true
     */
    @Column(name = "`css_class`")
    private String cssClass;

    /**
     * 表格回显样式
     *
     * Table:     sys_dict_data
     * Column:    list_class
     * Nullable:  true
     */
    @Column(name = "`list_class`")
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     *
     * Table:     sys_dict_data
     * Column:    is_default
     * Nullable:  true
     */
    @Column(name = "`is_default`")
    private String isDefault;

    /**
     * 状态（0正常 1停用）
     *
     * Table:     sys_dict_data
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 创建者
     *
     * Table:     sys_dict_data
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     *
     * Table:     sys_dict_data
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新者
     *
     * Table:     sys_dict_data
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * 更新时间
     *
     * Table:     sys_dict_data
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 备注
     *
     * Table:     sys_dict_data
     * Column:    remark
     * Nullable:  true
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;
}