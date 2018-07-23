package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;

/**
 * 等待/通知机制.
 * 等待/通知机制，是指一个线程A调用了对象O的wait(）方法进入等待状态，而另一个线程B调用了O对象的notify()
 * 或者notifyAll()方法, 线程A收到通知后从对象O的wait()方法返回，进而执行后面的操作。
 * 两个线程通过对象O来完成交互, 而对象上的wait和notify/notifyAll方法如同开关信号一样。
 * 用来完成等待方和通知方之间的交互工作。
 * 
 * 注意: 
 * 1. wait, notify和notifyAll方法调用前，需要先对对象加锁。
 * 2. wait方法调用后，会释放锁。
 * 3. notify和notifyAll方法调动后，不会立刻从wait方法返回，还需要释放锁。
 * 4. wait方法调用后，线程状态由Running状态变为waitting状态, 并将当前线程放到对象的等待队列。
 * 5. notify和notifyAll方法调用后，将等待队列中的一个(或者全部)线程从等待队列中移动到同步队列中。
 * 
 * 另外，需要注意: 等待/通知机制依赖于同步机制。
 * @author IluckySi
 *
 */
public class WaitNotifyTest {

	public static void main(String[] args) {
		Object lock = new Object();
		new WaitThread(lock).start();
		try {
			System.out.println("---------等待线程执行完成2秒后，再执行通知线程--------");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new NotifyThread(lock).start();
	}
}

class WaitThread extends Thread {

	private Object lock;
	
	WaitThread(Object lock) {
		this.lock = lock;
	}
	
	public void run() {
		System.out.println(new Date() + "-----------【进入等待线程】---------");
		synchronized(lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(new Date() + "------------等的花儿都谢了...[开始执行业务逻辑了]");
		}
	}
}

class NotifyThread extends Thread {

	private Object lock;
	
	NotifyThread(Object lock) {
		this.lock = lock;
	}
	
	public void run() {
		System.out.println(new Date() + "-----------【进入通知线程】---------");
		synchronized(lock) {
			lock.notify();
			try {
				System.out.println(new Date() + "---------通知完睡5秒--------");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(new Date() + "---------睡完5秒, 再睡一秒, 继续加锁--------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(lock) {
			System.out.println(new Date() + "---------这次我就自己玩了，不通知别人了--------");
		}
	}
}
/**
注意观察时间:
---------等待线程执行完成2秒后，再执行通知线程--------
Wed May 24 14:28:40 GMT+08:00 2017-----------【进入等待线程】---------
Wed May 24 14:28:42 GMT+08:00 2017-----------【进入通知线程】---------
Wed May 24 14:28:42 GMT+08:00 2017---------通知完睡5秒--------
Wed May 24 14:28:47 GMT+08:00 2017---------睡完5秒, 再睡一秒, 继续加锁--------
Wed May 24 14:28:47 GMT+08:00 2017------------等的花儿都谢了...[开始执行业务逻辑了]
Wed May 24 14:28:48 GMT+08:00 2017---------这次我就自己玩了，不通知别人了--------
*/
