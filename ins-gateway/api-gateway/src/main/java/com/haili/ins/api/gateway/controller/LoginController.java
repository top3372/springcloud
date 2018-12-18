package com.haili.ins.api.gateway.controller;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.DisabledAccountException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    public String login(String username,String password) {
//        Subject user = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        try {
//            //shiro帮我们匹配密码什么的，我们只需要把东西传给它，它会根据我们在UserRealm里认证方法设置的来验证
//            user.login(token);
//            return "login success";
//        } catch (UnknownAccountException e) {
//            //账号不存在和下面密码错误一般都合并为一个账号或密码错误，这样可以增加暴力破解难度
//            return "账号不存在！";
//        } catch (DisabledAccountException e) {
//            return "账号未启用！";
//        } catch (IncorrectCredentialsException e) {
//            e.printStackTrace();
//            return "密码错误！";
//        } catch (Throwable e) {
//            return"未知错误！";
//        }
//
//    }
}
