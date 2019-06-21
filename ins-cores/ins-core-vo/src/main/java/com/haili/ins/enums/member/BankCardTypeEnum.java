package com.haili.ins.enums.member;

/**
 * 系统交易代码枚举
 *
 * @author new
 */
public enum BankCardTypeEnum {

    _DEBIT("1", "借记卡"),

    _CREDIT("2", "信用卡"),

    _BUSSINESS("3", "对公账户"),
    ;


    private String code;
    private String des;


    BankCardTypeEnum(String code, String des) {

        this.code = code;
        this.des = des;
    }


    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }


    public static BankCardTypeEnum getBankCardTypeEnum(String value) {
        if (value != null) {
            for (BankCardTypeEnum nameEnum : values()) {
                if (nameEnum.getCode().equals(value)) {
                    return nameEnum;
                }
            }
        }
        return null;
    }


    public static boolean isBankCardTypeEnum(String value) {
        if (value != null) {
            for (BankCardTypeEnum nameEnum : values()) {
                if (nameEnum.getCode().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
}