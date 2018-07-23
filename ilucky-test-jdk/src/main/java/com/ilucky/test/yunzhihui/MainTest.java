package com.ilucky.test.yunzhihui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 何谓并行......
 * @author IluckySi
 * 主要关注: count数量
 *
 */
public class MainTest implements Runnable {

    private static AtomicLong c = new AtomicLong(0);
    private static LinkedBlockingQueue<Map<String, Long>> l = new LinkedBlockingQueue<Map<String, Long>>();
    private static CountDownLatch cdl = new CountDownLatch(3);
    
    public static void main(String[] args) {
        getTestData();
        long s = System.currentTimeMillis();
        new Thread(new MainTest()).start();
        new Thread(new MainTest()).start();
        new Thread(new MainTest()).start();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long e = System.currentTimeMillis();
        System.out.println(e-s);
        CacheTest.print();
        System.out.println(c.incrementAndGet());
    }
    
    
    private static void getTestData() {
        System.out.println("-------------start");
        for(int i=0; i<10; i++) {
            for(int j=0; j<100; j++) {
                Map<String, Long> m = new HashMap<String, Long>();
                m.put("uri"+i, j*2l);
                l.add(m);
            }
        }
        System.out.println("-------------end");
    }


    public void run() {
        while(true) {
            Map<String, Long> m = l.poll();
            if(m == null || m.size() == 0) {
                cdl.countDown();
                System.out.println(Thread.currentThread().getName());
                break;
            } 
            c.getAndIncrement();
            for(Entry<String, Long> e : m.entrySet()) {
                CacheTest.put(e.getKey(), e.getValue());
            }
        }
    }
}


