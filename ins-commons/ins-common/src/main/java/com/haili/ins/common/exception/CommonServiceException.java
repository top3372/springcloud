package com.haili.ins.common.exception;


import com.haili.ins.common.constants.HailiInsConstant;

/** 业务异常 */
public class CommonServiceException extends RuntimeException {

    private String code;

    private String desc;

    public CommonServiceException() {
        super("业务异常");
        this.code = HailiInsConstant.FAILURE;
        this.desc = "业务异常";
    }

    public CommonServiceException(String desc) {
        super(desc);
        this.code = HailiInsConstant.FAILURE;
        this.desc = desc;
    }

    public CommonServiceException(String code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
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
