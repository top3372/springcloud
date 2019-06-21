package com.haili.ins.event.listener;

import com.haili.ins.dto.member.request.RegisterRequest;
import com.haili.ins.event.MemberRegisterEvent;
import com.haili.ins.service.member.register.impl.MemberRegisterService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ========================
 *
 * @author Leon
 */
@Component
public class MemberRegisterListener implements SmartApplicationListener {
    @Resource
    private MemberRegisterService memberRegisterService;


    /**
     * 该方法返回true&supportsSourceType同样返回true时，才会调用该监听内的onApplicationEvent方法
     *
     * @param aClass 接收到的监听事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        //只有UserRegisterEvent监听类型才会执行下面逻辑
        return aClass == MemberRegisterEvent.class;
    }

    /**
     * 该方法返回true&supportsEventType同样返回true时，才会调用该监听内的onApplicationEvent方法
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        //只有在UserService内发布的MemberRegisterEvent事件时才会执行下面逻辑
        return aClass == MemberRegisterService.class;
    }

    /**
     * supportsEventType & supportsSourceType 两个方法返回true时调用该方法执行业务逻辑
     *
     * @param applicationEvent 具体监听实例，这里是MemberRegisterEvent
     */
    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        MemberRegisterEvent memberRegisterEvent = (MemberRegisterEvent) applicationEvent;
        //获取注册用户对象信息
        RegisterRequest registerRequest = memberRegisterEvent.getRegisterRequest();

        memberRegisterService.addRegLog(registerRequest);

        //生成邀请码信息
        System.out.println("用户：" + registerRequest.getRegisterName() + "，注册成功，发送邮件通知。");
    }

    /**
     * 同步情况下监听执行的顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}