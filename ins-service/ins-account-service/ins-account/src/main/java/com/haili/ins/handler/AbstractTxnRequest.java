package com.haili.ins.handler;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 抽象的交易类请求, 区别于查询类请求
 * 该请求除了TXN_CODE外,附带了TXN_DESCRIPTION和REMARK字段
 *
 * @Author: leon
 * @Date: 2019/3/13 15:00
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AbstractTxnRequest extends AbstractRequest {

    private String txnDesc;

    private String remark;
}
