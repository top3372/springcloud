package com.haili.ins.event.listener;


import com.haili.ins.dto.MgmLogParam;
import com.haili.ins.event.MgmLogEvent;
import com.haili.ins.service.MgmLogService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: leon
 * @Date: 2019/3/14 16:48
 * @Version 1.0
 */
@Component
public class MgmLogListener implements SmartApplicationListener {

    @Resource
    private MgmLogService mgmLogService;

    /**
     * 该方法返回true&supportsSourceType同样返回true时，才会调用该监听内的onApplicationEvent方法
     *
     * @param aClass 接收到的监听事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        //只有UserRegisterEvent监听类型才会执行下面逻辑
        return aClass == MgmLogEvent.class;
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
        return true;
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
        MgmLogEvent mgmLogEvent = (MgmLogEvent) applicationEvent;
        //获取注册用户对象信息
        List<MgmLogParam> mgmLogParamList = mgmLogEvent.getMgmLogParamList();

        mgmLogService.insertMgmLogBatchly(mgmLogParamList);

        //生成邀请码信息
        System.out.println("开户日志记录成功。");
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
