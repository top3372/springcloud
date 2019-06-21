/**
 * File: AccountMngTxnEnum.java
 * Description:
 * Copyright 2015 GAOEX Corporation. All rights reserved.
 * Date      Author      Changes
 * MAY 25, 2015   Jack     Create
 */
package com.haili.ins.enums;

/**
 * 账户管理的交易类型的几种枚举
 */
public enum AccountMngTxnEnum {
    /**
     *  1:开户
     2:销户
     3:账户冻结
     4:账户解冻
     5:锁定
     6:解锁
     */
    ACCOUNT_MNG_OPEN("1", "开户"),
    ACCOUNT_MNG_CANCEL("2", "销户"),
    ACCOUNT_MNG_FROZEN("3", "账户冻结"),
    ACCOUNT_MNG_UNFROZEN("4", "账户解冻"),
    ACCOUNT_MNG_LOCK("5", "锁定"),
    ACCOUNT_MNG_UNLOCK("6", "解锁");
    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private AccountMngTxnEnum() {
    }

    private AccountMngTxnEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
