package com.haili.ins.common.invoke.bussiness;

import com.haili.ins.common.invoke.dto.InvokeParameter;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import com.haili.ins.exception.ServiceException;

/**
 * @author
 * @Date
 * @Description 对内统一处理业务接口
 */
public interface BusinessHandler {
    /**
     * 处理接口
     *
     * @return
     */
    InvokeResponse invokeBusiness(InvokeParameter invokeParam) throws ServiceException;

}
