package com.haili.ins.controller;

import com.haili.ins.common.vo.ResultInfo;
import com.haili.ins.dto.CustomUserDetails;
import com.haili.ins.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

    @GetMapping("/auth/userInfo")
    public ResultInfo<Map> user(Authentication authentication){
        log.info("获取userInfo开始");
        Map<String, String> map = new LinkedHashMap<>();

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        map.put("userName",userDetails.getOauth2User().getUsername());
        map.put("userId",userDetails.getOauth2User().getId());
        map.put("status",userDetails.getOauth2User().getStatus());
        map.put("roles",String.join(",", userDetails.getRoles()));
        map.put("resources",String.join(",", userDetails.getResources()));

        ResultInfo<Map> resultInfo = new ResultInfo<>(ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getDesc());
        resultInfo.setData(map);
        log.info("获取userInfo结束");
        return resultInfo;
    }

//    @RequestMapping("/error")
//    public String error(){
//
//        return user;
//    }

}