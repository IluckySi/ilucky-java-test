package com.ilucky.test.jdk.util.concurrent2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列的常用方法
 * @author IluckySi
 *
 */
public class ArrayBlockingQueueTest {

	public static void main(String[] args) {
		test1();
	}
	
	/**
	 * offer和poll方法的使用:
	 * offer: 向队列添加数据, 如果队列已满, 则返回false.
	 * poll: 获取队列数据, 如果队列已空, 则返回null.
	 */
	private static void test1() {
		ArrayBlockingQueue<String> strAbq = new ArrayBlockingQueue<String>(5);
		for(int i=0; i<6; i++) {
			System.out.println("Offer: "+strAbq.offer("Test-"+i));
		}
		for(int i=0; i<6; i++) {
			String str = strAbq.poll();
			System.out.println("Poll: "+str);
		}
	}
	
	/**
	 * offer和poll超时方法的使用:
	 * offer: 向队列添加数据, 如果队列已满, 则返回false.
	 * poll: 获取队列数据, 如果队列已空, 则返回null.
	 */
	private static void test2() {
		try {
			ArrayBlockingQueue<String> strAbq = new ArrayBlockingQueue<String>(5);
			for(int i=0; i<6; i++) {
				System.out.println("Offer: "+strAbq.offer("Test-"+i, 3, TimeUnit.SECONDS));
			}
			for(int i=0; i<6; i++) {
				String str = strAbq.poll();
				System.out.println("Poll: "+str);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * put和take方法的使用:
	 * put: 向队列添加数据, 如果队列已满, 则阻塞着，直到队列有空间了。
	 * take: 获取队列数据, 如果队列已空, 则阻塞着，直到队列有数据了。
	 */
	private static void test3() {
		ArrayBlockingQueue<String> strAbq = new ArrayBlockingQueue<String>(5);
//		ProduceThread implements Runnable {
//
//			@Override
//			public void run() {
//				for(int i=0; i<12; i++) {
//					System.out.println("Offer: "+strAbq.offer("Test-"+i, 2, TimeUnit.SECONDS));
//					Thread.sleep(1000);
//				}
//			}
//		}
//	
//		for(int i=0; i<12; i++) {
//			String str = strAbq.poll();
//			System.out.println("Poll: "+str);
//		}
	}
}
/**
private static void test1() {
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
*/