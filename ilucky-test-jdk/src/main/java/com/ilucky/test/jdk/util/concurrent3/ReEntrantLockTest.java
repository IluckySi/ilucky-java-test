package com.ilucky.test.jdk.util.concurrent3;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 参考: https://blog.csdn.net/Somhu/article/details/78874634
 * 
 * @author IluckySi
 *
 */
public class ReEntrantLockTest implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition c = lock.newCondition();
    
    @Override
    public void run() {
        System.out.println(new Date() + ": begin(2000)...");
        try {
            lock.lock();
            c.await();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(new Date() + ": end(2000)...");
    }
    
    public static void main(String[] args) {
        ReEntrantLockTest r = new ReEntrantLockTest();
        Thread t = new Thread(r);
        t.start();
       
        try {
            System.out.println(new Date() + ": begin(3000)...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": end(3000)...");
        
        lock.lock();
        c.signal();
        lock.unlock();
        System.out.println(new Date() + ": end(1000)...");
    }
}
/**
输出结果:
Mon Aug 20 11:54:24 GMT+08:00 2018: begin(3000)...
Mon Aug 20 11:54:24 GMT+08:00 2018: begin(2000)...
Mon Aug 20 11:54:27 GMT+08:00 2018: end(3000)...
Mon Aug 20 11:54:27 GMT+08:00 2018: end(1000)...
Mon Aug 20 11:54:29 GMT+08:00 2018: end(2000)...
*/