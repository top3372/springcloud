package com.haili.ins.common.security.access.prepost.exception;

import com.haili.ins.common.exception.CommonServiceException;

/**
 * @Author: leon
 * @Date: 2019/4/11 14:33
 * @Version 1.0
 */
public class SecureException extends CommonServiceException {
    private static final long serialVersionUID = 2359767895161832954L;


    public SecureException() {
        super("业务异常");
    }

    public SecureException(String desc) {
        super(desc);
    }

    public SecureException(String code, String desc) {
        super(code, desc);
    }


}
