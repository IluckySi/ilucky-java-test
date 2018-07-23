package com.ilucky.test.yunzhihui.test2;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * min-max-sum-count
 * @author IluckySi
 *
 */
public class DataAggregate implements Runnable {

    static HashMap<String, long[]> cache = new HashMap<String,long[]>();
    static ReentrantLock lock = new ReentrantLock();
    static long s = 0;
    
    /**
     * 记录开始时间
     */
    public static void start() {
        s = System.currentTimeMillis();
    }
    
    /**
     * 复位cache
     */
    public static void reset() {
        cache.clear();
        s = System.currentTimeMillis();
    }
   
    /**
     * @param key
     * @param time
     */
    public static void put(String key, long time) {
        lock.lock();
        long[] l = cache.get(key);
        if(l == null) {
            l = new long[4];
            l[0]=l[1]=l[2]=l[3]=0;
            cache.put(key, l);
        }
        if(time < l[0]) {
            l[0] = time;
        }
        if(time > l[1]) {
            l[1] = time;
        }
        l[2] = l[2] + time;
        l[3] = l[3] + 1;
        lock.unlock();
    }

    public void run() {
        while(true) {
            // 根据时间,并异步处理发送数据
            long e = System.currentTimeMillis();
            if(e - s >= 10000) {
                lock.lock();
                if(cache.size() > 0) {
                    HashMap<String, long[]> process = new HashMap<String,long[]>();
                    process.putAll(cache);
                    new Thread(new DataSender(s, e, process)).start();
                    reset();
                }
                lock.unlock();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
    
//    public static void print() {
//        for(Entry<String, long[]> entry : cache.entrySet()) {
//            long [] l = entry.getValue();
//            System.out.println(entry.getKey()+"----"+l[0]+"---"+l[1]+"---"+l[2]+"---"+l[3]);
//        }
//    }
}
