package com.breeze.senior.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author : breeze
 * @date : 2020/5/26
 * @description : 线程8锁
 *
 * 	1.同一个对象，两个同步方法，请问先发送邮件还是短信？               Email
 * 	2.同一个对象，邮件新增暂停4秒钟的方法，请问先打印邮件还是短信？     Email
 * 	3.同一个对象，新增普通的hello方法，请问先打印邮件还是hello?        hello
 * 	4.有两部手机，请问先打印邮件还是短信？                 SMS
 * 	5.两个静态同步方法，同一部手机，请问先打印邮件还是短信？ Email
 * 	6.两个静态同步方法，2部手机，请问先打印邮件还是短信？    Email
 * 	7.1个静态同步方法,1个普通同步方法，1部手机，请问先打印邮件还是短信？   SMS
 * 	8.1个静态同步方法,1个普通同步方法，2部手机，请问先打印邮件还是短信？   SMS
 */
public class lock8 {
    public static void main(String[] args) {
        Phone p1 = new Phone();
        Phone p2 = new Phone();

        new Thread(() -> {
            p1.sendEmail();
        }, "ThreadCommunication").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            p1.sendSMS();
            // p1.hello();
            // p2.sendSMS();
        }, "B").start();

    }
}
