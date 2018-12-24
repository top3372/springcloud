package com.haili.ins.core.invoke.dto;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.utils.JSONUtil;
import lombok.Data;

import java.util.HashMap;

/**
 * 响应对象
 * 
 * @author jack
 *
 */
@Data
public class InvokeResponse {

	private String responseCode;
	private String responseDesc;
	private Object data;

	public InvokeResponse() {
		this.buildSuccResp();
	}

	//bulid
	public void build(ResponseCodeEnum responseCodeEnum) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = responseCodeEnum.getDesc();
		this.data = new HashMap<String, Object>();
	}

	public void build(String code, String desc, Object data) {
		this.responseCode = code;
		this.responseDesc = desc;
		this.data = data;
	}


	// buildSuccResp
	public void buildSuccResp(Object data) {
		this.responseCode = ResponseCodeEnum.SUCCESS.getCode();
		this.responseDesc = ResponseCodeEnum.SUCCESS.getDesc();
		this.data = data;
	}

	public void buildSuccResp() {
		this.responseCode = ResponseCodeEnum.SUCCESS.getCode();
		this.responseDesc = ResponseCodeEnum.SUCCESS.getDesc();
		this.data = new HashMap<String, Object>();
	}

	// buildFailResp
	public void buildFailResp() {
		this.responseCode = ResponseCodeEnum.PROCESS_ERROR.getCode();
		this.responseDesc = ResponseCodeEnum.PROCESS_ERROR.getDesc();
		this.data = new HashMap<String, Object>();
	}

	public void buildFailResp(String msg) {
		this.responseCode = ResponseCodeEnum.PROCESS_ERROR.getCode();
		this.responseDesc = msg;
		this.data = new HashMap<String, Object>();
	}

	public void buildFailResp(ResponseCodeEnum responseCodeEnum) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = responseCodeEnum.getDesc();
		this.data = new HashMap<String, Object>();
	}

	public void buildFailResp(ResponseCodeEnum responseCodeEnum, String msg) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = msg;
		this.data = new HashMap<String, Object>();
	}


	public String toJson() {

		String serializedStr = null;
		try {
			serializedStr = JSONUtil.toJson(this);
		} catch (Exception e) {
			throw new RuntimeException("Error when serializing response", e);
		}

		return serializedStr;

	}


}