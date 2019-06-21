package com.haili.ins.enums.member;

public enum LoginModeEnum {


    LOGIN_BY_PWD("1", "密码登录"),
    LOGIN_BY_PHONE_CODE("2", "验证码登录"),
    LOGIN_BY_THIRD_ORG_UNION("3", "第三方机构联合登录"),
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

    private LoginModeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}