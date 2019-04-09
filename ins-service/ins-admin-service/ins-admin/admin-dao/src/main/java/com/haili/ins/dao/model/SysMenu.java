package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: sys_menu
 */
@Table(name = "`sys_menu`")
@Data
public class SysMenu implements Serializable {
    /**
     * 主键
     *
     * Table:     sys_menu
     * Column:    menu_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`menu_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String menuId;

    /**
     * Table:     sys_menu
     * Column:    menu_name
     * Nullable:  true
     */
    @Column(name = "`menu_name`")
    private String menuName;

    /**
     * Table:     sys_menu
     * Column:    menu_url
     * Nullable:  true
     */
    @Column(name = "`menu_url`")
    private String menuUrl;

    /**
     * Table:     sys_menu
     * Column:    parent_id
     * Nullable:  true
     */
    @Column(name = "`parent_id`")
    private String parentId;

    /**
     * Table:     sys_menu
     * Column:    order_num
     * Nullable:  true
     */
    @Column(name = "`order_num`")
    private Integer orderNum;

    /**
     * 状态
0：禁用
1：启用
     *
     * Table:     sys_menu
     * Column:    status
     * Nullable:  true
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 是否删除
0:未删除
1:已删除
     *
     * Table:     sys_menu
     * Column:    is_del
     * Nullable:  true
     */
    @Column(name = "`is_del`")
    private String isDel;

    private static final long serialVersionUID = 1L;
}