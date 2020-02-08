package com.smart.db_redis_Lock.test;

import com.smart.db_redis_Lock.lock.DbLock;
import com.smart.db_redis_Lock.lock.RedisLock;
import com.smart.singleLock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Redis实现分布式锁
 *
 * @Author: yk
 * @Date: 2020/2/8 12:53
 */
public class testRedisLock {

    @Autowired
    private static RedisLock redisLock;

    static {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("smart-context.xml");
        redisLock = classPathXmlApplicationContext.getBean(RedisLock.class);
    }

    static class StockThread implements Runnable {

        @Override
        public void run() {
            // 上锁
            redisLock.lock();
            // 调用减少库存的方法
            boolean b = new Stock().reduceStock();
            // 解锁
            redisLock.unlock();
            if (b) {
                System.out.println(Thread.currentThread().getName() + "减少库存成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "减少库存失败");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new testRedisLock.StockThread(), "线程1").start();
        new Thread(new testRedisLock.StockThread(), "线程2").start();

    }
}
