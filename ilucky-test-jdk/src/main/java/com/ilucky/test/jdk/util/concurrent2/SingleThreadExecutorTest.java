package com.ilucky.test.jdk.util.concurrent2;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个单线程的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * 参考: http://justsee.iteye.com/blog/999189
 * @author IluckySi
 *
 * Creates an Executor that uses a single worker thread operating off an unbounded queue. 
 * 创建一个执行器, 它使用一个单独的工作线程在无界队列中运行。
 * (Note however that if this single thread terminates due to a failure during execution prior to shutdown, 
 * 但是请注意，如果单线程在关闭前在执行过程中失败，则终止此线程，
 * a new one will take its place if needed to execute subsequent tasks.) 
 * 如果需要执行后续任务，则需要使用新的）。
 * Tasks are guaranteed to execute sequentially, and no more than one task will be active at any given time. 
 * 任务是按顺序执行的，在任何给定的时间内都不会有超过一个任务处于活动状态(即任意时间，只有一个任务处于活动状态)。
 * Unlike the otherwise equivalent newFixedThreadPool(1) the returned executor is guaranteed not to be reconfigurable to use additional threads.
 * 不像其他等效创建固定数目线程的线程池（1）返回的执行是保证不可使用额外的线程
 * 
 * Returns: the newly created single-threaded Executor
 */
public class SingleThreadExecutorTest {

	public static void main(String[] args) {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		// 第一种
//		singleThreadExecutor.execute(new SingleThreadExecutorTestT1());
		
		// 第二种
		for(int i=0; i<5; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {

				public void run() {
					try {
						System.out.println(new Date() + "--->" + Thread.currentThread().getName() +Thread.currentThread().getId() + "--->" +index);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 线程池调用该方法时, 线程池的状态立刻变成SHUTDOWN状态。此时, 则不能再往线程池中添加任何任务，
		// 否则将会抛出RejectedExecutionException异常。但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出
		singleThreadExecutor.shutdown();
		
		// 线程池调用该方法时, 线程池的状态立刻变成SHUTDOWN状态，并试图停止所有正在执行的线程，不再处理还在池队列中等待的任务，
		// 当然，它会返回那些未执行的任务。同时，也不允许再往线程池中添加任何任务， 否则将会抛出RejectedExecutionException异常。
		// List<Runnable> list = singleThreadExecutor.shutdownNow();
		// System.out.println("---->" + list.size());
		
		System.out.println("isShutdown="+singleThreadExecutor.isShutdown());
		// 如果执行shutdown方法, 等待所有任务完成。
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Returns true if all tasks have completed following shut down. 
		// Note that isTerminated is never true unless either shutdown or shutdownNow was called first.
		System.out.println("isTerminated="+singleThreadExecutor.isTerminated());
		
		// 测试shutdown后, 再次向线程池提交任务.
		for(int i=6; i<10; i++) {
			final int index = i;
			singleThreadExecutor.execute( new Runnable() {

				public void run() {
					try {
						System.out.println(new Date() + "--->" + Thread.currentThread().getName() +Thread.currentThread().getId() + "--->" +index);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}

class SingleThreadExecutorTestT1 extends Thread {
		
	public void run() {
		try {
			for(int i=0; i<5; i++) {
				System.out.println(new Date() + "--->" + Thread.currentThread().getName() +Thread.currentThread().getId() + "--->" + i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/**
// 第一种
Thu Jul 06 11:15:11 GMT+08:00 2017--->pool-1-thread-110--->0
Thu Jul 06 11:15:12 GMT+08:00 2017--->pool-1-thread-110--->1
Thu Jul 06 11:15:13 GMT+08:00 2017--->pool-1-thread-110--->2
Thu Jul 06 11:15:14 GMT+08:00 2017--->pool-1-thread-110--->3
Thu Jul 06 11:15:15 GMT+08:00 2017--->pool-1-thread-110--->4
// 第二种
Thu Jul 06 11:15:55 GMT+08:00 2017--->pool-1-thread-19--->0
Thu Jul 06 11:15:56 GMT+08:00 2017--->pool-1-thread-19--->1
Thu Jul 06 11:15:57 GMT+08:00 2017--->pool-1-thread-19--->2
Thu Jul 06 11:15:58 GMT+08:00 2017--->pool-1-thread-19--->3
Thu Jul 06 11:15:59 GMT+08:00 2017--->pool-1-thread-19--->4

// singleThreadExecutor.shutdown();
Thu Jul 06 11:28:32 GMT+08:00 2017--->pool-1-thread-19--->0
Thu Jul 06 11:28:33 GMT+08:00 2017--->pool-1-thread-19--->1
isShutdown=true
Thu Jul 06 11:28:34 GMT+08:00 2017--->pool-1-thread-19--->2
Thu Jul 06 11:28:35 GMT+08:00 2017--->pool-1-thread-19--->3
isTerminated=false
Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.ilucky.test.jdk.util.concurrent2.SingleThreadExecutorTest$2@153a6057 rejected from java.util.concurrent.ThreadPoolExecutor@2b8afaa4[Shutting down, pool size = 1, active threads = 1, queued tasks = 1, completed tasks = 3]
	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2048)
	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:821)
	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1372)
	at java.util.concurrent.Executors$DelegatedExecutorService.execute(Executors.java:628)
	at com.ilucky.test.jdk.util.concurrent2.SingleThreadExecutorTest.main(SingleThreadExecutorTest.java:75)
Thu Jul 06 11:28:36 GMT+08:00 2017--->pool-1-thread-19--->4

---------------------------------------------------------------------------------------------
// List<Runnable> list = singleThreadExecutor.shutdownNow();
// System.out.println("---->" + list.size());
Thu Jul 06 11:27:33 GMT+08:00 2017--->pool-1-thread-19--->0
Thu Jul 06 11:27:34 GMT+08:00 2017--->pool-1-thread-19--->1
---->3
isShutdown=true
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at com.ilucky.test.jdk.util.concurrent2.SingleThreadExecutorTest$1.run(SingleThreadExecutorTest.java:43)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
isTerminated=true
Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.ilucky.test.jdk.util.concurrent2.SingleThreadExecutorTest$2@2b0951aa rejected from java.util.concurrent.ThreadPoolExecutor@70d085f8[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 2]
	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2048)
	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:821)
	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1372)
	at java.util.concurrent.Executors$DelegatedExecutorService.execute(Executors.java:628)
	at com.ilucky.test.jdk.util.concurrent2.SingleThreadExecutorTest.main(SingleThreadExecutorTest.java:75)

*/