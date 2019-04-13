package com.haili.ins.enums.member;

public enum MemberTypeEnum {

    MEMBER_TYPE_PERSONAL("1","个人会员"),
    MEMBER_TYPE_COMPANY("2","企业会员"),

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

    private MemberTypeEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MemberTypeEnum getMemberTypeEnum(String value) {
        for (MemberTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isMemberTypeEnum(String value) {
        for (MemberTypeEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
