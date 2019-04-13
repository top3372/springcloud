package com.haili.ins.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: LeonMa
 * @date: 2019/01/16 08:36
 */
@Data
public class Oauth2User implements Serializable {


    private static final long serialVersionUID = 7637697184571624871L;
    private String id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String status;

    private String name;

    private String type;

    private Date createDate;

    private Date updateDate;
}
