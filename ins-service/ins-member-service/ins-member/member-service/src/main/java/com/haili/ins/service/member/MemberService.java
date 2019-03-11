package com.haili.ins.service.member;

import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.dto.member.response.MemberInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leon
 */
@Service
public class MemberService {



    /**
     * 绑定手机号
     * @param memberInfo
     */
    @Transactional(propagation= Propagation.REQUIRES_NEW,rollbackFor = ServiceException.class)
    public void bindMobile(MemberInfo memberInfo){

    }





}
