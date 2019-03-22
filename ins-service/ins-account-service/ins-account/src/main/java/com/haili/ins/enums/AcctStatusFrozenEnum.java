/**
 *  File: AcctStatusFrozenEnum.java
 *  Description:
 *  Copyright 2015 GAOEX Corporation. All rights reserved.
 *  Date      Author      Changes
 *  MAY 25, 2015   Jack     Create
 *
 */
package com.haili.ins.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 */
public enum AcctStatusFrozenEnum implements CodeEnumerable {

	UNFROZEN("0", "未冻结"), AMOUNT_FROZEN("1", "限额冻结"), DEBIT_FROZEN("2", "借方冻结"), CREDIT_FROZEN(
			"3", "贷方冻结"), DOUBLE_FROZEN("4", "双向冻结"), ;

	private AcctStatusFrozenEnum(String code, String description) {
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

	public static CodeEnumerable codeOf(String code) {

		CodeEnumerable[] enums = values();
		for (CodeEnumerable entry : enums) {
			if (StringUtils.equals(code, entry.getCode())) {
				return entry;
			}
		}

		return null;
	}

}
