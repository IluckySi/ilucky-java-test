package com.ilucky.test.jdk.util.concurrent;

/**
 * 等待/通知机制.
 * 等待超时模式.
 * @author IluckySi
 *
 */
public class WaitNotifyTest2 {

	private static Object lock = new Object();
	
	public static void main(String[] args) {
		System.out.println(getResouce(2000L));
	}

	public static int getResouce(long timeout) {
		synchronized(lock) {
			long future = System.currentTimeMillis() + timeout;
			long remaining = timeout;
			while(remaining > 0) {
				System.out.println("----------> remaining="+remaining);
				try {
					lock.wait(remaining);
					remaining = future - System.currentTimeMillis();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return -1;
				}
			}
			return 1;
		}
	}
}
/**
----------> remaining=2000
1
*/
