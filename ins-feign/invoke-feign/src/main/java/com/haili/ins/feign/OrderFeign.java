package com.haili.ins.feign;


import com.haili.ins.common.invoke.dto.InvokeRequest;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@FeignClient(name = "order-server")
public interface OrderFeign {

    /**
     * member invoke
     * @param invokeRequest
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/invoke")
    InvokeResponse invoke(@RequestBody InvokeRequest invokeRequest);
}
