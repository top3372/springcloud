package com.haili.ins.common.security.access.prepost.exception;

import com.haili.ins.common.enums.ResponseCodeEnum;

/**
 * @Author: leon
 * @Date: 2019/4/11 14:33
 * @Version 1.0
 */
public class SecureException extends RuntimeException  {
    private static final long serialVersionUID = 2359767895161832954L;

    private String code;

    private String desc;

    public SecureException() {
        super("业务异常");
        this.code = ResponseCodeEnum.FAILURE.getCode();
        this.desc = "业务异常";
    }

    public SecureException(String desc) {
        super(desc);
        this.code = ResponseCodeEnum.FAILURE.getCode();
        this.desc = desc;
    }

    public SecureException(String code,String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public SecureException(ResponseCodeEnum respCodeEnum, String desc) {
        super(desc);
        this.code = respCodeEnum.getCode();
        this.desc = desc;
    }

    public SecureException(ResponseCodeEnum respCodeEnum) {
        super(respCodeEnum.getDesc());
        this.code = respCodeEnum.getCode();
        this.desc = respCodeEnum.getDesc();
    }

    public SecureException(ResponseCodeEnum respCodeEnum, Throwable cause) {
        super(cause);
        this.code = respCodeEnum.getCode();
        this.desc = respCodeEnum.getDesc();
    }

    public SecureException(ResponseCodeEnum respCodeEnum, String msg, Throwable cause) {
        super(msg, cause);
        this.code = respCodeEnum.getCode();
        this.desc = respCodeEnum.getDesc();
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


}
