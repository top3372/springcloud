package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/1 14:41
 * @Version 1.0
 */
public enum IdentityAuthTypeEnum {
    IDENTITY("1", "实名认证"),
    BANK_VERIFY("2", "银行卡认证"),
    OCR_VERIFY("3", "人脸识别认证"),

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

    IdentityAuthTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IdentityAuthTypeEnum getIdentityAuthTypeEnum(String value) {
        for (IdentityAuthTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isIdentityAuthTypeEnum(String value) {
        for (IdentityAuthTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
