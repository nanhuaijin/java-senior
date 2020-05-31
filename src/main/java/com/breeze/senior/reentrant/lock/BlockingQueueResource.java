package com.breeze.senior.reentrant.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : breeze
 * @date : 2020/5/31
 * @description : 资源类 - 阻塞队列版本生消模式
 */
public class BlockingQueueResource {
    //默认开启生消模式
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public BlockingQueueResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产者
     * @throws Exception
     */
    public void produce() throws Exception {

        String data = null;
        boolean retValue;

        while (flag) {
            data = atomicInteger.getAndIncrement() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"大老板叫停了，停止生产！");
    }

    /**
     * 消费者
     * @throws Exception
     */
    public void consumer() throws Exception {

        String result = null;
        while (flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if (result == null || result.equalsIgnoreCase("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒没有取到蛋糕，消费退出！");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费蛋糕队列" + result + "成功！");
        }
    }

    public void stop() {
        this.flag = false;
    }

}
