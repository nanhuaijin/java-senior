package com.breeze.senior.lock;

import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/26
 * @description : 死锁
 */
@AllArgsConstructor
public class DeadLock implements Runnable{

    private String lockA;
    private String lockB;

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "尝试获取：" + lockB);

            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB + "尝试获取：" + lockA);
            }
        }
    }
}
