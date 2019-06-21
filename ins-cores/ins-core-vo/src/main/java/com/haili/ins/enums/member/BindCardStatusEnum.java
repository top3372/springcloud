package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/1 13:58
 * @Version 1.0
 */
public enum BindCardStatusEnum {

    BANK_CARD_BINDING("1", "银行卡已绑定"),

    BANK_CARD_UNBINDING("0", "银行未绑定"),
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

    BindCardStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static BindCardStatusEnum getBindCardStatusEnum(String value) {
        for (BindCardStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isBindCardStatusEnum(String value) {
        for (BindCardStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
