package com.haili.ins.dto;

import com.haili.ins.enums.MgmTxnTypeEnum;
import lombok.Data;

/**
 * @Author: leon
 * @Date: 2019/3/13 16:36
 * @Version 1.0
 */
@Data
public class MgmLogParam {

    private String sysTraceNo;
    private MgmTxnTypeEnum txnType;
    private String accountNo;
    private String ownerOrg;
    private String beforeStatusMap;
    private String afterStatusMap;
    private String remark;
    private String txnCode;
    private String txnDesc;

    public MgmLogParam(
            String sysTraceNo,
            MgmTxnTypeEnum txnType,
            String accountNo,
            String ownerOrg,
            String beforeStatusMap,
            String afterStatusMap,
            String remark,
            String txnCode,
            String txnDesc) {
        this.sysTraceNo = sysTraceNo;
        this.txnType = txnType;
        this.accountNo = accountNo;
        this.ownerOrg = ownerOrg;
        this.beforeStatusMap = beforeStatusMap;
        this.afterStatusMap = afterStatusMap;
        this.remark = remark;
        this.txnCode = txnCode;
        this.txnDesc = txnDesc;
    }
}
