package com.haili.ins.service.member;

import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.dto.member.request.RegisterRequest;
import com.haili.ins.dto.member.response.MemberInfo;
import com.haili.ins.mapper.MemberBaseMapper;
import com.haili.ins.mapper.MemberLoginInfoMapper;
import com.haili.ins.mapper.MemberRegLogMapper;
import com.haili.ins.model.MemberPayInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class MemberService {

    @Resource
    private MemberBaseMapper memberBaseMapper;

    @Resource
    private MemberLoginInfoMapper memberLoginInfoMapper;

    @Resource
    private MemberRegLogMapper memberRegLogMapper;

    /**
     * 注册用户
     * @param registerRequest
     * @return
     */
    @Transactional(propagation= Propagation.REQUIRES_NEW,rollbackFor = ServiceException.class)
    public MemberInfo register(RegisterRequest registerRequest){



    }

    /**
     * 绑定手机号
     * @param memberInfo
     */
    @Transactional(propagation= Propagation.REQUIRES_NEW,rollbackFor = ServiceException.class)
    public void bindMobile(MemberInfo memberInfo){

    }



}
