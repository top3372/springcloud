/**
 * File: AccountEntryStatusEnum.java
 * Description:
 * Copyright 2015 GAOEX Corporation. All rights reserved.
 * Date      Author      Changes
 * MAY 25, 2015   Jack     Create
 */
package com.haili.ins.enums;


/**
 *
 */
public enum AccountTypeEnum {

    ONLINE_ACCOUNT("1", "联机账户"),
    OFFLINE_ACCOUNT("2", "脱机账户"),
    VOUCHERS_ACCOUNT("3", "代金券账户"),
    POINTS_ACCOUNT("4", "积分账户"),
    OTHER_ACCOUNT("9", "其他");

    private AccountTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

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

    public static String getDesc(String code) {

        for (AccountTypeEnum accountTypeEnum : values()) {
            if (accountTypeEnum.getCode().equals(code)) {
                return accountTypeEnum.getDesc();
            }

        }
        return null;
    }

}
