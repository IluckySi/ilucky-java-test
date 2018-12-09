package com.ilucky.test.jdk.util.concurrent2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列的常用方法: offer和poll
 * @author IluckySi
 *
 */
public class ArrayBlockingQueueTest implements Runnable {

    static ArrayBlockingQueue<String> q = new ArrayBlockingQueue<String>(5);
    
	public static void main(String[] args) {
		test1();
		System.out.println("--------------");
		test2();
	}
	
	/**
	 * offer和poll方法的使用:
	 * offer: 向队列添加数据, 如果队列已满, 则返回false.
	 * poll: 获取队列数据, 如果队列已空, 则返回null.
	 */
	private static void test1() {
		for(int i=0; i<6; i++) {
			System.out.println("Offer: "+q.offer("Test-"+i));
		}
		for(int i=0; i<6; i++) {
			String str = q.poll();
			System.out.println("Poll: "+str);
		}
	}
	
	@Override
    public void run() {
        try {
            // Thread.sleep(2000);
            Thread.sleep(4000);
            System.out.println("Poll: " + q.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	  
	/**
	 * offer和poll超时方法的使用:
	 */
	private static void test2() {
		try {
		    new Thread(new ArrayBlockingQueueTest()).start();
			for(int i=0; i<6; i++) {
				System.out.println("Offer: "+q.offer("Test-"+i, 3, TimeUnit.SECONDS));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
/**
1. Thread.sleep(2000);
Offer: true
Offer: true
Offer: true
Offer: true
Offer: true
Offer: false
Poll: Test-0
Poll: Test-1
Poll: Test-2
Poll: Test-3
Poll: Test-4
Poll: null
--------------
Offer: true
Offer: true
Offer: true
Offer: true
Offer: true
Poll: Test-0
Offer: true

2. Thread.sleep(4000);
Offer: true
Offer: true
Offer: true
Offer: true
Offer: true
Offer: false
Poll: Test-0
Poll: Test-1
Poll: Test-2
Poll: Test-3
Poll: Test-4
Poll: null
--------------
Offer: true
Offer: true
Offer: true
Offer: true
Offer: true
Offer: false
Poll: Test-0
*/