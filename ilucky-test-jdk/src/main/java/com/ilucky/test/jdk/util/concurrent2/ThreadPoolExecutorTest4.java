package com.ilucky.test.jdk.util.concurrent2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import com.ilucky.test.jdk.util.concurrent2.model.TaskInfo;

/**
 * allowCoreThreadTimeOut的使用和线程池监控信息
 * @author IluckySi
 *
 */
public class ThreadPoolExecutorTest4 implements Runnable {
	
	static ThreadPoolExecutor executor;
	
	public static void main(String[] args) {
		executor = new ThreadPoolExecutor(1, 100, 10, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(80), new DiscardOldestPolicy()); 
		executor.allowCoreThreadTimeOut(true);
		List<TaskInfo> taskInfoList = getTaskInfoList();
		for(TaskInfo t : taskInfoList) {
			final TaskInfo taskInfo = t;
			executor.execute(new Runnable() {
	
				public void run() {
					  System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + "- 正在执行[" + taskInfo.getTaskName() + "]任务");
					  try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			});
		}
		new Thread(new ThreadPoolExecutorTest4()).start();
	}

	private static List<TaskInfo> getTaskInfoList() {
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		for(int i=0; i<=5; i++) {
			list.add(new TaskInfo("name-"+i));
		}
		return list;
	}

	public void run() {
		while(true) {
			System.out.println("------------------------------start----------------------");
			System.out.println("getActiveCount="+executor.getActiveCount());
			System.out.println("getQueue().size()="+executor.getQueue().size());
			System.out.println("getPoolSize="+executor.getPoolSize());
			System.out.println("getTaskCount="+executor.getTaskCount());
			System.out.println("getLargestPoolSize="+executor.getLargestPoolSize());
			System.out.println("------------------------------end----------------------");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/**
输出结果如下:
pool-1-thread-1-10- 正在执行[name-0]任务
------------------------------start----------------------
getActiveCount=1
getQueue().size()=5
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=5
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=5
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-1]任务
------------------------------start----------------------
getActiveCount=1
getQueue().size()=4
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=4
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-2]任务
------------------------------start----------------------
getActiveCount=1
getQueue().size()=3
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=3
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=3
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-3]任务
------------------------------start----------------------
getActiveCount=1
getQueue().size()=2
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=2
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-4]任务
------------------------------start----------------------
getActiveCount=1
getQueue().size()=1
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=1
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=1
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-5]任务
------------------------------start----------------------
getActiveCount=1
getQueue().size()=0
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=1
getQueue().size()=0
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=0
getQueue().size()=0
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
------------------------------start----------------------
getActiveCount=0
getQueue().size()=0
getPoolSize=1
getTaskCount=6
getLargestPoolSize=1
------------------------------end----------------------
*/
