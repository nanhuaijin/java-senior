package com.breeze.senior.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : breeze
 * @date : 2020/5/26
 * @description : 手写自旋锁
 */
public class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\tinvoked myLock()");
        //如果是null就更新
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\tinvoked unLock()");
    }

}


