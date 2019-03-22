package com.haili.ins.handler.v1._201001;

import com.haili.ins.dto.account.AccountInfo;
import com.haili.ins.handler.AbstractTxnRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: leon
 * @Date: 2019/3/13 15:21
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Request extends AbstractTxnRequest {


    private List<AccountInfo> accountNos;

}
