package com.ilucky.test.jdk.util.concurrent2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

import com.ilucky.test.jdk.util.concurrent2.model.TaskInfo;

/**
 * 注意corePoolSize的使用.
 * @author IluckySi
 */
public class ThreadPoolExecutorTest2 {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(80), new DiscardOldestPolicy()); 
		List<TaskInfo> taskInfoList = getTaskInfoList();
		for(TaskInfo t : taskInfoList) {
			final TaskInfo taskInfo = t;
			executor.execute(new Runnable() {
	
				public void run() {
					  System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + "- 正在执行[" + taskInfo.getTaskName() + "]任务");
				}
			});
		}
	}

	private static List<TaskInfo> getTaskInfoList() {
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		for(int i=0; i<=10; i++) {
			list.add(new TaskInfo("name-"+i));
		}
		return list;
	}
}
/**
输出结果:
ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 100, 10, TimeUnit.SECONDS, 
	new ArrayBlockingQueue<Runnable>(80), new DiscardOldestPolicy()); 
pool-1-thread-1-9- 正在执行[name-0]任务
pool-1-thread-1-9- 正在执行[name-1]任务
pool-1-thread-1-9- 正在执行[name-2]任务
pool-1-thread-1-9- 正在执行[name-3]任务
pool-1-thread-1-9- 正在执行[name-4]任务
pool-1-thread-1-9- 正在执行[name-5]任务
pool-1-thread-1-9- 正在执行[name-6]任务
pool-1-thread-1-9- 正在执行[name-7]任务
pool-1-thread-1-9- 正在执行[name-8]任务
pool-1-thread-1-9- 正在执行[name-9]任务
pool-1-thread-1-9- 正在执行[name-10]任务

ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 100, 10, TimeUnit.SECONDS, 
	new ArrayBlockingQueue<Runnable>(80), new DiscardOldestPolicy()); 
pool-1-thread-1-9- 正在执行[name-0]任务
pool-1-thread-2-10- 正在执行[name-1]任务
pool-1-thread-1-9- 正在执行[name-2]任务
pool-1-thread-2-10- 正在执行[name-3]任务
pool-1-thread-1-9- 正在执行[name-4]任务
pool-1-thread-2-10- 正在执行[name-5]任务
pool-1-thread-2-10- 正在执行[name-7]任务
pool-1-thread-1-9- 正在执行[name-6]任务
pool-1-thread-2-10- 正在执行[name-8]任务
pool-1-thread-1-9- 正在执行[name-9]任务
pool-1-thread-2-10- 正在执行[name-10]任务

ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, 
	new ArrayBlockingQueue<Runnable>(80), new DiscardOldestPolicy()); 
pool-1-thread-2-10- 正在执行[name-1]任务
pool-1-thread-1-9- 正在执行[name-0]任务
pool-1-thread-1-9- 正在执行[name-10]任务
pool-1-thread-4-12- 正在执行[name-3]任务
pool-1-thread-3-11- 正在执行[name-2]任务
pool-1-thread-8-16- 正在执行[name-7]任务
pool-1-thread-6-14- 正在执行[name-5]任务
pool-1-thread-7-15- 正在执行[name-6]任务
pool-1-thread-10-18- 正在执行[name-9]任务
pool-1-thread-5-13- 正在执行[name-4]任务
pool-1-thread-9-17- 正在执行[name-8]任务
*/
