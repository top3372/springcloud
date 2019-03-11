package com.haili.ins.enums.member;

/**
 * @Author: leon
 * @Date: 2019/3/1 13:36
 * @Version 1.0
 */
public enum CertificationStepEnum {

    INIT_CERTIFICATION_STATE("1","初始认证状态"),
    REALNAME_CERTIFICATION_STATE("2","已实名认证状态"),
    BANKCARD_CERTIFICATION_STATE("3","已绑卡认证状态"),
    PAY_PWD_CERTIFICATION_STATE("4","已设置支付密码状态"),
    UNBUND_CERTIFICATION_STATE("5","已解绑银行卡状态"),
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

    CertificationStepEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CertificationStepEnum getCertificationStepEnum(String value) {
        for (CertificationStepEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return nameEnum;
            }
        }
        return null;
    }

    public static boolean isCertificationStepEnum(String value) {
        for (CertificationStepEnum nameEnum : values()) {
            if (nameEnum.getCode().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
