/**
 * 
 */
package com.haili.ins.core.invoke;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.enums.SystemCodeEnum;
import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.common.utils.*;
import com.haili.ins.core.invoke.dto.InvokeParameter;
import com.haili.ins.core.invoke.dto.InvokeResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author new
 * 
 */
public class InvokeHelper {

	private final static int compressLimit= 1024;

	

	/**
	 * 构建异常响应信息
	 * @param serCode  	服务代码
	 * @param sysTraceNo 系统跟踪号
	 * @param originNo	源系统编号
	 * @param targetNo	目标系统编号
	 * @param versionNo	接口版本号
	 * @param responseCode 响应代码
	 * @param responseDesc 响应描述
	 * @return
	 */
	public static   String buildExceptionResponse(String serCode, String sysTraceNo,
			String originNo, String targetNo, String versionNo,String responseCode,String responseDesc) {

		Map<String, String> result = new HashMap<String, String>(2);
		result.put("responseCode", responseCode);
		result.put("responseDesc", responseDesc);
		InvokeParameter param = new InvokeParameter();
		param.setSerCode(serCode);
		param.setSysTraceNo(sysTraceNo);
		param.setOriginNo(originNo);
		param.setTargetNo(targetNo);
		param.setVersionNo(versionNo);
		param.setDataMsg(JSONUtil.toJson(result));
		return param.toString();
	}

//	/**
//	 * 处理响应参数，如果dataMsg被压缩，则解压
//	 * @param param
//	 * @return
//	 */
//	public static void processResponse(InvokeParameter param){
//
//		if(param.getMsgCompress()==1){
//			try {
//				param.setDataMsg(ZipUtil.uncompress(param.getDataMsg()));
//				param.setMsgCompress(0);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}

