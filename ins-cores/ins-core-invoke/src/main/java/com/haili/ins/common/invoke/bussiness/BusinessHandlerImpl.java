package com.haili.ins.common.invoke.bussiness;

import com.haili.ins.common.invoke.dto.InvokeParameter;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import com.haili.ins.exception.ServiceException;
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
