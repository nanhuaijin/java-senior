package com.breeze.senior.volati1e;

/**
 * @author : breeze
 * @date : 2020/5/25
 * @description : Volatile解决单例模式指令重排问题
 *
 *  1.synchronize可以控制线程安全问题，但是会导致并发性下降
 *  2.多线程情况下，由于指令重排会出现安全性问题，所以需要Volatile
 *  3.多线程单例模式：需要DCL（double check lock）双端检锁机制
 */
public class SingletonByVolatile {

    private static volatile SingletonByVolatile instance = null;

    private SingletonByVolatile() {
        System.out.println(Thread.currentThread().getName() + "我是SingletonByVolatile构造方法");
    }

    public static SingletonByVolatile getInstance() {
        if (instance == null) {
            //锁前锁后分别校验（DCL）
            synchronized (SingletonByVolatile.class) {
                if (instance == null) {
                    instance = new SingletonByVolatile();
                }
            }
        }
        return instance;
    }
}
