package com.haili.ins.handler.v1._001001;

import com.haili.ins.common.constants.HttpHeaderConstant;
import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.common.utils.JSONUtil;
import com.haili.ins.feign.MemberFeign;
import com.haili.ins.invoke.InvokeHelper;
import com.haili.ins.invoke.InvokeLogger;
import com.haili.ins.invoke.bussiness.BusinessHandler;
import com.haili.ins.invoke.dto.InvokeParameter;
import com.haili.ins.invoke.dto.InvokeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author jack
 *
 */
@Service("001001.v1")
@Slf4j
public class HandlerImpl implements BusinessHandler {
	
	@Resource
	private MemberFeign memberFeign;

	
	@Override
	public InvokeResponse invokeBusiness(InvokeParameter invokeParam) throws ServiceException {
		InvokeLogger.info("_001001开始");
		InvokeResponse response = new InvokeResponse();

		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest httpServletRequest = attrs.getRequest();
		String userId = httpServletRequest.getHeader(HttpHeaderConstant.USER_ID);
		log.info("userId: " + userId);
		try {
			Request request = JSONUtil.toObject(invokeParam.getDataMsg(), Request.class);
			// 请求参数校验
			InvokeHelper.validate(request);


			response.buildSuccResp();

		}catch(ServiceException se) {
			throw se;
		}catch (Exception e) {
			InvokeLogger.error("_001001失败", e);
			response.buildFailResp();
		}

		InvokeLogger.info("_001001结束");
		return response;
	}
}
