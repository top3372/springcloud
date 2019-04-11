package com.haili.ins.controller;

import com.haili.ins.common.cloud.version.annotation.ApiVersion;
import com.haili.ins.common.cloud.version.annotation.UrlVersion;
import com.haili.ins.common.dto.ResultInfo;
import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.dto.member.response.MemberInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: leon
 * @Date: 2019/4/11 20:24
 * @Version 1.0
 */
@RestController
@UrlVersion("1")
@RequestMapping("member")
public class MemberController {

    @RequestMapping("/info")
    public ResultInfo info(){
        return new ResultInfo(ResponseCodeEnum.SUCCESS);
    }

    @RequestMapping("/info2")
    @UrlVersion("1.2")

    public ResultInfo info2(){
        return new ResultInfo(ResponseCodeEnum.SUCCESS);
    }
}
