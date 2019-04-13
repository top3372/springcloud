package com.haili.ins.controller;

import com.haili.ins.common.cloud.version.annotation.ApiVersion;
import com.haili.ins.common.security.access.prepost.annotations.PreAuth;
import com.haili.ins.common.vo.ResultInfo;
import com.haili.ins.enums.ResponseCodeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: leon
 * @Date: 2019/4/11 20:24
 * @Version 1.0
 */
@RestController
@ApiVersion("1")
@RequestMapping("member")
public class MemberController {

    //hasPermission('user::add')
    @RequestMapping("/info")
    @PreAuth("@permissionService.permitAll()")
    public ResultInfo<String> info(){

        return ResultInfo.success("success",ResponseCodeEnum.SUCCESS.getDesc());
    }

    @RequestMapping("/info2")
    @ApiVersion("1.2")
    @PreAuth("@permissionService.denyAll()")
    public ResultInfo info2(){

        return ResultInfo.success("success",ResponseCodeEnum.SUCCESS.getDesc());
    }
}
