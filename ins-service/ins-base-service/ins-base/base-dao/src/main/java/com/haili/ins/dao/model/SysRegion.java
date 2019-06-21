package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * Table: sys_region
 */
@Table(name = "`sys_region`")
@Data
public class SysRegion implements Serializable {
    /**
     * 地区id
     * <p>
     * Table:     sys_region
     * Column:    region_code
     * Nullable:  false
     */
    @Id
    @Column(name = "`region_code`")
    private String regionCode;

    /**
     * 名称
     * <p>
     * Table:     sys_region
     * Column:    region_name
     * Nullable:  false
     */
    @Column(name = "`region_name`")
    private String regionName;

    /**
     * 上级id
     * <p>
     * Table:     sys_region
     * Column:    parent_region_code
     * Nullable:  false
     */
    @Column(name = "`parent_region_code`")
    private String parentRegionCode;

    /**
     * 排序代码
     * <p>
     * Table:     sys_region
     * Column:    disorder
     * Nullable:  true
     */
    @Column(name = "`disorder`")
    private Integer disorder;

    private static final long serialVersionUID = 1L;
}