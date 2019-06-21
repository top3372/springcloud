package com.haili.ins.common.invoke.controller;

import com.haili.ins.common.annotation.version.ApiVersion;
import com.haili.ins.common.invoke.InvokeHelper;
import com.haili.ins.common.invoke.InvokeService;
import com.haili.ins.common.invoke.dto.InvokeParameter;
import com.haili.ins.common.invoke.dto.InvokeRequest;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import com.haili.ins.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@ApiVersion("v1")
public class InvokeController {

    @Resource
    private InvokeService invokeService;

//    @Value("#{'${haili.api.version:1}'.split(',')}")
//    private List<String> supportVersionList;

    @PostMapping("/api/invoke")
    public InvokeResponse invoke(@RequestBody InvokeRequest invokeRequest) {
        InvokeResponse invokeResponse = new InvokeResponse();
        //判断版本
//        String vSeq = version.substring(1);
//        if(supportVersionList != null && supportVersionList.size() != 0){
//            if(!supportVersionList.contains(vSeq)){
//                invokeResponse.build(ResponseCodeEnum.API_VERSION_ERROR);
//                return invokeResponse;
//            }
//        }
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();

        InvokeParameter parameter = new InvokeParameter();
        BeanUtils.copyProperties(invokeRequest, parameter);
        parameter.setVersionNo("v1");

        invokeResponse = InvokeHelper.invoke(invokeService, parameter);

        return invokeResponse;
    }

}
