package com.haili.ins.feign;

import com.haili.ins.invoke.dto.InvokeRequest;
import com.haili.ins.invoke.dto.InvokeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: leon
 * @Date: 2019/3/11 14:16
 * @Version 1.0
 */
@Component
@FeignClient(name = "ins-encrypt")
public interface EncryptFeign {


    /**
     * encrypt加密
     *
     * @param invokeRequest
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/encrypt")
    InvokeResponse encrypt(@RequestBody InvokeRequest invokeRequest);

    /**
     * encrypt加密
     *
     * @param dataMsg
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/encryptedMsg")
    String encryptedMsg(String dataMsg);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/encryp/jwt/check")
    String jwtCheck(String jwt);
}
