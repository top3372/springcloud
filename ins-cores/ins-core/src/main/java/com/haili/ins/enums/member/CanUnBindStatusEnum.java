package com.haili.ins.enums.member;

public enum CanUnBindStatusEnum {


	CAN_UNBIND("1","可以解绑"),
	
	NO_CAN_UNBIND("0","不能解绑"),
	
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
	
	private CanUnBindStatusEnum(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
}
