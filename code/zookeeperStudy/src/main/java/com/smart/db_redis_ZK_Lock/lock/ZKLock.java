package com.smart.db_redis_ZK_Lock.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: yk
 * @Date: 2020/2/8 16:33
 */
public class ZKLock implements Lock {

    // zk客户端
    private ZooKeeper zk;

    // 目录结构，locks
    private String root = "/locks";

    // 锁名称
    private String lockName;

    // 当前线程创建的序列node
    private ThreadLocal<String> nodeId = new ThreadLocal<>();

    // 用来同步等待zkclient链接到了服务器
    private CountDownLatch connectedSignal = new CountDownLatch(1);
    private final static int sessionTimeout = 3000;
    private final static byte[] data = new byte[0];

    public ZKLock(String config, String lockName) {
        this.lockName = lockName;

        try {
            zk = new ZooKeeper(config, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 建立连接
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        connectedSignal.countDown();
                    }
                }
            });

            connectedSignal.await();
            Stat stat = zk.exists(root, false);
            if (null == stat) {
                // 创建根节点
                zk.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 添加watch聆听临时顺序节点的删除
    class LockWatcher implements Watcher {

        private CountDownLatch latch = null;

        public LockWatcher(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDeleted) {
                latch.countDown();
            }

        }
    }

    /**
     * 上锁
     */
    @Override
    public void lock() {
        try {
            // 创建临时子节点
            String myNode = zk.create(root + "/" + lockName, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + myNode + " created ");

            // 取出所有子节点,借助TreeSet排序
            List<String> subNode = zk.getChildren(root, false);
            TreeSet<String> sortedNodes = new TreeSet<>();
            for (String node : subNode) {
                sortedNodes.add(root + "/" + node);
            }

            String smallNode = sortedNodes.first();
            if (myNode.equals(smallNode)) {
                // 如果是最小节点，则表示取得锁
                System.out.println(Thread.currentThread().getName() + myNode + " get lock ");
                this.nodeId.set(myNode);
                return;
            }

            String preNode = sortedNodes.lower(myNode);

            // 借助CountDownLatch对象完成线程是同步等待
            CountDownLatch latch = new CountDownLatch(1);
            // 同时注册监听
            Stat stat = zk.exists(preNode, new LockWatcher(latch));
            // 判断比自己小一个数的节点是否存在，如果不存在则无需等待锁，同时注册监听
            if (stat != null) {
                System.out.println(Thread.currentThread().getName() + myNode + " waiting for " + root + "/" + preNode + " reeleased lock ");
                // 等待。这里应该一直等待其他线程释放锁
                latch.await();
                nodeId.set(myNode);
                latch = null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
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
     * 释放锁
     */
    @Override
    public void unlock() {
        try {
            System.out.println(Thread.currentThread().getName() + " unLock ");
            if (null != nodeId) {
                zk.delete(nodeId.get(), -1);
            }
            nodeId.remove();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
