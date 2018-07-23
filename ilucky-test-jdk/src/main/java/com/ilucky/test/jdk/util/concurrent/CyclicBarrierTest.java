package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * n个线程互相等待, 任何一个线程完成之前, 所有的线程都必须等待.
 * CyclicBarrier更像一个水闸, 线程执行就像水流, 在水闸处都会堵住, 等到水满(线程到齐)了, 才开始泄流.
 * 
 * CountDownLatch和CylicBarrier的区别: http://blog.csdn.net/kjfcpua/article/details/7300286
 * @author IluckySi
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(3);
		new CyclicBarrierTestT1(cb).start();
		new CyclicBarrierTestT2(cb).start();
		new CyclicBarrierTestT3(cb).start();
	}
}

class CyclicBarrierTestT1 extends Thread {

	private CyclicBarrier cb;
	
	CyclicBarrierTestT1(CyclicBarrier cb) {
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

class CyclicBarrierTestT2 extends Thread {

	private CyclicBarrier cb;
	
	CyclicBarrierTestT2(CyclicBarrier cb) {
		this.cb = cb;
	}
	
	public void run() {
		try {
			Thread.sleep(2000);
			cb.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + "name =" + Thread.currentThread().getName());
	}
}

class CyclicBarrierTestT3 extends Thread {
	
	private CyclicBarrier cb;
	
	CyclicBarrierTestT3(CyclicBarrier cb) {
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
Thu May 11 10:45:54 GMT+08:00 2017name =Thread-1
Thu May 11 10:45:54 GMT+08:00 2017name =Thread-0
Thu May 11 10:45:54 GMT+08:00 2017name =Thread-2
*/
