package com.smart.db_redis_ZK_Lock.test;

import com.smart.db_redis_ZK_Lock.lock.RedisLock;
import com.smart.db_redis_ZK_Lock.lock.ZKLock;
import com.smart.singleLock.Stock;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ZooKeeper实现分布式锁
 *
 * @Author: yk
 * @Date: 2020/2/8 17:10
 */
public class testZKLock {
    private static ZKLock zkLock;

    static {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("smart-context.xml");
        zkLock = classPathXmlApplicationContext.getBean(ZKLock.class);

        zkLock = new ZKLock("127.0.0.1:2181","stock_zk");
    }

    static class StockThread implements Runnable {

        @Override
        public void run() {
            // 上锁
            zkLock.lock();

            // 调用减少库存的方法
            boolean b = new Stock().reduceStock();

            // 解锁
            zkLock.unlock();

            if (b) {
                System.out.println(Thread.currentThread().getName() + "减少库存成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "减少库存失败");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new testZKLock.StockThread(), "线程1").start();
        new Thread(new testZKLock.StockThread(), "线程2").start();

    }
}
