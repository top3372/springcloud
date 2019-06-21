package com.haili.ins.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: sys_oper_log
 */
@Table(name = "`sys_oper_log`")
@Data
public class SysOperLog implements Serializable {
    /**
     * 日志主键
     * <p>
     * Table:     sys_oper_log
     * Column:    oper_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`oper_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operId;

    /**
     * 模块标题
     * <p>
     * Table:     sys_oper_log
     * Column:    title
     * Nullable:  true
     */
    @Column(name = "`title`")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     * <p>
     * Table:     sys_oper_log
     * Column:    business_type
     * Nullable:  true
     */
    @Column(name = "`business_type`")
    private Integer businessType;

    /**
     * 方法名称
     * <p>
     * Table:     sys_oper_log
     * Column:    method
     * Nullable:  true
     */
    @Column(name = "`method`")
    private String method;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     * <p>
     * Table:     sys_oper_log
     * Column:    operator_type
     * Nullable:  true
     */
    @Column(name = "`operator_type`")
    private Integer operatorType;

    /**
     * 操作人员
     * <p>
     * Table:     sys_oper_log
     * Column:    oper_name
     * Nullable:  true
     */
    @Column(name = "`oper_name`")
    private String operName;

    /**
     * 部门名称
     * <p>
     * Table:     sys_oper_log
     * Column:    dept_name
     * Nullable:  true
     */
    @Column(name = "`dept_name`")
    private String deptName;

    /**
     * 请求URL
     * <p>
     * Table:     sys_oper_log
     * Column:    oper_url
     * Nullable:  true
     */
    @Column(name = "`oper_url`")
    private String operUrl;

    /**
     * 主机地址
     * <p>
     * Table:     sys_oper_log
     * Column:    oper_ip
     * Nullable:  true
     */
    @Column(name = "`oper_ip`")
    private String operIp;

    /**
     * 操作地点
     * <p>
     * Table:     sys_oper_log
     * Column:    oper_location
     * Nullable:  true
     */
    @Column(name = "`oper_location`")
    private String operLocation;

    /**
     * 请求参数
     * <p>
     * Table:     sys_oper_log
     * Column:    oper_param
     * Nullable:  true
     */
    @Column(name = "`oper_param`")
    private String operParam;

    /**
     * 操作状态（0正常 1异常）
     * <p>
     * Table:     sys_oper_log
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 错误消息
     * <p>
     * Table:     sys_oper_log
     * Column:    error_msg
     * Nullable:  true
     */
    @Column(name = "`error_msg`")
    private String errorMsg;

    /**
     * 操作时间
     * <p>
     * Table:     sys_oper_log
     * Column:    oper_time
     * Nullable:  true
     */
    @Column(name = "`oper_time`")
    private Date operTime;

    private static final long serialVersionUID = 1L;
}