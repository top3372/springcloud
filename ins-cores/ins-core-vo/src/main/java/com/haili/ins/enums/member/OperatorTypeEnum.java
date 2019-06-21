package com.haili.ins.enums.member;

public enum OperatorTypeEnum {

    SYSTEM("S", "系统操作"),
    PERSON("A", "人工操作"),


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

    OperatorTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}