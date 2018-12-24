package com.haili.ins.core.invoke.dto;

import lombok.Data;

@Data
public class InvokeRequest {

    private String serCode;
    private String originNo;
    private String targetNo;
    private String dataMsg;

}
