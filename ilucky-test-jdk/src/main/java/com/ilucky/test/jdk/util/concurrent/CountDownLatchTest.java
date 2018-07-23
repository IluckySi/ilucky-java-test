package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 一个线程(或者多个线程)，等待另外n个线程完成某个事情之后执行.
 * CountDownLatch是计数器, 线程完成一个就记一个, 就像报数一样, 只不过是递减的.
 * 
 * 倒计数(CountDown)门闩(Latch), 倒计数不用说，门闩的意思顾名思义就是阻止前进。
 * 在这里就是指CountDownLatch.await()方法在倒计数为0之前会阻塞当前线程。
 * 和Thread.join方法类似...因为线程池的线程不能直接被引用, 所以就必须使用CountDownLatch了.
 * 
 * CountDownLatch和CylicBarrier的区别: http://blog.csdn.net/kjfcpua/article/details/7300286
 * @author IluckySi
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(3);
		new Test1T1(cdl).start();
		new Test1T2(cdl).start();
		new Test1T3(cdl).start();
		try {
			cdl.await();
			// 继续执行下面的逻辑...(略)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Success...");
	}
}

class Test1T1 extends Thread {

	private CountDownLatch cdl;
	
	Test1T1(CountDownLatch cdl) {
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

class Test1T2 extends Thread {

	private CountDownLatch cdl;
	
	Test1T2(CountDownLatch cdl) {
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

class Test1T3 extends Thread {
	
	private CountDownLatch cdl;
	
	Test1T3(CountDownLatch cdl) {
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
Thu May 11 10:45:12 GMT+08:00 2017name =Thread-0
Thu May 11 10:45:13 GMT+08:00 2017name =Thread-1
Thu May 11 10:45:16 GMT+08:00 2017name =Thread-2
Success...
*/
