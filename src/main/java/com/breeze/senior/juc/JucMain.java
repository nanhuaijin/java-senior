package com.breeze.senior.juc;

import javafx.beans.binding.When;

import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/25
 * @description : JUC main方法
 */
public class JucMain {
    public static void main(String[] args) {

        // JucMain.visibility();
        JucMain.unAtomicity();
    }

    /**
     * 如何保证原子性
     *  1.加synchronize锁
     *  2.使用juc下AtomicInteger
     */
    public static void unAtomicity() {
        Data data = new Data();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000 ; j++) {
                    data.addPlus();
                    data.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //需要等上面的20个线程全部执行完，才可以继续走
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\tfinally number value: " + data.number);
        System.out.println(Thread.currentThread().getName() + "\tmission is over atomic value: " + data.atomicInteger);
    }

    /**
     * 验证volatile可见性
     */
    public static void visibility() {
        Data data = new Data();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tcome in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

            data.numberTo60();
            System.out.println(Thread.currentThread().getName() + "\tupdate number value: " + data.number);
        }, "A").start();

        while (data.number == 0) {
            //不加volatile，程序会一直在这里打转，下面的mission is over不会执行
            //加了volatile 下面会打印
        }

        System.out.println(Thread.currentThread().getName() + "\tmission is over main number value: " + data.number);
    }
}
