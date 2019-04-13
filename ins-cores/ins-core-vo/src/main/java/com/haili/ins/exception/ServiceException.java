package com.haili.ins.exception;


import com.haili.ins.common.exception.CommonServiceException;
import com.haili.ins.enums.ResponseCodeEnum;

/** 业务异常 */
public class ServiceException extends CommonServiceException {

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

    public ServiceException(String code, String desc) {
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

    public ServiceException(ResponseCodeEnum respCodeEnum, Throwable cause) {
        this.code = respCodeEnum.getCode();
        this.desc = respCodeEnum.getDesc();
    }

    public ServiceException(ResponseCodeEnum respCodeEnum, String msg, Throwable cause) {
        this.code = respCodeEnum.getCode();
        this.desc = respCodeEnum.getDesc();
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
