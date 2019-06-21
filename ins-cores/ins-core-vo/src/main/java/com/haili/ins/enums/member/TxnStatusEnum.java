package com.haili.ins.enums.member;

public enum TxnStatusEnum {


    TXN_STATUS_NORMAL("00", "交易正常"),
    TXN_STATUS_LOCK("01", "交易冻结"),

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

    private TxnStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
