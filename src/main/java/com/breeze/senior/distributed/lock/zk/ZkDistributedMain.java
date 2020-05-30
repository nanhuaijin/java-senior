package com.breeze.senior.distributed.lock.zk;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 主启动类 测试
 */
public class ZkDistributedMain {

    OrderNumberUtil orderNumberUtil = new OrderNumberUtil();
    private ZkLock zkLock = new ZkDistributedLock();

    public void getOrderNumber() {
        zkLock.lock();
        try {
            System.out.println("订单编号：" + orderNumberUtil.getOrderNum());
        } finally {
            zkLock.unlock();
        }
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                new ZkDistributedMain().getOrderNumber();
            }, String.valueOf(i)).start();
        }
    }
}
