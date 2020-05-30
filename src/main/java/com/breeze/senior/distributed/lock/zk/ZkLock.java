package com.breeze.senior.distributed.lock.zk;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description :
 */
public interface ZkLock {
    /**
     * 加锁方法
     */
    void lock();

    /**
     * 解锁方法
     */
    void unlock();

}
