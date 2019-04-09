package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Table(name = "`sys_role_menu`")
@Data
public class SysRoleMenu implements Serializable {
    /**
     * Table:     sys_role_menu
     * Column:    role_id
     * Nullable:  true
     */
    @Id
    @Column(name = "`role_id`")
    private String roleId;

    /**
     * Table:     sys_role_menu
     * Column:    menu_id
     * Nullable:  true
     */
    @Id
    @Column(name = "`menu_id`")
    private String menuId;

    private static final long serialVersionUID = 1L;
}