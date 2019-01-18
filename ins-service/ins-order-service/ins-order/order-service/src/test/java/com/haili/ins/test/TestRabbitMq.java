package com.haili.ins.test;

import com.haili.ins.OrderApplication;
import com.haili.ins.service.rabbitmq.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: LeonMa
 * @date: 2019/01/08 11:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class TestRabbitMq {

    @Autowired
    private SenderService senderService;

    @Test
    public void testFind() {
        System.out.println("测试开始");
        senderService.send();
    }
}
