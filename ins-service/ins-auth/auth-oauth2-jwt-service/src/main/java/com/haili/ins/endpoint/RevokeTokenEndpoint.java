package com.haili.ins.endpoint;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.response.ResponseMessage;
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
    public ResponseMessage revokeToken(String access_token) {

        if (consumerTokenServices.revokeToken(access_token)){
            return new ResponseMessage(ResponseCodeEnum.SUCCESS,"注销成功");
        }else{
            return new ResponseMessage(ResponseCodeEnum.LOGOUT_FAILURE,"注销失败");
        }
    }
}