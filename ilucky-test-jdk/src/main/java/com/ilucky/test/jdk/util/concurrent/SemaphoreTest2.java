package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * 公平锁和非公平锁: 
 * 默认使用非公平锁，因为非公平锁的的效率要高于公平锁。但是非公平锁会导致"线程饿死"的问题.
 * 线程饿死，即某些线程一直空闲, 获取不到cpu时间片。所以, 要结合具体的业务场景进行选择.
 * 
 * 公平锁: 锁能够采用公平的策略分配给线程
 * 非公平锁: 锁首先采用不公平的策略来分配给线程, 如果失败, 则再采用公平的策略.
 * @author IluckySi
 *
 */
public class SemaphoreTest2 {

	public static void main(String[] args) {
		//Semaphore s = new Semaphore(1, true);
		Semaphore s = new Semaphore(2, true);
		new SemaphoreTestT21(s).start();
		new SemaphoreTestT2(s).start();
		new SemaphoreTestT3(s).start();
	}
}

class SemaphoreTestT21 extends Thread {

	private Semaphore s;
	
	SemaphoreTestT21(Semaphore s) {
		this.s = s;
	}
	
	public void run() {
		try {
			while(true) {
				s.acquire();
				System.out.println(new Date() + "name =" + Thread.currentThread().getName());
				Thread.sleep(2000);
				s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SemaphoreTestT22 extends Thread {

	private Semaphore s;
	
	SemaphoreTestT22(Semaphore s) {
		this.s = s;
	}
	
	public void run() {
		try {
			while(true) {
				s.acquire();
				System.out.println(new Date() + "name =" + Thread.currentThread().getName());
				Thread.sleep(2000);
				s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SemaphoreTestT23 extends Thread {
	
	private Semaphore s;
	
	SemaphoreTestT23(Semaphore s) {
		this.s = s;
	}
	
	public void run() {
		try {
			while(true) {
				s.acquire();
				System.out.println(new Date() + "name =" + Thread.currentThread().getName());
				Thread.sleep(2000);
				s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
/**
注意: 观察线程顺序, 另外观察时间:
Semaphore s = new Semaphore(1, true);
Fri May 12 12:22:21 GMT+08:00 2017name =Thread-0
Fri May 12 12:22:23 GMT+08:00 2017name =Thread-1
Fri May 12 12:22:25 GMT+08:00 2017name =Thread-2
Fri May 12 12:22:27 GMT+08:00 2017name =Thread-0
Fri May 12 12:22:29 GMT+08:00 2017name =Thread-1
Fri May 12 12:22:31 GMT+08:00 2017name =Thread-2

Semaphore s = new Semaphore(2, true);
Fri May 12 14:55:10 GMT+08:00 2017name =Thread-1
Fri May 12 14:55:10 GMT+08:00 2017name =Thread-0
Fri May 12 14:55:12 GMT+08:00 2017name =Thread-0
Fri May 12 14:55:12 GMT+08:00 2017name =Thread-2
Fri May 12 14:55:14 GMT+08:00 2017name =Thread-1
Fri May 12 14:55:14 GMT+08:00 2017name =Thread-2
Fri May 12 14:55:16 GMT+08:00 2017name =Thread-0
Fri May 12 14:55:16 GMT+08:00 2017name =Thread-1
Fri May 12 14:55:18 GMT+08:00 2017name =Thread-2
Fri May 12 14:55:18 GMT+08:00 2017name =Thread-0
Fri May 12 14:55:20 GMT+08:00 2017name =Thread-1
Fri May 12 14:55:20 GMT+08:00 2017name =Thread-2
Fri May 12 14:55:22 GMT+08:00 2017name =Thread-0
Fri May 12 14:55:22 GMT+08:00 2017name =Thread-1
Fri May 12 14:55:24 GMT+08:00 2017name =Thread-2
Fri May 12 14:55:24 GMT+08:00 2017name =Thread-0
*/
