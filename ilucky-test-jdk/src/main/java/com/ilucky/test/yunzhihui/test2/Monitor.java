package com.ilucky.test.yunzhihui.test2;

/**
 * 统计监测
 * @author IluckySi
 *
 */
public class Monitor implements Runnable {

    public void run() {
        while(true) {
            System.out.println("生产数据="+DataGenerator.c.get()+", 处理数据(聚合)="+DataProcessor.c.get()+", 数据发送="+DataSender.c.get());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
