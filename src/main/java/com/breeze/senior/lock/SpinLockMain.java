package com.breeze.senior.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/26
 * @description : 自旋锁主类验证
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock()方法并持有锁5秒
 * B随后进来发现当前有线程持有锁，不是null，所以通过自旋等待A释放锁
 */
public class SpinLockMain {
    public static void main(String[] args) {

        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.myLock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLock.unLock();
        }, "A").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            spinLock.myLock();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLock.unLock();
        }, "B").start();
    }
}
