package com.haili.ins.enums.member;

public enum MemberLevelEnum {

    MEMBER_LEVEL_0("0", "0级会员"),

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

    private MemberLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
