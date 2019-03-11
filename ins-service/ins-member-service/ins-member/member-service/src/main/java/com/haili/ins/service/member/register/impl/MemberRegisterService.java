package com.haili.ins.service.member.register.impl;

import com.haili.ins.dto.member.request.RegisterRequest;
import com.haili.ins.event.MemberRegisterEvent;
import com.haili.ins.service.member.register.AbstractRegisterService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author MaTang
 */
@Service
public class MemberRegisterService extends AbstractRegisterService {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void registerBefore(RegisterRequest registerRequest) {

    }

    @Override
    public void registerAfter(RegisterRequest registerRequest) {
        applicationContext.publishEvent(new MemberRegisterEvent(this,registerRequest));
    }

}
