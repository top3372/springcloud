package com.haili.ins.feign;



import com.haili.ins.core.invoke.dto.InvokeRequest;
import com.haili.ins.core.invoke.dto.InvokeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
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
     * member invoke
     * @param invokeRequest
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/v1/invoke")
    InvokeResponse invoke(@RequestBody InvokeRequest invokeRequest);

}