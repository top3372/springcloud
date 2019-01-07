package com.haili.ins.test;


import com.haili.ins.service.RedisLockCallBack;
import com.haili.ins.utils.RedisLockUtil;
import com.haili.ins.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisUtil.class)
public class TestClass {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisLockUtil redisLockUtil;

    @Test
    public void testFind() {
        System.out.println("测试开始");
        redisLockUtil.reentrantLock("lockName", 3, 10,
                TimeUnit.SECONDS, new RedisLockCallBack() {
            @Override
            public void onRedisCallBack() {

            }
        });

    }
}