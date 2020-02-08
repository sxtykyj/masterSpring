package com.smart.dbLock;

import com.smart.dbLock.bean.LockRecord;
import com.smart.dbLock.mapper.LockRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: yk
 * @Date: 2020/2/7 11:33
 */
@Component
public class DbLock implements Lock {

    private static final String LOCK_NAME = "db_lock_stock";

    @Autowired
    private LockRecordMapper lockRecordMapper;

    /**
     * 上锁
     */
    @Override
    public void lock() {
        while (true) {
            boolean b = tryLock();
            if (b) {
                // 添加记录
                LockRecord lockRecord = new LockRecord();
                lockRecord.setLockName(LOCK_NAME);
                lockRecordMapper.insert(lockRecord);
                return;
            } else {
                System.out.println("--- 等待中......");
            }
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 尝试获取锁，根据指定的名称在数据库表中发起一次查询
     * <p>
     * sql： select * from lock_record where lock_name = "db_lock_stock"
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        Example example = new Example(LockRecord.class);
        example.createCriteria().andEqualTo("lockName", LOCK_NAME);
        LockRecord lockRecord = lockRecordMapper.selectOneByExample(example);
        if (lockRecord == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 解锁
     * 本质：删除指定名称的记录
     */
    @Override
    public void unlock() {
        Example example = new Example(LockRecord.class);
        example.createCriteria().andEqualTo("lockName", LOCK_NAME);
        lockRecordMapper.deleteByExample(example);

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
