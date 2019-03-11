package com.haili.ins.enums.member;

/**
 * 终端
 * @author new
 *
 */
public enum TerminalEnum {


	ANDROID("Android", "安卓"),

	IOS("iOS", "苹果"),

	WEB("WEB", "PC"),

	H5("H5", "H5");


	private String code;
	private String desc;


	TerminalEnum(String code, String desc) {

		this.code = code;
		this.desc = desc;
	}


	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}