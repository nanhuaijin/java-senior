package com.breeze.senior.lock;

/**
 * @author : breeze
 * @date : 2020/5/26
 * @description : 死锁验证Main方法
 */
public class DeadLockMain {
    public static void main(String[] args) {

        new Thread(new DeadLock("lockA", "lockB"), "AAAA").start();
        new Thread(new DeadLock("lockB", "lockA"), "BBBB").start();

    }
}
