package com.haili.ins.common.dto;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.utils.JSONUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 响应对象
 * 
 * @author jack
 *
 */
@Data
public class ResultInfo implements Serializable {

	private static final long serialVersionUID = -3355484205126156607L;
	private String responseCode;
	private String responseDesc;
	private Object responseData;

	public ResultInfo() {
		this.buildSuccResp();
	}

	//bulid
	public ResultInfo(ResponseCodeEnum responseCodeEnum) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = responseCodeEnum.getDesc();
		this.responseData = new HashMap<String, Object>();
	}

	//bulid
	public ResultInfo(ResponseCodeEnum responseCodeEnum,String msg) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = msg;
		this.responseData = new HashMap<String, Object>();
	}

	public ResultInfo(ResponseCodeEnum responseCodeEnum,Object data) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = responseCodeEnum.getDesc();
		this.responseData = data;
	}

	public void ResultInfo(String code, String desc, Object data) {
		this.responseCode = code;
		this.responseDesc = desc;
		this.responseData = data;
	}


	// buildSuccResp
	public void buildSuccResp(Object data) {
		this.responseCode = ResponseCodeEnum.SUCCESS.getCode();
		this.responseDesc = ResponseCodeEnum.SUCCESS.getDesc();
		this.responseData = data;
	}

	public void buildSuccResp() {
		this.responseCode = ResponseCodeEnum.SUCCESS.getCode();
		this.responseDesc = ResponseCodeEnum.SUCCESS.getDesc();
		this.responseData = new HashMap<String, Object>();
	}

	// buildFailResp
	public void buildFailResp() {
		this.responseCode = ResponseCodeEnum.PROCESS_ERROR.getCode();
		this.responseDesc = ResponseCodeEnum.PROCESS_ERROR.getDesc();
		this.responseData = new HashMap<String, Object>();
	}

	public void buildFailResp(String msg) {
		this.responseCode = ResponseCodeEnum.PROCESS_ERROR.getCode();
		this.responseDesc = msg;
		this.responseData = new HashMap<String, Object>();
	}

	public void buildFailResp(ResponseCodeEnum responseCodeEnum) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = responseCodeEnum.getDesc();
		this.responseData = new HashMap<String, Object>();
	}

	public void buildFailResp(ResponseCodeEnum responseCodeEnum, String msg) {
		this.responseCode = responseCodeEnum.getCode();
		this.responseDesc = msg;
		this.responseData = new HashMap<String, Object>();
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