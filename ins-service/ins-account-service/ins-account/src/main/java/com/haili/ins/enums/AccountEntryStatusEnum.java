/**
 * File: AccountEntryStatusEnum.java
 * Description:
 * Copyright 2015 GAOEX Corporation. All rights reserved.
 * Date      Author      Changes
 * MAY 25, 2015   Jack     Create
 */
package com.haili.ins.enums;


/**
 *
 */
public enum AccountEntryStatusEnum implements BitmapStatusEnumable {

    IS_RED(0),
    IS_BLUE(1),

    ;

    private AccountEntryStatusEnum(int sequence) {
        this.sequence = sequence;
    }

    private int sequence;

    /* (non-Javadoc)
     * @see com.hnacard.account.util.BitmapStatusEnumable#getSequence()
     */
    @Override
    public int getSequence() {
        // TODO Auto-generated method stub
        return sequence;
    }

}
