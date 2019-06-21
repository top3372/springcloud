package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: sys_login_log
 */
@Table(name = "`sys_login_log`")
@Data
public class SysLoginLog implements Serializable {
    /**
     * 访问ID
     * <p>
     * Table:     sys_login_log
     * Column:    info_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`info_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    /**
     * 登录账号
     * <p>
     * Table:     sys_login_log
     * Column:    login_name
     * Nullable:  true
     */
    @Column(name = "`login_name`")
    private String loginName;

    /**
     * 登录IP地址
     * <p>
     * Table:     sys_login_log
     * Column:    ipaddr
     * Nullable:  true
     */
    @Column(name = "`ipaddr`")
    private String ipaddr;

    /**
     * 登录地点
     * <p>
     * Table:     sys_login_log
     * Column:    login_location
     * Nullable:  true
     */
    @Column(name = "`login_location`")
    private String loginLocation;

    /**
     * 浏览器类型
     * <p>
     * Table:     sys_login_log
     * Column:    browser
     * Nullable:  true
     */
    @Column(name = "`browser`")
    private String browser;

    /**
     * 操作系统
     * <p>
     * Table:     sys_login_log
     * Column:    os
     * Nullable:  true
     */
    @Column(name = "`os`")
    private String os;

    /**
     * 登录状态（0成功 1失败）
     * <p>
     * Table:     sys_login_log
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 提示消息
     * <p>
     * Table:     sys_login_log
     * Column:    msg
     * Nullable:  true
     */
    @Column(name = "`msg`")
    private String msg;

    /**
     * 访问时间
     * <p>
     * Table:     sys_login_log
     * Column:    login_time
     * Nullable:  true
     */
    @Column(name = "`login_time`")
    private Date loginTime;

    private static final long serialVersionUID = 1L;
}