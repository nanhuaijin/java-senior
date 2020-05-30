package com.breeze.senior.distributed.lock.zk;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 编写抽象类
 */
public abstract class ZkAbstractLock implements ZkLock{

    private static final String ZK_SERVER = "localhost:2181";

    private static final int TIME_OUT = 45 * 1000;

    //创建Zk客户端
    ZkClient zkClient = new ZkClient(ZK_SERVER, TIME_OUT);

    String path = "/zkLock";
    CountDownLatch countDownLatch = null;

    public abstract boolean tryLock();
    public abstract void waitLock();

    @Override
    public void lock() {
        //尝试抢占锁
        if (tryLock()) {
            System.out.println(Thread.currentThread().getName() + "\t抢占锁成功！");
        } else {
            waitLock();

            //递归调用加锁方法
            lock();
        }
    }

    @Override
    public void unlock() {
        if (zkClient != null) {
            zkClient.close();
        }
        System.out.println(Thread.currentThread().getName() + "\t 释放锁成功");
        System.out.println();
        System.out.println();
    }
}
