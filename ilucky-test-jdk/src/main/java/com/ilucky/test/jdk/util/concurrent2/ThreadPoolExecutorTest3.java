package com.ilucky.test.jdk.util.concurrent2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import com.ilucky.test.jdk.util.concurrent2.model.TaskInfo;

/**
 * 四种饱和策略的使用
 * AbortPolicy: 抛出异常
 * DiscardOldestPolicy: 抛弃旧的未执行的请求
 * DiscardPolicy: 抛弃新的请求
 * CallerRunsPolicy: 对于拒绝的请求交给调用方(caller)去执行, 此测试类的调用方是main函数.
 * 同时注意: 队列长度设置为1, 即new ArrayBlockingQueue<Runnable>(1), 否则无法模拟场景.
 * @author IluckySi
 *
 */
public class ThreadPoolExecutorTest3 {

	public static void main(String[] args) {
		/**
		 	public static ExecutorService newSingleThreadExecutor() {
		        return new FinalizableDelegatedExecutorService
		                (new ThreadPoolExecutor(1, 1,
		                                        0L, TimeUnit.MILLISECONDS,
		                                        new LinkedBlockingQueue<Runnable>()));
		        }
		 */
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, 
//		new ArrayBlockingQueue<Runnable>(1)); 
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, 
//				new ArrayBlockingQueue<Runnable>(1), new DiscardOldestPolicy()); 
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, 
//				new ArrayBlockingQueue<Runnable>(1), new DiscardPolicy()); 
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, 
				new ArrayBlockingQueue<Runnable>(1), new CallerRunsPolicy()); 
		
		List<TaskInfo> taskInfoList = getTaskInfoList();
		for(TaskInfo t : taskInfoList) {
			final TaskInfo taskInfo = t;
			executor.execute(new Runnable() {
	
				public void run() {
					  System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + "- 正在执行[" + taskInfo.getTaskName() + "]任务");
					  try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
使用AbortPolicy(默认)策略:
ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, 
		new ArrayBlockingQueue<Runnable>(1)); 
Exception in thread "main" pool-1-thread-1-9- 正在执行[name-0]任务
java.util.concurrent.RejectedExecutionException: Task com.ilucky.test.jdk.util.concurrent2.ThreadPoolExecutorTest3$1@99ffac2 rejected from java.util.concurrent.ThreadPoolExecutor@ddb1fe0[Running, pool size = 1, active threads = 1, queued tasks = 1, completed tasks = 0]
	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2048)
	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:821)
	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1372)
	at com.ilucky.test.jdk.util.concurrent2.ThreadPoolExecutorTest3.main(ThreadPoolExecutorTest3.java:36)
pool-1-thread-1-9- 正在执行[name-1]任务

使用DiscardOldestPolicy策略:
ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, 
				new ArrayBlockingQueue<Runnable>(1), new DiscardOldestPolicy()); 
pool-1-thread-1-9- 正在执行[name-0]任务
pool-1-thread-1-9- 正在执行[name-10]任务

使用DiscardPolicy策略:
ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, 
		new ArrayBlockingQueue<Runnable>(1), new DiscardPolicy()); 
pool-1-thread-1-9- 正在执行[name-0]任务
pool-1-thread-1-9- 正在执行[name-1]任务

使用CallerRunsPolicy策略:
main-1- 正在执行[name-2]任务
pool-1-thread-1-9- 正在执行[name-0]任务
main-1- 正在执行[name-3]任务
pool-1-thread-1-9- 正在执行[name-1]任务
main-1- 正在执行[name-5]任务
pool-1-thread-1-9- 正在执行[name-4]任务
main-1- 正在执行[name-7]任务
pool-1-thread-1-9- 正在执行[name-6]任务
pool-1-thread-1-9- 正在执行[name-8]任务
main-1- 正在执行[name-10]任务
pool-1-thread-1-9- 正在执行[name-9]任务
*/
