/**
 *  File: AccountInfoStatusEnum.java
 *  Description:
 *  Copyright 2015 GAOEX Corporation. All rights reserved.
 *  Date      Author      Changes
 *  MAY 25, 2015   Jack     Create
 *
 */
package com.haili.ins.enums;

import com.gaoex.service.account.util.BitmapStatusEnumable;

/**
 * 
 */
public enum AccountInfoStatusEnum implements BitmapStatusEnumable {

	/**
	 * 激活状态位
	 */
	ACTIVE_STATUS(0),
	
	/**
	 * 锁定状态位
	 */
	LOCK_STATUS(1),
	
	/**
	 * 冻结状态位
	 */
	FROZEN_STATUS(2),
	
	/**
	 * 销户状态位
	 */
	REMOVE_STATUS(3)
	
	;
	
	private AccountInfoStatusEnum(int sequence) {
		this.sequence = sequence;
	}
	
	private int sequence;

	/* (non-Javadoc)
	 * @see com.hnacard.account.util.BitmapStatusEnumable#getSequence()
	 */
	@Override
	public int getSequence() {
		// TODO Auto-generated method stub
		return this.sequence;
	}
	
	
}
