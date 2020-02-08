package com.smart.singleLock;

/**
 * @Author: yk
 * @Date: 2020/2/7 11:06
 */
public class Stock {

    // 库存数量
    private static int num = 1;

    // 减少库存数量的方法
    public boolean reduceStock() {
        if (num > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            num--;
            return true;
        } else {
            return false;
        }
    }
}
