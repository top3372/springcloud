package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: sys_post
 */
@Table(name = "`sys_post`")
@Data
public class SysPost implements Serializable {
    /**
     * 岗位ID
     * <p>
     * Table:     sys_post
     * Column:    post_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`post_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    /**
     * 岗位编码
     * <p>
     * Table:     sys_post
     * Column:    post_code
     * Nullable:  false
     */
    @Column(name = "`post_code`")
    private String postCode;

    /**
     * 岗位名称
     * <p>
     * Table:     sys_post
     * Column:    post_name
     * Nullable:  false
     */
    @Column(name = "`post_name`")
    private String postName;

    /**
     * 显示顺序
     * <p>
     * Table:     sys_post
     * Column:    post_sort
     * Nullable:  false
     */
    @Column(name = "`post_sort`")
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     * <p>
     * Table:     sys_post
     * Column:    status
     * Nullable:  false
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 创建者
     * <p>
     * Table:     sys_post
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     * <p>
     * Table:     sys_post
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新者
     * <p>
     * Table:     sys_post
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * 更新时间
     * <p>
     * Table:     sys_post
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 备注
     * <p>
     * Table:     sys_post
     * Column:    remark
     * Nullable:  true
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;
}