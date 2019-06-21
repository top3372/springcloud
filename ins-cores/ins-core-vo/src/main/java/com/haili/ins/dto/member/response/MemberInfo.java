package com.haili.ins.dto.member.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Leon
 */
@Data
public class MemberInfo implements Serializable {
    private static final long serialVersionUID = -3751052688848039278L;

    /**
     * 会员号 生成
     **/
    private String memberId;

    /**
     * 用户名 默认生成 可以修改一次
     **/
    private String userName;

    private String userNameMask;

    /**
     * 手机号
     **/
    private String mobile;

    /**
     * 手机号掩码
     **/
    private String mobileMask;

    /**
     * 邮箱
     **/
    private String email;

    /**
     * 邮箱掩码
     **/
    private String emailMask;

    /**
     * 会员类型
     **/
    private String memberType;

    /**
     * 头像路径
     **/
    private String faceUrl;

    /**
     * 昵称
     **/
    private String nickName;

    /**
     * 昵称掩码
     **/
    private String nickNameMask;

    /**
     * 绑卡认证状态（0已绑卡1未绑卡）
     **/
    private String isBindBankCard;

    /**
     * 实名认证状态（0已认证1未认证）
     **/
    private String realNameStatus;

    /**
     * 真实姓名
     **/
    private String realName;

    /**
     * 真实姓名掩码
     **/
    private String realNameMask;

    /**
     * 认证的状态1、2、3、4(认证的进度)0 初始化状态 1已经实名 2 绑卡 3设置支付密码 4认证完成
     **/
    private String certificationState;

    /**
     * 是否设置登录密码(0未设置1已设置)
     **/
    private String isSetLoginPwd;

    /**
     * 是否购买过得标识 0 未购买 1 已购买
     **/
    private String buyFlag;

    /**
     * 是否修改过用户名 0 未修改 1 已修改
     **/
    private String isModifyUserName;

}
