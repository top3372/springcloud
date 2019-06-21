package com.haili.ins.enums.member;

public enum FrozenStatusEnum {

    FROZEN("1", "冻结"),
    UNFROZEN("2", "解冻"),
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

    FrozenStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FrozenStatusEnum getFrozenStatusEnum(String value) {
        for (FrozenStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isFrozenStatusEnum(String value) {
        for (FrozenStatusEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
