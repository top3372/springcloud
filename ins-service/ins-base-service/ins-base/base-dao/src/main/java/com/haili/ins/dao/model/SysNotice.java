package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: sys_notice
 */
@Table(name = "`sys_notice`")
@Data
public class SysNotice implements Serializable {
    /**
     * 公告ID
     * <p>
     * Table:     sys_notice
     * Column:    notice_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`notice_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;

    /**
     * 公告标题
     * <p>
     * Table:     sys_notice
     * Column:    notice_title
     * Nullable:  false
     */
    @Column(name = "`notice_title`")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     * <p>
     * Table:     sys_notice
     * Column:    notice_type
     * Nullable:  false
     */
    @Column(name = "`notice_type`")
    private String noticeType;

    /**
     * 公告内容
     * <p>
     * Table:     sys_notice
     * Column:    notice_content
     * Nullable:  true
     */
    @Column(name = "`notice_content`")
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     * <p>
     * Table:     sys_notice
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 创建者
     * <p>
     * Table:     sys_notice
     * Column:    create_by
     * Nullable:  true
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     * <p>
     * Table:     sys_notice
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新者
     * <p>
     * Table:     sys_notice
     * Column:    update_by
     * Nullable:  true
     */
    @Column(name = "`update_by`")
    private String updateBy;

    /**
     * 更新时间
     * <p>
     * Table:     sys_notice
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 备注
     * <p>
     * Table:     sys_notice
     * Column:    remark
     * Nullable:  true
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;
}