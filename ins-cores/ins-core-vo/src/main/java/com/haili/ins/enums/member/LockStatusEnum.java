package com.haili.ins.enums.member;

public enum LockStatusEnum {


    STATUS_NORMAL("00", "正常"),
    STATUS_SYSTEM_LOCK("12", "系统自动锁定"),
    STATUS_PERSON_LOCK("13", "人工锁定"),

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

    private LockStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
