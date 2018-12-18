package com.haili.ins.common.invoke.dto;

import lombok.Data;

@Data
public class InvokeRequest {

    private String serCode;
    private String originNo;
    private String targetNo;
    private String dataMsg;
    private String versionNo;

}
