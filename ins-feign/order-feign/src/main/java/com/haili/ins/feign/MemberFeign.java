package com.haili.ins.feign;


import com.haili.ins.common.cloud.version.annotation.UrlVersion;
import com.haili.ins.common.dto.ResultInfo;
import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.invoke.dto.InvokeRequest;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author mali
 */
@Component
@FeignClient(name = "member-server")
@UrlVersion("1")
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
    ResultInfo info();

    @RequestMapping("/member/info2")
    @UrlVersion("1.2")
    ResultInfo info2();

}