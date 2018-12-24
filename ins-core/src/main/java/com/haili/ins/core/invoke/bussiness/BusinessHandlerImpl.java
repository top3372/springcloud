package com.haili.ins.core.invoke.bussiness;

import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.core.invoke.dto.InvokeParameter;
import com.haili.ins.core.invoke.dto.InvokeResponse;
import org.springframework.stereotype.Service;

/**
 * 默认有个空的实现类,为了再启动时,InvokeSevice 的Map不报错
 */
@Service
public class BusinessHandlerImpl implements BusinessHandler {


    @Override
    public InvokeResponse invokeBusiness(InvokeParameter invokeParam) throws ServiceException {
        return null;
    }
}
