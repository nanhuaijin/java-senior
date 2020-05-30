package com.breeze.senior.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 循环栅栏
 */
public class CyclicBarrierMain {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("收集7颗龙珠可以召唤神龙"));

        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t颗龙珠已经被收集");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
