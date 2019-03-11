package com.haili.ins.enums.member;

/**
 * 会员操作类型
 */
public enum EventTypeEnum {
	
	MODIFY_LOGIN_PWD("1", "修改登录密码"),
	MODIFY_PAY_PWD("2", "修改支付密码"),
	MODIFY_MOBILE("3", "修改手机号"),
	UNBIND_BANKCARD("4", "解绑银行卡"),
	VERIFYPAY_PWD("5", "校验支付密码"),
	AUDIT_MERCHANT("6", "审核企业信息"),
	SEND_AUDIT_MERCHANT("7","提交审核企业信息"),
	MODIFY_MERCHANT("8","更新企业信息"),
	FROZEN("9","冻结"),
	UNFROZEN("10","解冻"),
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
	
	private EventTypeEnum(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
}
