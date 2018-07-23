package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 优先执行: barrierAction
 * 用于在到达线程屏障时,优先执行barrierAction,方便处理更复杂的业务场景.
 * 注意: 优先还是滞后取决于业务在cb.await();哪里, 即先后位置.
 * 
 * 应用场景:
 * 比如我们用一个Excel保存了用户所有银行流水, 每个Sheet保存一个帐户近一年的每笔银行流水, 现在需要统计用户的日均银行流水,
 * 先用多线程处理每个sheet里的银行流水, 都执行完之后, 得到每个sheet的日均银行流水, 最后, 再用barrierAction用这些线程的计算结果, 
 * 计算出整个Excel的日均银行流水。
 * @author IluckySi
 *
 */
public class CyclicBarrierTest3 {

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(3, new CyclicBarrierTestT30());
		new CyclicBarrierTestT31(cb).start();
		new CyclicBarrierTestT32(cb).start();
		new CyclicBarrierTestT33(cb).start();
	}
}

class CyclicBarrierTestT31 extends Thread {

	private CyclicBarrier cb;
	
	CyclicBarrierTestT31(CyclicBarrier cb) {
		this.cb = cb;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println(new Date() + "计算: name =" + Thread.currentThread().getName());
			cb.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(new Date() + "name =" + Thread.currentThread().getName());
	}
}

class CyclicBarrierTestT32 extends Thread {

	private CyclicBarrier cb;
	
	CyclicBarrierTestT32(CyclicBarrier cb) {
		this.cb = cb;
	}
	
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println(new Date() + "计算: name =" + Thread.currentThread().getName());
			cb.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(new Date() + "name =" + Thread.currentThread().getName());
	}
}

class CyclicBarrierTestT33 extends Thread {
	
	private CyclicBarrier cb;
	
	CyclicBarrierTestT33(CyclicBarrier cb) {
		this.cb = cb;
	}
	
	public void run() {
		try {
			Thread.sleep(3000);
			System.out.println(new Date() + "计算: name =" + Thread.currentThread().getName());
			cb.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(new Date() + "name =" + Thread.currentThread().getName());
	}
}

class CyclicBarrierTestT30 extends Thread {
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("====\"优先执行\"====");
	}
}

/**
Fri May 12 11:41:43 GMT+08:00 2017计算: name =Thread-1
Fri May 12 11:41:44 GMT+08:00 2017计算: name =Thread-2
Fri May 12 11:41:45 GMT+08:00 2017计算: name =Thread-3
===="优先执行"====
*/
