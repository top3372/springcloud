package com.haili.ins.handler.v1._201001;

import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.common.utils.JSONUtil;
import com.haili.ins.common.utils.security.LuhnUtil;
import com.haili.ins.dao.mapper.AccTitleInfoMapper;
import com.haili.ins.dao.model.AccAccountInfo;
import com.haili.ins.dao.model.AccTitleInfo;
import com.haili.ins.dto.MgmLogParam;
import com.haili.ins.dto.account.AccountInfo;
import com.haili.ins.enums.MgmTxnTypeEnum;
import com.haili.ins.event.MgmLogEvent;
import com.haili.ins.invoke.InvokeHelper;
import com.haili.ins.invoke.InvokeLogger;
import com.haili.ins.invoke.bussiness.BusinessHandler;
import com.haili.ins.invoke.dto.InvokeParameter;
import com.haili.ins.invoke.dto.InvokeResponse;
import com.haili.ins.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: leon
 * @Date: 2019/3/13 15:40
 * @Version 1.0
 */
@Service("201001.v1")
public class TxServiceImpl implements BusinessHandler {

    @Resource
    private AccountService accountService;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private AccTitleInfoMapper accTitleInfoMapper;

    @Override
    public InvokeResponse invokeBusiness(InvokeParameter invokeParam)
            throws ServiceException {
        InvokeLogger.info("_201001 开始");

        Request request = JSONUtil.toObject(invokeParam.getDataMsg(), Request.class);
        // 请求参数校验
        InvokeHelper.validate(request);
        // 1	开户处理
        List<AccountInfo> listAccount = request.getAccountNos();
        List<MgmLogParam> mgmLogParamList = new ArrayList<>();
        List<AccAccountInfo> accountInfoList = new ArrayList<>();
        for (AccountInfo account : listAccount) {
            AccAccountInfo accountInfo = builderAccountInfo(account);
            accountInfoList.add(accountInfo);

            // 1.2	添加账户管理流水记录
            mgmLogParamList.add(new MgmLogParam(
                    invokeParam.getSysTraceNo(),
                    MgmTxnTypeEnum.CREATE,
                    accountInfo.getAccountNo(),
                    accountInfo.getOwnerOrg(),
                    null,
                    accountInfo.getStatusMap(),
                    request.getRemark(),
                    request.getTxnCode(),
                    request.getTxnDesc()));

            account.setAccountNo(accountInfo.getAccountNo());
        }

        accountService.batchCreate(accountInfoList);

        //异步记录开户管理日志
        applicationContext.publishEvent(new MgmLogEvent(this,mgmLogParamList));

        Response response = new Response();
        response.setAccountInfo(listAccount);
        InvokeLogger.info("_201001 结束");
        return response;
    }


    /**
     * 构造分户帐对象
     *
     * @param
     * @param account
     * @return
     * @throws ServiceException
     * @throws
     */
    private AccAccountInfo builderAccountInfo(AccountInfo account)
            throws ServiceException {

        //查询科目
        Map<String, Object> criteria = new HashMap<>(16);
        criteria.put("accountInnerType", account.getAccountInnerType());
        AccTitleInfo titleInfo = accTitleInfoMapper.selectOneByExample(criteria);

        AccAccountInfo accountInfo = new AccAccountInfo();
        accountInfo.setAccountNo(genAccountNo(account));
        accountInfo.setAccountName(account.getAccountName());// 账户名称
        accountInfo.setOpenDate(new Date());
        accountInfo.setOwnerOrg(account.getOwnerOrg());
        accountInfo.setOpenOrg(account.getOpenOrg());
        accountInfo.setStatusMap("1000");
        accountInfo.setAccountType(account.getAccountType());
        accountInfo.setAccountGrade(String.valueOf(1));
        accountInfo.setCurrencyCode(String.valueOf(156));
        accountInfo.setBalance(new BigDecimal(0));
        accountInfo.setAvailableBalance(new BigDecimal(0));
        accountInfo.setFrozenAmount(new BigDecimal(0));
        accountInfo.setEncryptedMsg(accountService.encrypt(accountInfo));
        accountInfo.setLastUpdateTime(new Date());
        accountInfo.setAccountTitleNo(titleInfo.getTitleNo());
        accountInfo.setAccountAttribute(titleInfo.getTitleAttribute());
        accountInfo.setAccountBaseType(titleInfo.getAccountBaseType());
        accountInfo.setBalDirection(titleInfo.getDirection());
        return accountInfo;

    }


    /**
     * 生成账户号
     * “1”+账户类型（4）+yyyyMMddHHmmss+序号（12）+校验码（1）
     *
     * @param account
     * @return
     * @throws
     */
    @SuppressWarnings("unchecked")
    private String genAccountNo(AccountInfo account) {
        StringBuffer accountNo = new StringBuffer();
        accountNo.append("1");
        accountNo.append(account.getAccountType());

        String curSeqValue = accountService.genAccSeqNo();
        accountNo.append(curSeqValue);
        accountNo.append(LuhnUtil.getCheckNumber(accountNo.toString()));

        return accountNo.toString();
    }
}
