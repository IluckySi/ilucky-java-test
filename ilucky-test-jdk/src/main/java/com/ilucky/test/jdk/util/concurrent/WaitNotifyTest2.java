package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;

/**
 * 等待/通知机制.
 * 等待超时模式.
 * @author IluckySi
 *
 */
public class WaitNotifyTest2 {

	private static Object lock = new Object();
	
	public static void main(String[] args) {
	    int rn =  getResouce(2000L);
		System.out.println(new Date() + "----------> " + rn);
	}

	public static int getResouce(long timeout) {
	    System.out.println(new Date());
		synchronized(lock) {
			long future = System.currentTimeMillis() + timeout;
			long remaining = timeout;
			while(remaining > 0) {
				System.out.println(new Date() + "----------> remaining="+remaining);
				try {
					lock.wait(remaining);
					remaining = future - System.currentTimeMillis();
					// 模拟业务逻辑
					Thread.sleep(500);
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
Fri Jul 27 09:06:28 GMT+08:00 2018
Fri Jul 27 09:06:28 GMT+08:00 2018----------> remaining=2000
Fri Jul 27 09:06:30 GMT+08:00 2018----------> 1

*/
