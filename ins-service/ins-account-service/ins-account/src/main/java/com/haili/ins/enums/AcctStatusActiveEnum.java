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
public enum AcctStatusActiveEnum implements CodeEnumerable {

    ACTIVED("1", "已激活"),
    UNACTIVED("0", "未激活"),
    ;

    private AcctStatusActiveEnum(String code, String description) {
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
