package com.smart.db_redis_ZK_Lock.test;

import com.smart.db_redis_ZK_Lock.lock.RedisLock;
import com.smart.singleLock.Stock;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Redis实现分布式锁
 *
 * @Author: yk
 * @Date: 2020/2/8 12:53
 */
public class testRedisLock {

    // redis实现分布式锁
    private static RedisLock redisLock;

    //redission实现分布式锁（可解决redis方式出现的死锁问题）
    private static RLock myLock;

    static {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("smart-context.xml");
        redisLock = classPathXmlApplicationContext.getBean(RedisLock.class);

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        Redisson redisson = (Redisson)Redisson.create(config);
        myLock = redisson.getLock("redis_lock_stock");
    }

    static class StockThread implements Runnable {

        @Override
        public void run() {
            // 上锁
            // redisLock.lock();
            myLock.lock();

            // 调用减少库存的方法
            boolean b = new Stock().reduceStock();

            // 解锁
            // redisLock.unlock();
            myLock.unlock();

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
