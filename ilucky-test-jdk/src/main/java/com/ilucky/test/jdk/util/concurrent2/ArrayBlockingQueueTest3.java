package com.ilucky.test.jdk.util.concurrent2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 队列的常用方法: remove
 * @author IluckySi
 *
 */
public class ArrayBlockingQueueTest3 {

    static ArrayBlockingQueue<String> q = new ArrayBlockingQueue<String>(5);
    
    public static void main(String[] args) {
        test1(); 
    }
    
    private static void test1() {
        try {
           q.offer("1");
           q.offer("2");
           q.offer("1");
           q.offer("3");
           q.offer("1");
           
           q.remove("1");
           System.out.println(q);
           q.remove("2");
           System.out.println(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
[2, 1, 3, 1]
[1, 3, 1]
*/