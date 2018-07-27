package com.ilucky.test.jdk.util.concurrent.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 等待/通知机制.
 * 等待超时模式的应用场景数据库连接池。
 * CountDownLatch在测试中的深入使用。
 * 
 * @author IluckySi
 *
 */
public class ConnectionPoolTest {

	static ConnectionPool pool = new ConnectionPool(5);
	
	// 保证所有ConnectionRunner同时运行.
	static CountDownLatch prepare;
	
	// 保证所有ConnectionRunner运行完成后, 再输出结果。
	static CountDownLatch finish;
	
	public static void main(String[] args) throws InterruptedException {
		// 线程数量, 可以通过修改线程数量进行观察.
		int threadCount = 200;
		prepare = new CountDownLatch(1);
		finish = new CountDownLatch(threadCount);
		
		// 每个线程发起20次获取数据库连接的请求.
		int count = 20;
		AtomicInteger got = new AtomicInteger(0);
		AtomicInteger notGot = new AtomicInteger(0);
		
		// 开始测试...
		for(int i=0; i<threadCount; i++) {
			Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
			thread.start();
		}
		prepare.countDown();
		finish.await();
		System.out.println("Total invoker:" + (threadCount * count));
		System.out.println("Got connection: " + got);
		System.out.println("NotGot connection: " + notGot);
	}
	
	/**
	 * 通过实现Runnable的线程可以共享变量
	 * 
	 * @author IluckySi
	 *
	 */
	static class ConnectionRunner implements Runnable {
		int count;
		AtomicInteger got;
		AtomicInteger notGot;
		
		public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}

		public void run() {
			try {
				prepare.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			while(count > 0) {
				try {
					// 从线程池中获取连接, 如果1000ms内无法获取到, 将会返回null,
					// 分别统计连接获取的数量got和未获取连接的数量notGot.
					Connection connection = pool.fetchConnection(1000);
					if(connection!= null) {
						try {
							connection.createStatement();
							connection.commit();
						} finally {
							pool.releaseConnection(connection);
							got.incrementAndGet();
						} 
					} else {
						notGot.incrementAndGet();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					count--;
				}
			}
			finish.countDown();
		}
	}
}
/**
注意: 可以通过修改线程数量和超时时间做测试。
-------------------------------10个连接的情况下-----------------
Total invoker:400
Got connection: 400
NotGot connection: 0

Total invoker:2000
Got connection: 2000
NotGot connection: 0

Total invoker:4000
Got connection: 4000
NotGot connection: 0

Total invoker:8000
Got connection: 8000
NotGot connection: 0

Total invoker:10000
Got connection: 9989
NotGot connection: 11

Total invoker:20000
Got connection: 19204
NotGot connection: 796

Total invoker:200000
Got connection: 18013
NotGot connection: 181987
-------------------------------100个连接的情况下-----------------
Total invoker:10000
Got connection: 10000
NotGot connection: 0

Total invoker:20000
Got connection: 20000
NotGot connection: 0

Total invoker:40000
Got connection: 40000
NotGot connection: 0

Total invoker:60000
Got connection: 59206
NotGot connection: 794

-------------------------------1000个连接的情况下-----------------
Total invoker:60000
Got connection: 60000
NotGot connection: 0

Total invoker:100000
Got connection: 100000
NotGot connection: 0

Total invoker:160000
Got connection: 160000
NotGot connection: 0

Total invoker:200000
Got connection: 199987
NotGot connection: 13
*/