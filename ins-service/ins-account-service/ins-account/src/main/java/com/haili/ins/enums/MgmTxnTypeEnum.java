/**
 *  File: AcctMgmTxnTypeEnum.java
 *  Description:
 *  Copyright 2015 GAOEX Corporation. All rights reserved.
 *  Date      Author      Changes
 *  MAY 25, 2015   Jack     Create
 *
 */
package com.haili.ins.enums;

/**
 * 账户管理流水表中交易类型字段的枚举
 */
public enum MgmTxnTypeEnum implements CodeEnumerable {

	CREATE("1", "开户"),
	CLOSE("2", "销户"),
	FREEZE("3", "冻结账户"),
	UNFREEZE("4", "解冻"),
	LOCK("5", "锁定账户"),
	UNLOCK("6", "解锁帐户"),
	CANCEL("7", "撤销户"),

	;
	private MgmTxnTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	private String code;
	
	private String description;
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
}

	
