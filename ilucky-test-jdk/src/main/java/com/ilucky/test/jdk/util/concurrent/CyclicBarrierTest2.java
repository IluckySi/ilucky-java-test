package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的使用并不难, 但需要注意它所相关的异常. 
 * 除了常见的异常, CyclicBarrier.await()方法会抛出一个独有的BrokenBarrierException。
 * 这个异常发生在当某个线程在被中断或超时或被重置时, 其它线程会抛出BrokenBarrierException。
 * 意思就是说, 同志们, 别等了, 有个小伙伴已经挂了, 咱们如果继续等有可能会一直等下去, 所有继续执行吧。
 * @author IluckySi
 *
 */
public class CyclicBarrierTest2 {

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(3);
		new CyclicBarrierTestT21(cb).start();
		new CyclicBarrierTestT22(cb).start();;
		new CyclicBarrierTestT23(cb).start();
	}
}

class CyclicBarrierTestT21 extends Thread {

	private CyclicBarrier cb;
	
	CyclicBarrierTestT21(CyclicBarrier cb) {
		this.cb = cb;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
			cb.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "name =" + Thread.currentThread().getName());
	}
}

class CyclicBarrierTestT22 extends Thread {

	private CyclicBarrier cb;
	
	CyclicBarrierTestT22(CyclicBarrier cb) {
		this.cb = cb;
	}
	
	public void run() {
		try {
			Thread.sleep(2000);
			// 中断当前线程, 会导致其他线程抛出BrokenBarrierException异常, 如果其他线程捕获了这个异常, 则会继续执行, 否则不会再继续执行.
			Thread.currentThread().interrupt();
			cb.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "name =" + Thread.currentThread().getName());
	}
}

class CyclicBarrierTestT23 extends Thread {
	
	private CyclicBarrier cb;
	
	CyclicBarrierTestT23(CyclicBarrier cb) {
		this.cb = cb;
	}
	
	public void run() {
		try {
			Thread.sleep(5000);
			cb.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "name =" + Thread.currentThread().getName());
	}
}
/**
java.util.concurrent.BrokenBarrierException
	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:243)
	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:355)
	at com.ilucky.test.jdk.util.concurrent.CyclicBarrierTestT21.run(CyclicBarrierTest2.java:32)
Fri May 12 10:24:53 GMT+08:00 2017name =Thread-0
java.lang.InterruptedException
	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:204)
	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:355)
	at com.ilucky.test.jdk.util.concurrent.CyclicBarrierTestT22.run(CyclicBarrierTest2.java:54)
Fri May 12 10:24:53 GMT+08:00 2017name =Thread-1
java.util.concurrent.BrokenBarrierException
	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:200)
	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:355)
	at com.ilucky.test.jdk.util.concurrent.CyclicBarrierTestT23.run(CyclicBarrierTest2.java:75)
Fri May 12 10:24:56 GMT+08:00 2017name =Thread-2
*/
