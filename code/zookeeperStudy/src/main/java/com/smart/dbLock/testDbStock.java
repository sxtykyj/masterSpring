package com.smart.dbLock;

import com.smart.singleLock.Stock;
import com.smart.singleLock.testStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据库实现分布式锁
 *
 * @Author: yk
 * @Date: 2020/2/7 15:35
 */
public class testDbStock {

    @Autowired
    private static DbLock dbLock;

    static {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("smart-context.xml");
        dbLock = classPathXmlApplicationContext.getBean(DbLock.class);
    }

    static class StockThread implements Runnable {

        @Override
        public void run() {
            // 上锁
            dbLock.lock();
            // 调用减少库存的方法
            boolean b = new Stock().reduceStock();
            // 解锁
            dbLock.unlock();
            if (b) {
                System.out.println(Thread.currentThread().getName() + "减少库存成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "减少库存失败");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new testDbStock.StockThread(), "线程1").start();
        new Thread(new testDbStock.StockThread(), "线程2").start();

    }
}
