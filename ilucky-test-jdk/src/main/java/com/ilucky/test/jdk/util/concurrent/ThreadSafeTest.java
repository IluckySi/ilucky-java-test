package com.ilucky.test.jdk.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全: 当多个线程访问某个类时, 这个类始终都能表现出正确的行为, 那么就称这个类是线程安全的。
 * @author IluckySi
 *
 */
public class ThreadSafeTest {

	   public static int count = 0;
	   public static Counter counter = new Counter();
	   public static AtomicInteger atomicInteger = new AtomicInteger(0);
	   volatile public static int countVolatile = 0;
	
	public static void main(String[] args) {
		// 保证所有线程执行完毕.
		final CountDownLatch cdl = new CountDownLatch(10);
		for(int i=0; i<10; i++) {
			 new Thread() {
				 public void run() {
					 for (int j = 0; j < 1000; j++) {
						 count++;
						 counter.increment();
						 atomicInteger.getAndIncrement();
						 countVolatile++;
					}
					cdl.countDown();
				 }
			 }.start();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("static count: " + count);
        System.out.println("Counter: " + counter.getValue());
        System.out.println("AtomicInteger: " + atomicInteger.intValue());
        System.out.println("countVolatile: " + countVolatile);
	}
}

class Counter {
	
	private int value;
	
	public synchronized int getValue() {
		return value;
	}
	
	public synchronized int increment() {
		return value++;
	}
	
	public synchronized int decrement() {
		return --value;
	}
}
/**
运行结果如下，每次运行结果都不一样，但是每次中间的两个都是10000.
static count: 9997
Counter: 10000
AtomicInteger: 10000
countVolatile: 9997

说明要解决自增操作在多线程环境下线程不安全的问题，可以选择使用Java提供的原子类或者使用synchronized同步方法。
而使用volatile关键字, 并不能解决非原子操作的线程安全性。

虽然递增操作++i看上去只是一个操作, 但这个操作并非原子的, 因而它并不会作为一个不可分割的操作来执行。
实际上它包含了三个独立的操作: 读取count的值, 将值加1, 然后将计算结果写入count. 
这是一个“读取 - 修改 - 写入”的操作序列, 并且其结果状态依赖于之前的状态。
 */

