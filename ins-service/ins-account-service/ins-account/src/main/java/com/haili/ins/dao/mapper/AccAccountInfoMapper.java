package com.haili.ins.dao.mapper;

import com.haili.ins.common.database.mybatis.mapper.BaseMapper;
import com.haili.ins.dao.model.AccAccountInfo;

import java.util.List;
import java.util.Map;

public interface AccAccountInfoMapper extends BaseMapper<AccAccountInfo> {

    List<AccAccountInfo> lockAccountRows(Map<String, Object> param);

    int updateAccStatus(AccAccountInfo account);

    int updateFrozenAmt(AccAccountInfo account);

    int updateBalance(AccAccountInfo account);

    List<AccAccountInfo> selectByAccountNoList(Map<String, Object> param);
}