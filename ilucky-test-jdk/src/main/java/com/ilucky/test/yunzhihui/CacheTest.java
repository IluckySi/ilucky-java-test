package com.ilucky.test.yunzhihui;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * min-max-count-sum
 * @author IluckySi
 *
 */
public class CacheTest {

    // static ConcurrentHashMap<String, long[]> cache = new ConcurrentHashMap<String,long[]>();
    static ConcurrentHashMap<String, AtomicLong[]> cache = new ConcurrentHashMap<String, AtomicLong[]>();
    
    static ReentrantLock lock = new ReentrantLock();
    
    // 线程不安全
   // public static void put(String key, long time) {
      // 线程安全: 使用重量级锁synchronized
//    public synchronized static void put(String key, long time) {
//        long[] l = cache.get(key);
//        if(l == null) {
//            l = new long[4];
//            cache.put(key, l);
//        }
//        if(time < l[0]) {
//            l[0] = time;
//        }
//        if(time > l[1]) {
//            l[1] = time;
//        }
//        l[2] = l[2] + time;
//        l[3] = l[3] + 1;
//    }
//    
    // 线程不安全:虽然ConcurrentHashMap和AtomicLong都是线程安全的,但是中间存在不安全的操作
    // public static void put(String key, long time) {
    // 线程安全: 使用重量级锁synchronized
//    public synchronized static void put(String key, long time) {
//        AtomicLong[] l = cache.get(key);
//        if(l == null) {
//            l = new AtomicLong[4];
//            l[0]=l[1]=l[2]=l[3]=new AtomicLong(0);
//            cache.put(key, l);
//        }
//        if(time < l[0].get()) {
//            l[0] = new AtomicLong(time);
//        }
//        if(time > l[1].get()) {
//            l[1] = new AtomicLong(time);
//        }
//        l[2] = new AtomicLong(l[2].get() + time);
//        l[3] = new AtomicLong(l[3].get() + 1);
//    }
    // 线程安全: 使用重入锁
    public static void put(String key, long time) {
        lock.lock();
        AtomicLong[] l = cache.get(key);
        if(l == null) {
            l = new AtomicLong[4];
            l[0]=l[1]=l[2]=l[3]=new AtomicLong(0);
            cache.put(key, l);
        }
        if(time < l[0].get()) {
            l[0] = new AtomicLong(time);
        }
        if(time > l[1].get()) {
            l[1] = new AtomicLong(time);
        }
        l[2] = new AtomicLong(l[2].get() + time);
        l[3] = new AtomicLong(l[3].get() + 1);
        lock.unlock();
    }
    
//    public static void print() {
//        for(Entry<String, long[]> entry : cache.entrySet()) {
//            long [] l = entry.getValue();
//            System.out.println(entry.getKey()+"----"+l[0]+"---"+l[1]+"---"+l[2]+"---"+l[3]);
//        }
//    }
    
    public static void print() {
        for(Entry<String, AtomicLong[]> entry : cache.entrySet()) {
            AtomicLong[] l = entry.getValue();
            System.out.println(entry.getKey()+"----"+l[0]+"---"+l[1]+"---"+l[2]+"---"+l[3]);
        }
    }
}
