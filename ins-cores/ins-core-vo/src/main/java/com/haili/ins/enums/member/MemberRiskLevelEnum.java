package com.haili.ins.enums.member;

public enum MemberRiskLevelEnum {

    RISK_LEVEL_0("0", "0级风险"),

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

    private MemberRiskLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
