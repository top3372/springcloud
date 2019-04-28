package com.haili.ins.feign;


import com.haili.ins.common.annotation.version.ApiVersion;
import com.haili.ins.common.invoke.dto.InvokeRequest;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import com.haili.ins.common.vo.ResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mali
 */
@Component
@FeignClient(name = "member-server")
@ApiVersion("1")
public interface MemberFeign {

    /**
     * invoke调用服务
     *
     * @param invokeRequest
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ApiVersion("v1")
    @RequestMapping("/api/invoke")
    InvokeResponse invoke(@RequestBody InvokeRequest invokeRequest);

    @RequestMapping("/member/info")
    ResultInfo<String> info();

    @RequestMapping("/member/info2")
    @ApiVersion("1.2")
    ResultInfo<String> info2();

}