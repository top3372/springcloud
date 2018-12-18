package com.haili.ins.common.invoke.controller;

import com.haili.ins.common.invoke.InvokeHelper;
import com.haili.ins.common.invoke.InvokeService;
import com.haili.ins.common.invoke.dto.InvokeParameter;
import com.haili.ins.common.invoke.dto.InvokeRequest;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoke")
public class InvokeController {

    @Autowired
    private InvokeService invokeService;

    @PostMapping
    public InvokeResponse invoke(@RequestBody  InvokeRequest invokeRequest) {
        InvokeParameter parameter = new InvokeParameter();
        BeanUtils.copyProperties(invokeRequest,parameter);

        InvokeResponse invokeResponse = InvokeHelper.invoke(invokeService,parameter);

        return invokeResponse;
    }

}
