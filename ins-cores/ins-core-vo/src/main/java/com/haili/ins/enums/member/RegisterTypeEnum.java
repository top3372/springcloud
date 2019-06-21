package com.haili.ins.enums.member;

public enum RegisterTypeEnum {


    PERSON_REGISTER("1", "前台会员注册"),
    THIRD_ORG_UNION_REGISTER("2", "第三方机构联合注册"),
    BOMS_REGISTER("3", "管理后台添加"),

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

    RegisterTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RegisterTypeEnum getRegisterTypeEnum(String value) {
        for (RegisterTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isRegisterTypeEnum(String value) {
        for (RegisterTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}