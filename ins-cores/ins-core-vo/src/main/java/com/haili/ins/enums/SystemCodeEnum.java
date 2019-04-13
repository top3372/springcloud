package com.haili.ins.enums;

/**
 * @author new
 *
 */
public enum SystemCodeEnum {
	
	_BASIC_V1("000","基础配置","0.0.1"),
	
	_MEMBER_V1("001","会员管理","0.0.1"),
	
	_ORDER_V1("002","订单管理","0.0.1"),
	

	;
	
	private String code;
	private String desc;
	private String version;
	
	
	




	SystemCodeEnum(String code, String desc, String version) {
		
		this.code = code;
		this.desc = desc;
		this.version = version;
	}



	
	public String getCode() {
		return code;
	}
	
	
	public String getDesc() {
		return desc;
	}

	public String getVersion() {
		return version;
	}


	public static SystemCodeEnum getSystemCodeEnum(String value) {
		if (value != null) {
			for (SystemCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return nameEnum;
				}
			}
		}
		return null;
	}
	
	
	public static boolean isSystemCodeEnum(String value) {
		if (value != null) {
			for (SystemCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
