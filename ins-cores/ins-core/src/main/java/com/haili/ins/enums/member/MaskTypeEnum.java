package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/4 13:25
 * @Version 1.0
 */
public enum  MaskTypeEnum {

    MASK_TYPE_BANKNO("1","掩码类型 银行卡号"),
    MASK_TYPE_IDNO("2","掩码类型 身份证号"),


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

    private MaskTypeEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
