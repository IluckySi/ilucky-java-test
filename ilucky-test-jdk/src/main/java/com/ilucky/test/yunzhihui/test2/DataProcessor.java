package com.ilucky.test.yunzhihui.test2;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author IluckySi
 *
 */
public class DataProcessor implements Runnable {

    static AtomicLong c = new AtomicLong(0);
    LinkedBlockingQueue<Map<String, Long>> l;
    
    public DataProcessor(LinkedBlockingQueue<Map<String, Long>> l) {
        this.l = l;
    }
    
    public void run() {
        while(true) {
            Map<String, Long> m = l.poll();
            if(m!= null) {
                // 统计数据
                c.getAndIncrement();
                for(Entry<String, Long> e : m.entrySet()) {
                    DataAggregate.put(e.getKey(), e.getValue());
                }
            }
            try {
                // 模拟业务处理
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


