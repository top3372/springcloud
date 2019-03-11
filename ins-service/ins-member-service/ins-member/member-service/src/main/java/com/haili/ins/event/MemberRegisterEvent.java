package com.haili.ins.event;

import com.haili.ins.dto.member.request.RegisterRequest;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MemberRegisterEvent extends ApplicationEvent {

    //注册用户对象
    private RegisterRequest registerRequest;

    /**
     * 重写构造函数
     * @param source 发生事件的对象
     * @param registerRequest 注册用户对象
     */
    public MemberRegisterEvent(Object source, RegisterRequest registerRequest) {
        super(source);
        this.registerRequest = registerRequest;
    }
}
