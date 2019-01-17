package com.haili.ins.utils;

import com.haili.ins.service.RedisLockCallBack;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: LeonMa
 * @date: 2019/01/07 13:07
 */
@Slf4j
@Component
public class RedisLockUtil {

    @Resource
    private RedissonClient redissonClient;


    private void lock(RLock lock,int waitTime,int autoUnLockTime,TimeUnit timeUnit,RedisLockCallBack redisLockCallBack){
        try {
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);

            boolean res = lock.tryLock(waitTime, autoUnLockTime, timeUnit);
            if (res) {
                log.info("###########LockName: " +  lock.getName() +" 加锁成功" );
                redisLockCallBack.onRedisCallBack();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            log.info("###########LockName: " +  lock.getName() +" 解锁成功" );
        }
    }

    private void lockAsync(RLock lock,int waitTime,int autoUnLockTime,TimeUnit timeUnit,RedisLockCallBack redisLockCallBack){
        try {
//          lock.lockAsync();
//          lock.lockAsync(10, TimeUnit.SECONDS);
            Future<Boolean> res = lock.tryLockAsync(waitTime, autoUnLockTime, timeUnit);
            if (res.get()) {
                log.info("###########异步LockName: " +  lock.getName() +" 加锁成功" );
                redisLockCallBack.onRedisCallBack();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            log.info("###########异步LockName: " +  lock.getName() +" 解锁成功" );
        }
    }

    /**
     * 可重入锁（Reentrant Lock）
     * @param lockName
     * @param waitTime
     * @param autoUnLockTime
     * @param timeUnit
     * @param redisLockCallBack
     */
    public void reentrantLock(String lockName,int waitTime,int autoUnLockTime,TimeUnit timeUnit,RedisLockCallBack redisLockCallBack) {
        RLock lock = redissonClient.getLock(lockName);
        this.lock(lock,waitTime,autoUnLockTime,timeUnit,redisLockCallBack);
    }

    /**
     * 可重入锁异步（Reentrant Lock）
     * @param waitTime
     * @param autoUnLockTime
     * @param timeUnit
     * @param redisLockCallBack
     */
    public void reentrantLockAsync(String lockName,int waitTime,int autoUnLockTime,TimeUnit timeUnit,RedisLockCallBack redisLockCallBack) {
        RLock lock = redissonClient.getLock(lockName);
        this.lockAsync(lock,waitTime,autoUnLockTime,timeUnit,redisLockCallBack);
    }


    /**
     * 公平锁（Fair Lock）
     * @param lockName
     * @param waitTime
     * @param autoUnLockTime
     * @param timeUnit
     * @param redisLockCallBack
     */
    public void fairLock(String lockName,int waitTime,int autoUnLockTime,TimeUnit timeUnit,RedisLockCallBack redisLockCallBack) {
        RLock lock = redissonClient.getFairLock(lockName);
        this.lock(lock,waitTime,autoUnLockTime,timeUnit,redisLockCallBack);
    }


    /**
     * 公平锁异步（Fair Lock）
     * @param lockName
     * @param waitTime
     * @param autoUnLockTime
     * @param timeUnit
     * @param redisLockCallBack
     */
    public void fairLockAsync(String lockName,int waitTime,int autoUnLockTime,TimeUnit timeUnit,RedisLockCallBack redisLockCallBack) {
        RLock lock = redissonClient.getFairLock(lockName);
        this.lockAsync(lock,waitTime,autoUnLockTime,timeUnit,redisLockCallBack);
    }











}
