package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/1 13:38
 * @Version 1.0
 */
public enum ConfigStatusEnum {

    IS_SET("1", "已设置"),

    NO_SET("0", "未设置"),
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

    ConfigStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ConfigStatusEnum getConfigStatusEnum(String value) {
        for (ConfigStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isConfigStatusEnum(String value) {
        for (ConfigStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
