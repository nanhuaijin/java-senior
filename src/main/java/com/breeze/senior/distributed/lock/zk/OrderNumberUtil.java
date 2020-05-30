package com.breeze.senior.distributed.lock.zk;

/**
 * @author : breeze
 * @date : 2020/5/30
 * @description : 生成订单号工具类
 */
public class OrderNumberUtil {

    private static int number = 0;

    public String getOrderNum() {
        return "" + (++number);
    }

}
