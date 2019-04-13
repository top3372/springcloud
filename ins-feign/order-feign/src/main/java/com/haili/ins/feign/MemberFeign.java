package com.haili.ins.feign;


import com.haili.ins.common.cloud.version.annotation.ApiVersion;
import com.haili.ins.common.vo.ResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author mali
 */
@Component
@FeignClient(name = "member-server")
@ApiVersion("1")
public interface MemberFeign {

//    /**
//     * invoke调用服务
//     *
//     * @param version
//     * @param invokeRequest
//     * @return
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/api/{version}/invoke")
//    InvokeResponse invoke(@PathVariable(value="version") String version,
//                          @RequestBody InvokeRequest invokeRequest);

    @RequestMapping("/member/info")
    ResultInfo<String> info();

    @RequestMapping("/member/info2")
    @ApiVersion("1.2")
    ResultInfo<String> info2();

}