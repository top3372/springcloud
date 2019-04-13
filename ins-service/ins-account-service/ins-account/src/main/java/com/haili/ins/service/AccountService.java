package com.haili.ins.service;

import com.haili.ins.dao.mapper.AccAccountInfoMapper;
import com.haili.ins.dao.model.AccAccountInfo;
import com.haili.ins.enums.AcctStatusMapEnum;
import com.haili.ins.enums.CodeEnumerable;
import com.haili.ins.enums.ResponseCodeEnum;
import com.haili.ins.exception.ServiceException;
import com.haili.ins.utils.BitmapStatusUtil;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 账户操作
 * @Author: leon
 * @Date: 2019/3/14 11:38
 * @Version 1.0
 */
@Service
public class AccountService extends AccountBaseService {


    @Resource
    private AccAccountInfoMapper accAccountInfoMapper;


    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = ServiceException.class)
    public void batchCreate(List<AccAccountInfo> accAccountInfoList){
        accAccountInfoMapper.insertList(accAccountInfoList);
    }

    /**
     * 获取并锁定指定的账户
     *
     * @param acctNos
     * @return
     * @throws ServiceException
     */
    protected List<AccAccountInfo> retrieveAndLockAccts(List<String> acctNos) throws ServiceException {
        List<AccAccountInfo> accountList = null;
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("acctNos", acctNos);
            accountList = accAccountInfoMapper.lockAccountRows(param);
        } catch (UncategorizedSQLException e) {
            // ORA-30006: resource busy; acquire with WAIT timeout expired
            if (e.getSQLException().getErrorCode() == 30006) {
                throw new ServiceException(ResponseCodeEnum.DB_ERROR,
                        e.getSQLException());
            } else {
                throw new ServiceException(ResponseCodeEnum.DB_BUSY,
                        e.getSQLException());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set temporary balance
        for (AccAccountInfo acct : accountList) {
            acct.setTmpAvailableAmt(acct.getAvailableBalance());
        }

        return accountList;
    }

    /**
     * 获取并锁定账户
     *
     * @param acctNo
     * @return
     * @throws ServiceException
     */
    protected AccAccountInfo retrieveAndLockAcct(String acctNo) throws ServiceException {

        List<String> acctNos = new ArrayList<String>();
        acctNos.add(acctNo);

        if (acctNos == null || acctNos.size() < 1) {
            return null;
        }

        return retrieveAndLockAccts(acctNos).get(0);

    }


    /**
     * 更改账户状态
     *
     * @param account
     * @param status
     * @param val
     * @throws ServiceException
     */
    protected void updateAcctStatus(
            AccAccountInfo account,
            AcctStatusMapEnum status,
            CodeEnumerable val) throws ServiceException  {

        account.setLastUpdateTime(new Date());
        account.setStatusMap(BitmapStatusUtil.setStatus(account.getStatusMap(),
                status, val.getCode().toCharArray()[0]));
        account.setEncryptedMsg(encrypt(account));
        accAccountInfoMapper.updateAccStatus(account);
    }


    /**
     * 更新冻结金额
     *
     * @param account
     * @param amt 如果是负数则为解冻，正数则为冻结
     * @throws ServiceException
     * @throws
     */
    protected void updateFrozenAmt(AccAccountInfo account, long amt)
            throws ServiceException {

        Date systime = new Date();
        account.setLastTxnTime(systime);
        account.setLastUpdateTime(systime);
        if (account.getAvailableBalance().longValue() - amt < 0) {
            throw new ServiceException(ResponseCodeEnum._3017);
        }
        if (account.getFrozenAmount().longValue() + amt < 0) {
            throw new ServiceException(ResponseCodeEnum._3016);
        }
        account.setFrozenAmount(new BigDecimal(account.getFrozenAmount().longValue() + amt));
        account.setAvailableBalance(new BigDecimal(account.getAvailableBalance().longValue() - amt));
        account.setEncryptedMsg(encrypt(account));

        accAccountInfoMapper.updateFrozenAmt(account);

    }
}
