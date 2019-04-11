package com.haili.ins.handler.v1._201001;

import com.haili.ins.dto.account.AccountInfo;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: leon
 * @Date: 2019/3/13 15:24
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Response extends InvokeResponse {

    private List<AccountInfo> accountInfo;
}
