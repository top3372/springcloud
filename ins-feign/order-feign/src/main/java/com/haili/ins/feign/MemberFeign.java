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

@Component
@FeignClient(name = "member-server")
/**
 * @author mali
 */
public interface MemberFeign {

    /**
     * invoke调用服务
     *
     * @param version
     * @param invokeRequest
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/{version}/invoke")
    InvokeResponse invoke(@PathVariable(value="version") String version,
                          @RequestBody InvokeRequest invokeRequest);

}