package com.smart.db_redis_Lock.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: yk
 * @Date: 2020/2/8 12:48
 */
@Component
public class RedisLock implements Lock {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK_NAME = "redis_lock_stock";

    /**
     * 上锁
     */
    @Override
    public void lock() {
        while (true) {
            Boolean b = redisTemplate.opsForValue().setIfAbsent("lockName", LOCK_NAME);
            if (b) {
                // 设置过期时间，解决Redis的死锁问题
                redisTemplate.expire("lockName",15,TimeUnit.SECONDS);
                return;
            } else {
                System.out.println("循环等待中......");
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        redisTemplate.delete("lockName");

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
