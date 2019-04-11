package com.haili.ins.service;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.dao.mapper.AccAccountInfoMapper;
import com.haili.ins.dao.model.AccAccountInfo;
import com.haili.ins.feign.EncryptFeign;
import com.haili.ins.feign.LeafIdFeign;
import com.haili.ins.handler.AbstractRequest;
import com.haili.ins.common.invoke.InvokeLogger;
import com.haili.ins.common.invoke.bussiness.BusinessHandler;
import com.haili.ins.common.invoke.dto.InvokeParameter;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.UncategorizedSQLException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leon
 * @Date: 2019/3/13 15:42
 * @Version 1.0
 */
public class AccountBaseService{

    @Resource
    private  EncryptFeign encryptFeign;

    @Resource
    private  LeafIdFeign leafIdFeign;


    public String genAccSeqNo(){

        return leafIdFeign.getSegmentID("S_ACC_SEQ_NO");
    }

    public String genEntrySeqNo(){

        return leafIdFeign.getSegmentID("S_ENTRY_SEQ_NO");
    }

    /**
     * 检查账户的密钥信息
     * Note: 如果账户刚初始化,那么在进行操作的时候无需交验其关键密文,所谓初始化状态即(
     * 最后更新时间为空,最后交易时间为空,余额为0
     * )
     *
     * @param account
     * @return
     */
    public boolean checkEncryptMsg(AccAccountInfo account) throws ServiceException{

        // 如果账户为初始化状态,则无需交验关键密文
//		if (account.getLastTxnTime() == null &&
//			account.getLastUpdateTime() == null &&
//			account.getBalance() == 0) {
//
//			return true;
//		}

        String encryptMsg = encrypt(account);
        if (!StringUtils.equals(encryptMsg, account.getEncryptedMsg())) {
            throw new ServiceException(ResponseCodeEnum._3009);
        }

        return true;
    }

    public String encrypt(AccAccountInfo account) {
        StringBuffer strBuf= new StringBuffer();
        strBuf.append(account.getAccountNo());
        strBuf.append(account.getStatusMap());
        strBuf.append(account.getBalance());
        strBuf.append(account.getAvailableBalance());
        strBuf.append(account.getFrozenAmount());
        String encryptedMsg = null;
        try {
            encryptedMsg = encryptFeign.encryptedMsg(strBuf.toString());
        } catch (Exception e) {
            InvokeLogger.error("Error in encrypting account");
            throw new RuntimeException(e);
        }

        return encryptedMsg;
    }

    public String encrypt(String accountNo, String statusMap, String balance, String availableBalance, String frozenAmount) {
        StringBuffer strBuf= new StringBuffer();
        strBuf.append(accountNo);
        strBuf.append(statusMap);
        strBuf.append(balance);
        strBuf.append(availableBalance);
        strBuf.append(frozenAmount);
        String encryptedMsg = null;
        try {
            encryptedMsg = encryptFeign.encryptedMsg(strBuf.toString());
        } catch (Exception e) {
            InvokeLogger.error("Error in encrypting account");
            throw new RuntimeException(e);
        }

        return encryptedMsg;
    }

}
