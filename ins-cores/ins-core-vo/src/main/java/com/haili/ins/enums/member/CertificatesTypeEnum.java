package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/1 14:44
 * @Version 1.0
 * 证件类型
 */
public enum CertificatesTypeEnum {
    CERTIFICATES_TYPE_IDCARD("1", "身份证"),

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

    private CertificatesTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
