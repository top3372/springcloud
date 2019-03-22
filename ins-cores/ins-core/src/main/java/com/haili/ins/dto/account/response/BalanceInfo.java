package com.haili.ins.dto.account.response;

import lombok.Data;

/**
 * @Author: leon
 * @Date: 2019/3/13 14:43
 * @Version 1.0
 */
@Data
public class BalanceInfo {

    private String dAccountNo;

    private String cAccountNo;

    private Long dAccountBalance;

    private Long cAccountBalance;
}
