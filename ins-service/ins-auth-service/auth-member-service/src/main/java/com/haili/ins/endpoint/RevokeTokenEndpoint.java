package com.haili.ins.endpoint;

import com.haili.ins.common.vo.ResultInfo;
import com.haili.ins.enums.ResponseCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangyunfei on 2017/7/24.
 * 注销token
 */
@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public ResultInfo revokeToken(String access_token) {

        if (consumerTokenServices.revokeToken(access_token)){
            return new ResultInfo(ResponseCodeEnum.SUCCESS.getCode(),"注销成功");
        }else{
            return new ResultInfo(ResponseCodeEnum.LOGOUT_FAILURE.getCode(),"注销失败");
        }
    }
}