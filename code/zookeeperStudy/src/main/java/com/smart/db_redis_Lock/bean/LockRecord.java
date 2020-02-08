package com.smart.db_redis_Lock.bean;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: yk
 * @Date: 2020/2/7 12:34
 */
@Table(name = "lock_record")
public class LockRecord {
    @Id
    private Integer id;

    private String lockName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }
}
