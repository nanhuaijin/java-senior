package com.breeze.senior.volati1e;

import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/25
 * @description : JUC main方法
 */
public class VolatileMain {
    public static void main(String[] args) {

        VolatileMain.visibility();
        VolatileMain.unAtomicity();
    }

    /**
     * 如何保证原子性
     *  1.加synchronize锁
     *  2.使用juc下AtomicInteger
     */
    public static void unAtomicity() {
        VolatileData volatileData = new VolatileData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000 ; j++) {
                    volatileData.addPlus();
                    volatileData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //需要等上面的20个线程全部执行完，才可以继续走
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\tfinally number value: " + volatileData.number);
        System.out.println(Thread.currentThread().getName() + "\tmission is over atomic value: " + volatileData.atomicInteger);
    }

    /**
     * 验证volatile可见性
     */
    public static void visibility() {
        VolatileData volatileData = new VolatileData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tcome in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

            volatileData.numberTo60();
            System.out.println(Thread.currentThread().getName() + "\tupdate number value: " + volatileData.number);
        }, "A").start();

        while (volatileData.number == 0) {
            //不加volatile，程序会一直在这里打转，下面的mission is over不会执行
            //加了volatile 下面会打印
        }

        System.out.println(Thread.currentThread().getName() + "\tmission is over main number value: " + volatileData.number);
    }
}
