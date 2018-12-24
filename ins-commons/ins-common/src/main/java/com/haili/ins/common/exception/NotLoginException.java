package com.haili.ins.common.exception;

/** 未登录的异常, 此异常会导致 app 上的页面扭转 */
public class NotLoginException extends RuntimeException {

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
