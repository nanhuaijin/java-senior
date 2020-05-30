package com.breeze.senior.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 减少计数
 */
public class CountDownLatchMain {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t号同学离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("班长离开教室，并且锁门");
    }
}
