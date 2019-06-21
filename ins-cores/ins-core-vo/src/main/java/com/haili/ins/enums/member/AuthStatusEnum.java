package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/1 13:37
 * @Version 1.0
 */
public enum AuthStatusEnum {

    AUTH_WAIT("0", "待验证"),
    AUTH_SUCCESS("1", "验证通过"),
    AUTH_FAIL("2", "验证失败"),
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

    AuthStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthStatusEnum getAuthStatusEnum(String value) {
        for (AuthStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isAuthStatusEnum(String value) {
        for (AuthStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
