package com.haili.ins.enums;

public enum RequestSourceEnum {


    GATEWAY("gateway", "gateway"),


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

    private RequestSourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RequestSourceEnum getRequestSourceEnum(String value) {
        if (value != null) {
            for (RequestSourceEnum nameEnum : values()) {
                if (nameEnum.getCode().equals(value)) {
                    return nameEnum;
                }
            }
        }
        return null;
    }


    public static boolean isRequestSourceEnum(String value) {
        if (value != null) {
            for (RequestSourceEnum nameEnum : values()) {
                if (nameEnum.getCode().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

}
