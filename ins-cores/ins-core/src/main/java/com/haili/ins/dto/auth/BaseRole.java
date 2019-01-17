package com.haili.ins.dto.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: LeonMa
 * @date: 2019/01/16 09:00
 */
@Data
public class BaseRole implements Serializable {

    private static final long serialVersionUID = -1242268296361404360L;

    private String id;

    private String roleCode;

    private String roleName;

    private Date createDate;

    private Date updateDate;
}
