package com.breeze.senior.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : breeze
 * @date : 2020/5/25
 * @description : 多线程资源类
 */
public class Data {
    volatile int number = 0;
    boolean flag = false;
    /**
     * 改变成员变量值的方法
     */
    public void changeValue() {
        number = 1;
        flag = true;
    }
    public void addNumber() {
        if (flag) {
            number += 5;
            System.out.println(Thread.currentThread().getName() + "\tnumber：" + number);
        }
    }
    public void numberTo60() {
        this.number = 60;
    }
    /**
     * 注意：测试的时候，此时的number是由volatile修饰的，测试其不能保证原子性
     */
    public void addPlus() {
        number++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    /**
     * 验证AtomicInteger保证原子性
     */
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}
