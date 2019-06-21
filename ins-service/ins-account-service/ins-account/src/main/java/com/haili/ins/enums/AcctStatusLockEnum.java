/**
 * File: AcctStatusFrozenEnum.java
 * Description:
 * Copyright 2015 GAOEX Corporation. All rights reserved.
 * Date      Author      Changes
 * MAY 25, 2015   Jack     Create
 */
package com.haili.ins.enums;

/**
 *
 */
public enum AcctStatusLockEnum implements CodeEnumerable {

    UNLOCKED("0", "未锁定"),
    LOCKED("1", "已锁定"),
    ;

    private AcctStatusLockEnum(String code, String description) {
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
