package com.ilucky.test.yunzhihui.test2;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author IluckySi
 *
 */
public class MainTest {

 private static LinkedBlockingQueue<Map<String, Long>> l = new LinkedBlockingQueue<Map<String, Long>>();
    
    public static void main(String[] args) {
        // 给个初始时间
        DataAggregate.start();
        
        // 产生模拟数据
        new Thread(new DataGenerator(l)).start();
        new Thread(new DataGenerator(l)).start();
        new Thread(new DataGenerator(l)).start();
        
        // 聚合数据发送
        new Thread(new DataAggregate()).start();
        
        // 处理模拟数据,并交给聚合器进行聚合
        new Thread(new DataProcessor(l)).start();
        new Thread(new DataProcessor(l)).start();
        new Thread(new DataProcessor(l)).start();
        
        // 模拟
        new Thread(new Monitor()).start();
    }
}
