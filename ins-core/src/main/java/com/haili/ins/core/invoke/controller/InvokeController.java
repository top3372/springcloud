package com.haili.ins.core.invoke.controller;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.core.invoke.InvokeHelper;
import com.haili.ins.core.invoke.InvokeService;
import com.haili.ins.core.invoke.dto.InvokeParameter;
import com.haili.ins.core.invoke.dto.InvokeRequest;
import com.haili.ins.core.invoke.dto.InvokeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class InvokeController {

    @Autowired
    private InvokeService invokeService;

    @Value("#{'${haili.api.version:1}'.split(',')}")
    private List<String> supportVersionList;

    @PostMapping("/{version}/invoke")
    public InvokeResponse invoke(@PathVariable String version, @RequestBody InvokeRequest invokeRequest) {
        InvokeResponse invokeResponse = new InvokeResponse();
        //判断版本
        String vSeq = version.substring(1);
        if(supportVersionList != null && supportVersionList.size() != 0){
            if(!supportVersionList.contains(vSeq)){
                invokeResponse.build(ResponseCodeEnum.API_VERSION_ERROR);
                return invokeResponse;
            }
        }

        InvokeParameter parameter = new InvokeParameter();
        BeanUtils.copyProperties(invokeRequest,parameter);
        parameter.setVersionNo(version);

        invokeResponse = InvokeHelper.invoke(invokeService,parameter);

        return invokeResponse;
    }

}
