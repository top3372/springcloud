package com.haili.ins.common.vo;

import com.haili.ins.common.constants.HailiInsConstant;
import com.haili.ins.common.vo.base.ResponseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应对象
 * 
 * @author jack
 *
 */
@Data
public class ResultInfo<T> implements Serializable{

	private static final long serialVersionUID = -3355484205126156607L;

	private boolean result;

	private T data;

	private String code;

	private String msg;

	public ResultInfo(){

	}
	public ResultInfo(ResponseBean<T> responseBean){
		this.result = true;
		this.code = responseBean.getErrorCode();
		this.msg = responseBean.getErrorMessage();
	}

	public ResultInfo(String code, String msg){
		this.result = true;
		this.code = code;
		this.msg = msg;
	}

	public ResultInfo(T data,String code, String msg){
		this.result = true;
		this.data = data;
		this.code = code;
		this.msg = msg;
	}

	public ResultInfo(boolean result, T data, String code, String msg) {
		this.result = result;
		this.data = data;
		this.code = code;
		this.msg = msg;
	}

	public ResultInfo(boolean result,T data){
		this.result = result;
		this.data = data;
	}

	public ResultInfo(String msg){
		this.result = true;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static <T> ResultInfo<T> error(String code, String msg){
		return new ResultInfo<T>(code,msg);
	}

	public static <T> ResultInfo<T> error(T data,String code, String msg){
		return new ResultInfo<T>(false,data,code,msg);
	}

	public static <T> ResultInfo<T> success(T data, String msg){
		return new ResultInfo<T>(data, HailiInsConstant.SUCCESS,msg);
	}

	public static <T> ResultInfo<T> successSimple(ResponseBean<T> responseBean){
		return new ResultInfo<T>(responseBean);
	}


}