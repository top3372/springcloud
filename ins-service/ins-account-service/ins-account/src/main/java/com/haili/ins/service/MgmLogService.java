package com.haili.ins.service;

import com.haili.ins.dao.mapper.AccManagementLogMapper;
import com.haili.ins.dao.model.AccManagementLog;
import com.haili.ins.dto.MgmLogParam;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账户管理日志
 *
 * @Author: leon
 * @Date: 2019/3/13 16:27
 * @Version 1.0
 */
@Service
public class MgmLogService extends AccountBaseService {

    @Resource
    private AccManagementLogMapper accManagementLogMapper;

    /**
     * 添加账户管理流水
     *
     * @param
     * @param
     * @throws ServiceException
     */
    public void insertMgmLog(MgmLogParam mgmLogParam) {

        AccManagementLog tAccManagementLog = new AccManagementLog();
        tAccManagementLog.setSysTraceNo(mgmLogParam.getSysTraceNo());
        tAccManagementLog.setTxnTime(new Date());
        tAccManagementLog.setAccountNo(mgmLogParam.getAccountNo());
        tAccManagementLog.setOwnerOrg(mgmLogParam.getOwnerOrg());
        tAccManagementLog.setTxnType(mgmLogParam.getTxnType().getCode());
        tAccManagementLog.setBeforeStatus(mgmLogParam.getBeforeStatusMap());
        tAccManagementLog.setAfterStatus(mgmLogParam.getAfterStatusMap());
        tAccManagementLog.setRemark(mgmLogParam.getRemark());
        tAccManagementLog.setTxnCode(mgmLogParam.getTxnCode());
        tAccManagementLog.setTxnDscpt(mgmLogParam.getTxnDesc());
        tAccManagementLog.setAccSeqNo(genAccSeqNo());

        accManagementLogMapper.insertSelective(tAccManagementLog);
    }

    /**
     * 批量添加账户管理流水
     *
     * @param
     * @param
     * @throws ServiceException
     * @throws
     */
    public void insertMgmLogBatchly(List<MgmLogParam> paramList) {

        List<AccManagementLog> mgmLogList = new ArrayList<AccManagementLog>();
        for (MgmLogParam param : paramList) {
            AccManagementLog tAccManagementLog = new AccManagementLog();
            tAccManagementLog.setSysTraceNo(param.getSysTraceNo());
            tAccManagementLog.setTxnTime(new Date());
            tAccManagementLog.setAccountNo(param.getAccountNo());
            tAccManagementLog.setOwnerOrg(param.getOwnerOrg());
            tAccManagementLog.setTxnType(param.getTxnType().getCode());
            tAccManagementLog.setBeforeStatus(param.getBeforeStatusMap());
            tAccManagementLog.setAfterStatus(param.getAfterStatusMap());
            tAccManagementLog.setRemark(param.getRemark());
            tAccManagementLog.setTxnCode(param.getTxnCode());
            tAccManagementLog.setTxnDscpt(param.getTxnDesc());
            tAccManagementLog.setAccSeqNo(genAccSeqNo());
            mgmLogList.add(tAccManagementLog);
        }
        accManagementLogMapper.insertList(mgmLogList);


    }

}
