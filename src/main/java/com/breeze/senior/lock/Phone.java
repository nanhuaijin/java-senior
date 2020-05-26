package com.breeze.senior.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/26
 * @description : 模拟手机类
 */
public class Phone {
    //发邮件
    public synchronized void sendEmail() {
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("========Email=========");
    }
    //发短信
    public synchronized void sendSMS() {
        System.out.println("=======SMS============");
    }

    public void hello() {
        System.out.println("========Hello=========");
    }
}
