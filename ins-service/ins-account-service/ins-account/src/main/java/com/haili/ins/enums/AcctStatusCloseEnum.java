/**
 *  File: AcctStatusFrozenEnum.java
 *  Description:
 *  Copyright 2015 GAOEX Corporation. All rights reserved.
 *  Date      Author      Changes
 *  MAY 25, 2015   Jack     Create
 *
 */
package com.haili.ins.enums;

/**
 * 
 */
public enum AcctStatusCloseEnum implements CodeEnumerable {

	UNCLOSED("0", "未销户"),
	CLOSED("1", "已销户"),
	ETERNAL("2", "长期不动户"),
	CANCELLED("3", "已撤销")
	;
	
	private AcctStatusCloseEnum(String code, String description) {
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
