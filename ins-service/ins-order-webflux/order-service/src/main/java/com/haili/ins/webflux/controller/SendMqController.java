package com.haili.ins.webflux.controller;

import com.haili.ins.service.rabbitmq.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LeonMa
 * @date: 2019/01/09 14:35
 */
@RestController
public class SendMqController {

    @Autowired
    private SenderService senderService;

    @RequestMapping("/sendMq")
    public void send(){
        senderService.send();
    }

}
