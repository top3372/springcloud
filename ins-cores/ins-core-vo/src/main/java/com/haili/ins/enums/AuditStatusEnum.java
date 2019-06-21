package com.haili.ins.enums;

public enum AuditStatusEnum {


    AUDIT_WAIT("0", "待审核"),
    AUDIT_SUCCESS("1", "审核通过"),
    AUDIT_REFUSE("2", "审核拒绝"),

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

    private AuditStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
