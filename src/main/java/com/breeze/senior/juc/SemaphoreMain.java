package com.breeze.senior.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 信号量
 */
public class SemaphoreMain {
    public static void main(String[] args) {

        //并发容器容量3
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5) + 5);
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }

    }
}
