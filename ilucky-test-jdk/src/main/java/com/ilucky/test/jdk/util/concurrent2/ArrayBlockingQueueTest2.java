package com.ilucky.test.jdk.util.concurrent2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 队列的常用方法: put和take
 * @author IluckySi
 *
 */
public class ArrayBlockingQueueTest2 implements Runnable {

    static ArrayBlockingQueue<String> q = new ArrayBlockingQueue<String>(5);
    
    public static void main(String[] args) {
        test1(); // 会一直阻塞在put方法,jvm进程一直活着
        System.out.println("--------------");
        test2();
    }
    
    /**
     * put和take方法的使用:
     * put: 向队列添加数据, 如果队列已满, 则阻塞.
     * take: 获取队列数据, 如果队列已空, 则阻塞.
     */
    private static void test1() {
        try {
            for(int i=0; i<6; i++) {
                q.put("Test-"+i);
            }
            for(int i=0; i<6; i++) {
                String str = q.take();
                System.out.println("Take: "+str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            // Thread.sleep(4000);
            System.out.println("Take: " + q.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
      
    /**
     * put和take超时方法的使用:
     */
    private static void test2() {
        try {
            new Thread(new ArrayBlockingQueueTest2()).start();
            for(int i=0; i<6; i++) {
                q.put("Test-"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
1. Thread.sleep(2000);
2. Thread.sleep(4000);
输出结果一样(注意结合jvm进程状态):
--------------
Take: Test-0
*/