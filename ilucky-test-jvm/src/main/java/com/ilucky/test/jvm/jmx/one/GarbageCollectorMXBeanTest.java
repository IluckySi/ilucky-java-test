package com.ilucky.test.jvm.jmx.one;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 垃圾回收器时间: 青年代和老年代
 * 
 * @author IluckySi
 *
 */
public class GarbageCollectorMXBeanTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        showGarbageCollector();
        List<byte[]> list = new ArrayList<byte[]>();
        for(int i= 0; i< 10000; i++) {
            byte[] b = new byte[1024];
            list.add(b);
            if(i / 10 == 0) {
                list = null;
                // 手动gc
                System.gc();
                list = new ArrayList<byte[]>();
            }
        }
        try {
            // 模拟业务
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(showGarbageCollector() * 100.0/(end - start) + "%");
    }
    
    public static int showGarbageCollector() {
        int sum = 0;
        System.out.println("------getGarbageCollectorMXBeans------");
        
        // Returns a list of GarbageCollectorMXBean objects in the Java virtual machine. 
        // The Java virtual machine may have one or more GarbageCollectorMXBean objects. It may add or remove GarbageCollectorMXBean during execution.
        List<GarbageCollectorMXBean> mxbean = ManagementFactory.getGarbageCollectorMXBeans();
        
        for (GarbageCollectorMXBean gc : mxbean) {
            // Returns the name representing this memory manager.
            System.out.println("getName:" + gc.getName());
            // Returns the approximate accumulated collection elapsed time in milliseconds. This method returns -1 if the collection elapsed time is undefined for this collector. 
            System.out.println("getCollectionTime: " + gc.getCollectionTime());
            
            sum+=gc.getCollectionTime();
        }
        return sum;
    }
}
