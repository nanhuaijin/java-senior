package com.breeze.senior.reentrant.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 模拟空调资源类 - 普通版本
 */
public class AirConditioner {
    private int temperature = 0; //温度
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 升高温度
     * @throws Exception
     */
    public void increment() throws Exception {
        lock.lock();
        try {
            //1.如果温度不为0，等待
            while (temperature != 0) {
                condition.await();
            }
            //2.温度为0，升高1度
            temperature++;
            System.out.println(Thread.currentThread().getName()+"\t温度："+temperature);
            //3.通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 降低温度
     * @throws Exception
     */
    public void decrement() throws Exception{
        lock.lock();
        try {
            //1.如果温度为0，等待
            while (temperature == 0) {
                condition.await();
            }
            //2.温度为0，升高1度
            temperature--;
            System.out.println(Thread.currentThread().getName()+"\t温度："+temperature);
            //3.通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
