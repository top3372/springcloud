package com.haili.ins.dto.member.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 8609350473244530852L;

    private String memberId;

    /**
     * 注册名 手机 邮箱 用户名 第三方机构openId
     **/
    private String registerName;
    /**
     * 注册类型 方式(1 系统自登陆 2 第三方联合登录)
     **/
    private String registerType;
    /**
     * 注册用户名类型 用户名 手机号 邮箱号 第三方机构
     **/
    private String registerNameType;
    /**
     * 密码
     **/
    private String passWord;
    /**
     * 第三方机构代码 新浪微博 微信 支付宝 淘宝 QQ
     **/
    private String thirdSourceCode;
    /**
     * 来源IP地址
     **/
    private String sourceIp;
    /**
     * 来源 app h5 小程序 web
     **/
    private String source;
    /**
     * 注册设备 手机 pc
     **/
    private String regTerminal;
    /**
     * 设备号
     **/
    private String deviceId;
    /**
     * 营销渠道
     **/
    private String channelSource;
    /**
     * 邀请码
     **/
    private String inviteCode;


}
