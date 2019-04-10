package com.haili.ins.common.invoke;


import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.common.invoke.bussiness.BusinessHandler;
import com.haili.ins.common.invoke.dto.InvokeParameter;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import com.haili.ins.common.utils.JSONUtil;
import com.haili.ins.common.utils.ZipUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvokeService {

    @Resource
    private Map<String, BusinessHandler> businessHandlerMap;

    public String invoke(InvokeParameter invokeParameter){

        try {
            long start = System.currentTimeMillis();

            InvokeLogger.info("请求参数校验");
            validate(invokeParameter);

            //在feign中已经解压缩
            //String dataMsg = getCompress(invokeParameter.getMsgCompress(), invokeParameter.getDataMsg());
            //String dataMsg = invokeParameter.getDataMsg();

            //InvokeLogger.info("<=== Request", "dataMsg", invokeParameter.getMsgCompress() == 0 ? dataMsg : "<compressed>");

            //验证请求服务代码是否正确
            BusinessHandler service = getBusinessHandler(invokeParameter.getSerCode() + "." +invokeParameter.getVersionNo());
            if(service==null){
                throw new ServiceException(ResponseCodeEnum.UNDEFINED_SERVICE.getCode(), ResponseCodeEnum.UNDEFINED_SERVICE.getDesc());
            }
            //invokeParameter.setDataMsg(dataMsg);
            InvokeResponse response = service.invokeBusiness(invokeParameter);

            long cost = System.currentTimeMillis() - start;
            InvokeLogger.info("===> Response(" + cost + "ms)", "response", response.toJson());

            return InvokeHelper.buildResponse(invokeParameter.getSerCode(), invokeParameter.getSysTraceNo(), invokeParameter.getOriginNo(), invokeParameter.getVersionNo(), response.toJson());
        }catch (ServiceException e) {
            InvokeLogger.error("InvokeServiceImpl.invoke throws error", e);
            Map<String, String> result = new HashMap<String, String>();

            result.put("responseCode", e.getCode());
            result.put("responseDesc", e.getDesc());
            InvokeLogger.error("response:" + JSONUtil.toJson(result));
            return InvokeHelper.buildResponse(invokeParameter.getSerCode(), invokeParameter.getSysTraceNo(), invokeParameter.getOriginNo(), invokeParameter.getVersionNo(), JSONUtil.toJson(result));

        } catch (Exception e) {
            InvokeLogger.error("InvokeServiceImpl.invoke throws Exception error", e);
            Map<String, String> result = new HashMap<>();
            result.put("responseCode", ResponseCodeEnum.UNDEFINED_ERROR.getCode());
            result.put("responseDesc", ResponseCodeEnum.UNDEFINED_ERROR.getDesc());
            InvokeLogger.error("response:" + JSONUtil.toJson(result));
            return InvokeHelper.buildResponse(invokeParameter.getSerCode(), invokeParameter.getSysTraceNo(), invokeParameter.getOriginNo(), invokeParameter.getVersionNo(), JSONUtil.toJson(result));
        }
    }

    /**
     * 校验消息报文
     *
     * @return
     */
    private  void validate(InvokeParameter invokeParameter) throws ServiceException {
        InvokeHelper.validateReqParam(invokeParameter.getSerCode(), invokeParameter.getSysTraceNo(), invokeParameter.getOriginNo(),invokeParameter.getTargetNo(),
                invokeParameter.getVersionNo() , invokeParameter.getDataLength(), invokeParameter.getDataMsg());
        //验证请求消息正文内容长度
        InvokeHelper.validateDataMsgSize(invokeParameter.getDataLength(), invokeParameter.getDataMsg());
    }

    /**
     * 验证是否需要解压缩
     * @param msgCompress
     * @param dataMsg
     * @return
     * @throws ServiceException
     */
    private String getCompress(int msgCompress,String dataMsg) throws ServiceException{
        if(msgCompress==1){
            try {
                return  ZipUtil.uncompress(dataMsg);
            } catch (IOException e) {
                InvokeLogger.error(ResponseCodeEnum.UNCOMPRESS_FAILURE.getDesc(),e);
                throw new ServiceException(ResponseCodeEnum.UNCOMPRESS_FAILURE);
            }
        }
        return dataMsg;
    }

    public BusinessHandler getBusinessHandler(String serCode) {
        return businessHandlerMap.get(serCode);
    }
}
