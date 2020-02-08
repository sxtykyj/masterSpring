package com.smart.singleLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yk
 * @Date: 2020/2/7 11:08
 *
 * 单机情况下的锁机制Demo
 */
public class testStock {

    private static Lock lock = new ReentrantLock();

    static class StockThread implements Runnable {

        @Override
        public void run() {
            // 上锁
            lock.lock();
            // 调用减少库存的方法
            boolean b = new Stock().reduceStock();
            // 解锁
            lock.unlock();
            if (b) {
                System.out.println(Thread.currentThread().getName() + "减少库存成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "减少库存失败");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new StockThread(), "线程1").start();
        new Thread(new StockThread(), "线程2").start();

    }
}
