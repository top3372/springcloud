package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/1 13:59
 * @Version 1.0
 */
public enum BuyFlagEnum {

    NO_PURCHASE("0", "未购买"),

    PURCHASE("1", "已购买"),
    ;
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

    BuyFlagEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static BuyFlagEnum getBuyFlagEnum(String value) {
        for (BuyFlagEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isBuyFlagEnum(String value) {
        for (BuyFlagEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
