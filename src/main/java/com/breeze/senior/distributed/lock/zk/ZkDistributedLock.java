package com.breeze.senior.distributed.lock.zk;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 重写加锁和等待方法
 */
public class ZkDistributedLock extends ZkAbstractLock {

    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(path);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public void waitLock() {

        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };

        //开启监听
        zkClient.subscribeDataChanges(path, iZkDataListener);

        if (zkClient.exists(path)) {
            countDownLatch = new CountDownLatch(1);

            try {
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //关闭监听
        zkClient.unsubscribeDataChanges(path, iZkDataListener);
    }
}
