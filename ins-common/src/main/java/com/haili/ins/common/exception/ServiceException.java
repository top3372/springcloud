package com.haili.ins.common.exception;


import com.haili.ins.common.enums.ResponseCodeEnum;

/** 业务异常 */
public class ServiceException extends RuntimeException {

    private String code;

    private String desc;

    public ServiceException() {
        super("业务异常");
        this.code = ResponseCodeEnum.FAILURE.getCode();
        this.desc = "业务异常";
    }

    public ServiceException(String desc) {
        super(desc);
        this.code = ResponseCodeEnum.FAILURE.getCode();
        this.desc = desc;
    }

    public ServiceException(String code,String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public ServiceException(ResponseCodeEnum respCodeEnum, String desc) {
        super(desc);
        this.code = respCodeEnum.getCode();
        this.desc = desc;
    }

    public ServiceException(ResponseCodeEnum respCodeEnum) {
        super(respCodeEnum.getDesc());
        this.code = respCodeEnum.getCode();
        this.desc = respCodeEnum.getDesc();
    }


    // ------GET SET--------
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
