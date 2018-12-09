package com.ilucky.test.jvm.jmx.one;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * ManagementFactory基本使用
 * 
 * @author IluckySi
 *
 */
public class MBeanDemo2 {

    public static void main(String[] args) {
        int count = Runtime.getRuntime().availableProcessors();
        
        Thread t = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
//                        int sum = 0;
//                        for(int i=0; i<20000;i++) {
//                            sum+=i;
//                        }
//                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        
        long id = t.getId();
        
        // 进行缓存
        long sum = showThread(id)/count;
        // System.out.println(sum);
        while(true) {
            long s = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            sum = showThread(id)/count - sum;
            long e = System.currentTimeMillis();
            long period = e-s;
            System.out.println(sum * 100.0 / (period * 1000000)+"%");
            System.out.println(sum + " - " + period * 10000);
        }
    }

    /** Java 虚拟机的线程信息 */
    public static long showThread(long tid) {
        ThreadMXBean mxbean = ManagementFactory.getThreadMXBean();
        long sum = 0;
        // System.out.println("getAllThreadIds:");
        for(long id : mxbean.getAllThreadIds()) {
            // System.out.println("threadId: " + id);
            // Returns the total CPU time for a thread of the specified ID in nanoseconds.
            // System.out.println("getThreadCpuTime: " + mxbean.getThreadCpuTime(id));
            // Returns the thread info for a thread of the specified id with no stack trace. This method is equivalent to calling: 
            // getThreadInfo(id, 0); 
            // System.out.println("getThreadInfo: " + mxbean.getThreadInfo(id));
            if(id==tid) {
                sum+=mxbean.getThreadCpuTime(id);
            }
        }
        return sum;
    }
}
