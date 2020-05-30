package com.breeze.senior.reentrant.lock;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 生产消费者 - 传统版本
 */
public class AirConditionerMain {
    public static void main(String[] args) {

        AirConditioner airConditioner = new AirConditioner();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try { airConditioner.increment(); } catch (Exception e) { e.printStackTrace(); }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try { airConditioner.decrement(); } catch (Exception e) { e.printStackTrace(); }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try { airConditioner.increment(); } catch (Exception e) { e.printStackTrace(); }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try { airConditioner.decrement(); } catch (Exception e) { e.printStackTrace(); }
            }
        }, "D").start();
    }
}