	/**
	 * 处理请求参数，如果大于1024 byte，压缩后返回
	 * @param srcDataMsg 源串
	 * @return
	 */
	public static InvokeParameter processRequest(String srcDataMsg){
		InvokeParameter param = new InvokeParameter();
		param.setDataMsg(srcDataMsg);
		try {
			param.setDataLength(srcDataMsg.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}


//		if(param.getDataLength()>compressLimit){
//
//			try {
//				param.setDataMsg(ZipUtil.compress(srcDataMsg));
//				param.setMsgCompress(1);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		}else{
//			param.setMsgCompress(0);
//		}

		return param;
	}

	
	
	/**
	 * 构建响应信息
	 * @param serCode  	服务代码
	 * @param sysTraceNo 系统跟踪号
	 * @param originNo	源系统编号
	 * @param versionNo	接口版本号
	 * @param dataMsg	响应报文
	 * @return
	 */
	public static   String buildResponse(String serCode, String sysTraceNo,
			String originNo, String versionNo, String dataMsg) {
		InvokeParameter param = new InvokeParameter();
		param.setSerCode(serCode);
		param.setSysTraceNo(sysTraceNo);
		param.setOriginNo(originNo);
		param.setVersionNo(versionNo);
		param.setDataMsg(dataMsg);

		return param.toString();
	}

	/**
	 * 验证请求参数是否有效
	 * @param serCode
	 * @param sysTraceNo
	 * @param originNo
	 * @param targetNo
	 * @param versionNo
	 * @param dataLength
	 * @param dataMsg
	 * @throws ServiceException
	 */
	public static void validateReqParam(String serCode, String sysTraceNo, String originNo,
			String targetNo, String versionNo, int dataLength,
			String dataMsg) throws ServiceException{
		if(StringUtils.isEmpty(serCode)||StringUtils.isEmpty(sysTraceNo)||StringUtils.isEmpty(originNo)||StringUtils.isEmpty(targetNo)||StringUtils.isEmpty(versionNo)||dataLength==0||StringUtils.isEmpty(dataMsg)){
			throw new ServiceException(ResponseCodeEnum.INVALID_PARAM);
		}
	}

	/**
	 * 验证目标系统是否正确
	 * @param targetNo  请求目标系统编号
	 * @param currentSystemCode 当前系统编号
	 * @throws ServiceException
	 */
	public static void validateTargetNo(String targetNo,String currentSystemCode) throws ServiceException{
		if(StringUtils.isEmpty(targetNo)||StringUtils.isEmpty(currentSystemCode)){
			throw new ServiceException(ResponseCodeEnum.VALID_TARGETSYSCODE_FAILURE.getCode());
		}else if(!targetNo.equals(currentSystemCode)){
			throw new ServiceException(ResponseCodeEnum.VALID_TARGETSYSCODE_FAILURE.getCode());
		}
	}

	/**
	 * 验证请求消息正文内容长度
	 * @param dataLength 请求消息正文内容长度
	 * @param dataMsg    请求消息正文
	 * @throws ServiceException
	 */
	public static void validateDataMsgSize(int dataLength,String dataMsg) throws ServiceException{
		try {
			if(StringUtils.isEmpty(dataMsg)||dataMsg.getBytes("UTF-8").length!=dataLength){
				throw new ServiceException(ResponseCodeEnum.VALID_DATAMSGSIZE_FAILURE.getCode(),ResponseCodeEnum.VALID_DATAMSGSIZE_FAILURE.getDesc());
			}
		} catch (UnsupportedEncodingException e) {
			InvokeLogger.error(ResponseCodeEnum.MSG_PARSING_FAILURE.getDesc(),e);
			throw new ServiceException(ResponseCodeEnum.MSG_PARSING_FAILURE.getCode());
		}
	}


	/** 接口参数校验
	 * @param <T>
	 * @param bean
	 * @throws ServiceException
	 */
	public static<T> void validate(T bean) throws ServiceException {

		try {
			BeanValidator.validate(bean);
		} catch (ServiceException e) {
			throw new ServiceException(e.getCode(),e.getDesc());
		}

	}

	public static<T> void validateList(List<T> list) throws  ServiceException{

		try {
			if(list == null || list.size() == 0){
				throw new ServiceException(ResponseCodeEnum.PARAM_VRFY_FAIL);
			}
			for(T t : list){
				BeanValidator.validate(t);
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getCode(),e.getCode());
		}

	}


	public static String generateSysTraceNo(String systemCode) {
		String date = DateUtil.now(DateFormatType.YYYYMMDDHHMMSSSSS);
		return systemCode + date + RandomUtil.randomDegital(10);
	}


//	/**
//	 * @param invokeService
//	 * @param invokeParam	请求参数
//	 */
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> invoke(InvokeService invokeService,InvokeParameter invokeParam){
//
////		SystemCodeEnum currentSysEnum = SystemCodeEnum.getSystemCodeEnum(invokeParam.getOriginNo());
////		SystemCodeEnum targetSysEnum = SystemCodeEnum.getSystemCodeEnum(invokeParam.getTargetNo());
//
//		String seqNo = InvokeHelper.generateSysTraceNo(invokeParam.getOriginNo());
////		InvokeLogger.info("###### "+ currentSysEnum.getDesc() +"("+ currentSysEnum.getCode()+")调用"+ targetSysEnum.getDesc()+"("+targetSysEnum.getCode()+ ") 服务码"+invokeParam.getSerCode()+"  Hessian Request："+invokeParam.getDataMsg());
//
//		String result = null;
//		try {
//			result = invokeService.invoke(invokeParam.getSerCode(), seqNo, invokeParam.getOriginNo(), invokeParam.getTargetNo(), invokeParam.getVersionNo(), invokeParam.getDataLength(), invokeParam.getMsgCompress(), invokeParam.getDataMsg());
//		} catch (Exception e) {
//			InvokeLogger.error(e.getMessage(),e);
//		}
//		invokeParam.parse(result);
//		InvokeHelper.processResponse(invokeParam);
////		InvokeLogger.info("######  " + targetSysEnum.getDesc() + "响应 Hessian Response：" + invokeParam.getDataMsg());
//
//		Map<String, Object> resultDataMsg = JSONUtil.toObject(invokeParam.getDataMsg(), Map.class);
//		InvokeLogger.info(invokeParam.getDataMsg());
//		return resultDataMsg;
//	}


	/**
	 * @param invokeService
	 * @param invokeParam
	 */
	public static InvokeResponse invoke(InvokeService invokeService, InvokeParameter invokeParam ){
		try {

			String seqNo = InvokeHelper.generateSysTraceNo(invokeParam.getOriginNo());
			InvokeLogger.setSession(invokeParam.getSerCode(), seqNo);
			InvokeParameter param = InvokeHelper.processRequest(invokeParam.getDataMsg());

			param.setSysTraceNo(seqNo);
			param.setTargetNo(invokeParam.getTargetNo());
			param.setOriginNo(invokeParam.getOriginNo());
			param.setSerCode(invokeParam.getSerCode());
			param.setVersionNo(invokeParam.getVersionNo());
			SystemCodeEnum currentSysEnum = SystemCodeEnum.getSystemCodeEnum(param.getOriginNo());
			SystemCodeEnum targetSysEnum = SystemCodeEnum.getSystemCodeEnum(param.getTargetNo());
			InvokeLogger.info("###### "+ currentSysEnum.getDesc() +"("+ currentSysEnum.getCode()+")调用"+ targetSysEnum.getDesc()+"("+targetSysEnum.getCode()+ ") 服务码 "+param.getSerCode()+"   Request："+param.getDataMsg());


			String result = null;
			InvokeResponse response = null;


			result = invokeService.invoke(param);

			invokeParam.parse(result);
			//InvokeHelper.processResponse(invokeParam);
			InvokeLogger.info("######  " + targetSysEnum.getDesc() + "响应 Response：" + invokeParam.getDataMsg());
			response = JSONUtil.toObject(invokeParam.getDataMsg(), InvokeResponse.class);

			return response;
		} catch (Exception e) {
			InvokeLogger.error(e.getMessage(),e);
			InvokeResponse response = new InvokeResponse();
			response.setResponseCode(ResponseCodeEnum.UNDEFINED_ERROR.getCode());
			response.setResponseDesc(ResponseCodeEnum.UNDEFINED_ERROR.getDesc());
			response.setData(e.getMessage());
			return response;
		} finally {
			// 清理线程信息
			InvokeLogger.removeSession();
		}

	}
}
