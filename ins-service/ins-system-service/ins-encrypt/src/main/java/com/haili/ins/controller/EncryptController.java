package com.haili.ins.controller;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.exception.ServiceException;
import com.haili.ins.invoke.dto.InvokeRequest;
import com.haili.ins.invoke.dto.InvokeResponse;
import com.haili.ins.service.EncryptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: leon
 * @Date: 2019/3/11 13:57
 * @Version 1.0
 */
@RestController
@Slf4j
public class EncryptController {

    @Resource
    private EncryptService encryptService;


    @RequestMapping(value = "/api/encrypt")
    public InvokeResponse encrypt(@RequestBody InvokeRequest invokeRequest) {
        try {
            return encryptService.encrypt(invokeRequest);
        } catch (Exception e) {
            throw new ServiceException(ResponseCodeEnum.ENCRYP_MACHINE_HANDLE_FAILURE);
        }
    }

    @RequestMapping(value = "/api/encryptedMsg")
    public String encryptedMsg(String dataMsg) {
        try {
            return encryptService.encryptedMsg(dataMsg);
        } catch (Exception e) {
            throw new ServiceException(ResponseCodeEnum.ENCRYP_MACHINE_HANDLE_FAILURE);
        }
    }
}
