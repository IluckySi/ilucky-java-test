package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * cdl.await(long, TimeUnit);等待超时，针对某些业务场景，如果某一个线程非常耗时或者发生了异常.
 * 但是并不想影响主线程的继续执行, 则可以使用await(long, TimeUnit)方法.
 * 即一个线程(或者多个线程)，等待另外n个线程执行long时间后继续执行.
 * @author IluckySi
 *
 */
public class CountDownLatchTest2 {

	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(3);
		new Test1T21(cdl).start();
		new Test1T22(cdl).start();
		new Test1T23(cdl).start();
		try {
			cdl.await(4000, TimeUnit.MILLISECONDS);
			// 继续执行下面的逻辑...(略)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "Success...");
	}
}

class Test1T21 extends Thread {

	private CountDownLatch cdl;
	
	Test1T21(CountDownLatch cdl) {
		this.cdl = cdl;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "name =" + Thread.currentThread().getName());
		cdl.countDown();
	}
}

class Test1T22 extends Thread {

	private CountDownLatch cdl;
	
	Test1T22(CountDownLatch cdl) {
		this.cdl = cdl;
	}
	
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "name =" + Thread.currentThread().getName());
		cdl.countDown();
	}
}

class Test1T23 extends Thread {
	
	private CountDownLatch cdl;
	
	Test1T23(CountDownLatch cdl) {
		this.cdl = cdl;
	}
	
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "name =" + Thread.currentThread().getName());
		cdl.countDown();
	}
}
/**
Wed Jul 05 14:22:52 GMT+08:00 2017name =Thread-0
Wed Jul 05 14:22:53 GMT+08:00 2017name =Thread-1
Wed Jul 05 14:22:55 GMT+08:00 2017Success...
Wed Jul 05 14:22:56 GMT+08:00 2017name =Thread-2

*/
