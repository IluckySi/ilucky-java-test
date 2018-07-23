package com.ilucky.test.jdk.util.concurrent2;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newSingleThreadExecutor创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，
 * 如果这个线程异常结束，会有另一个取代它，保证顺序执行(我觉得这点是它的特色)。
 * 单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的 。
 * @author IluckySi
 */
public class SingleThreadExecutorTest2 {

	public static void main(String[] args) {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		for(int i=0; i<5; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {

				public void run() {
					System.out.println(new Date() + "--->" + Thread.currentThread().getName() +Thread.currentThread().getId() + "--->" +index);
					// 验证:  如果这个线程异常结束，会有另一个取代它
					Thread.currentThread().stop();
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}

/**
// Thread.currentThread().stop();
Thu Jul 06 11:58:27 GMT+08:00 2017--->pool-1-thread-19--->0
Thu Jul 06 11:58:27 GMT+08:00 2017--->pool-1-thread-211--->1
Thu Jul 06 11:58:27 GMT+08:00 2017--->pool-1-thread-312--->2
Thu Jul 06 11:58:27 GMT+08:00 2017--->pool-1-thread-413--->3
Thu Jul 06 11:58:27 GMT+08:00 2017--->pool-1-thread-514--->4
 */
