package com.haili.ins.common.response;

import com.haili.ins.common.enums.ResponseCodeEnum;

/**
 * 接口响应，所有接口返回的JSON数据应由此类承载
 * Created by GLGGAG on 2017/6/14.
 */
public class ResponseMessage<T> extends Message {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private T data;

    public ResponseMessage() {
        setRespMsg(ResponseCodeEnum.SUCCESS);
    }
    public ResponseMessage(ResponseCodeEnum err) {
        super();
        this.code = err.getCode();
        this.msg = err.getDesc();
    }

    public ResponseMessage(ResponseCodeEnum err, String msg) {
        super();
        this.code = err.getCode();
        this.msg = msg;
    }

    public ResponseMessage(ResponseCodeEnum err, T data){
    	 this.code = err.getCode();
         this.msg = err.getDesc();
         this.data = data;
    }
    
    public ResponseMessage(T data) {
    	this();
        this.data = data;
    }
    public ResponseMessage(String code, String message) {
    	this.code=code;
        this.msg = message;
    }

    public ResponseMessage(String code, String message,T data) {
        this(code, message);
        this.data=data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    /**
     * 处理成功
     *
     * @return
     */
    public boolean succeed() {
        return ResponseCodeEnum.SUCCESS.getCode().equals(code);
    }

    public void setRespMsg(ResponseCodeEnum msg) {
        this.code = msg.getCode();
        this.msg = msg.getDesc();
    }
    public void setRespMsg(ResponseCodeEnum msg, String customMsgText) {
        this.code = msg.getCode();
        this.msg = customMsgText;
    }
}