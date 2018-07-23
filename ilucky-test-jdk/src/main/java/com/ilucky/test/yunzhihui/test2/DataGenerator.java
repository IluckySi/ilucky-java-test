package com.ilucky.test.yunzhihui.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author IluckySi
 *
 */
public class DataGenerator implements Runnable {

    static AtomicLong c = new AtomicLong(0);
    LinkedBlockingQueue<Map<String, Long>> l;
    
    public DataGenerator(LinkedBlockingQueue<Map<String, Long>> l) {
        this.l = l;
    }
    
    public void run() {
        outer: while(true) {
            for(int i=0; i<10; i++) {
                for(int j=0; j<100; j++) {
                    if(c.intValue() >= 900000) {
                        break outer;
                    }
                    
                    // 统计
                    c.incrementAndGet();
                    
                    // 模拟数据
                    Map<String, Long> m = new HashMap<String, Long>();
                    m.put("uri"+i, j*2l);
                    l.add(m);
                }
            }  
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
