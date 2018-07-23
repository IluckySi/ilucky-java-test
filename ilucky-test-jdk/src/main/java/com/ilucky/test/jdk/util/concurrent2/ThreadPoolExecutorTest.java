package com.ilucky.test.jdk.util.concurrent2;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
/**
 * @author IluckySi
 */
public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(80), new DiscardOldestPolicy()); 
		executor.execute(new Runnable() {
			public void run() {
				  System.out.println(Thread.currentThread().getName() + "- 正在执行");
			}
		});
	}
}
/**
输出结果:
pool-1-thread-1- 正在执行
*/