package com.haili.ins.enums.member;

public enum FrozenTypeEnum {

    MEMBER_FROZEN("00", "会员冻结"),
    LOGIN_PWD_FROZEN("01", "登录密码冻结"),
    PAY_PWD_FROZEN("02", "交易密码冻结"),
    MEMBER_UNFROZEN("10", "会员解冻"),
    LOGIN_PWD_UNFROZEN("11", "登录密码解冻"),
    PAY_PWD_UNFROZEN("12", "交易密码解冻"),
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

    FrozenTypeEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FrozenTypeEnum getFrozenTypeEnum(String value) {
        for (FrozenTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isFrozenTypeEnum(String value) {
        for (FrozenTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
