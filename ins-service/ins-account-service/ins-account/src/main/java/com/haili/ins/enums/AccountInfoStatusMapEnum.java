/**
 *  File: AccountInfoStatusMapEnum.java
 *  Description:
 *  Copyright 2015 GAOEX Corporation. All rights reserved.
 *  Date      Author      Changes
 *  MAY 25, 2015   Jack     Create
 *
 */
package com.haili.ins.enums;

/**
 * 关于账户状态位的枚举
 */
public enum AccountInfoStatusMapEnum {
	/**
	 * 第一位 是否激活  0:未激活,1:已激活
	 */
	FST_NO_ACTIVE("0","未激活"),
	FST_IS_ACTIVE("1","已激活"),
	
	/**
	 * 第二位 是否锁定  0:未锁定,1:已锁定
	 */
	SND_NO_LOCKED("0","未锁定"),
	SND_IS_LOCKED("1","已锁定"),
	
	/**
	 * 第三位 是否冻结:  0:未冻结,1:限额冻结,2:借方冻结,3:贷方冻结,4:双向冻结
	 */
	THD_NO_FROZEN("0","未冻结"),
	THD_AMOUNT_FROZEN("1","限额冻结"),
	THD_DEBIT_FROZEN("2","借方冻结"),
	THD_AUDIT_FROZEN("3","贷方冻结"),
	THD_ALL_FROZEN("4","双向冻结"),
	
	/**
	 * 第四位 是否销户   0:未销户,1:已销户,2:已结转长期不动户,3：已废除
	 */
	FOR_NORMAL_ACCOUNT("0","未销户"),
	FOR_ABRO_ACCOUNT("1","已销户"),
	FOR_ETERNAL_ACCOUNT("2","已结转长期不动户"),
	FOR_CANCEL_ACCOUNT("3","已废除")
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
	private AccountInfoStatusMapEnum( ) {
	}
	private AccountInfoStatusMapEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
}
