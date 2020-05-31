package com.breeze.senior.reentrant.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/31
 * @description : 主启动 - 阻塞队列版本生消模式
 */
public class BlockingQueueMain {
    public static void main(String[] args) {
        BlockingQueueResource resource = new BlockingQueueResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动！");

            try {
                resource.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "PRO").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动！");
            System.out.println();
            System.out.println();

            try {
                resource.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "CON").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到了，大老板叫停！");
        resource.stop();

    }
}
