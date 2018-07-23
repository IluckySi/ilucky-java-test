package com.ilucky.test.jdk.util.concurrent2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import com.ilucky.test.jdk.util.concurrent2.model.TaskInfo;

/**
 * 监控线程池状态信息
 * 注意: 如果使用默认策略, 并且线程池配置如下:
 * 	executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, 
 *				new ArrayBlockingQueue<Runnable>(10), new DiscardOldestPolicy()); 
 * 会发生"很奇怪"的情况: 即线程池把异常吃掉了。
 * @author IluckySi
 *
 */
public class ThreadPoolExecutorTest5 implements Runnable {
	
	static ThreadPoolExecutor executor;
	
	public static void main(String[] args) {
//		executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, 
//				new ArrayBlockingQueue<Runnable>(10), new DiscardOldestPolicy());
		executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(100), new DiscardOldestPolicy()); 
		List<TaskInfo> taskInfoList = getTaskInfoList();
		for(TaskInfo t : taskInfoList) {
			final TaskInfo taskInfo = t;
			System.out.println("-------------------->"+ taskInfo.getTaskName());
			executor.execute(new Runnable() {
	
				public void run() {
					  System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + "- 正在执行[" + taskInfo.getTaskName() + "]任务");
					  try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			});
		}
		new Thread(new ThreadPoolExecutorTest5()).start();
	}

	private static List<TaskInfo> getTaskInfoList() {
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		for(int i=0; i<100; i++) {
			list.add(new TaskInfo("name-"+i));
		}
		return list;
	}

	public void run() {
		while(true) {
			System.out.println("------------------------------start----------------------");
			System.out.println("线程池中alive的线程数量: getActiveCount="+executor.getActiveCount());
			System.out.println("线程池中队列的大小: getQueue().size()="+executor.getQueue().size());
			System.out.println("线程池中当前线程数量: getPoolSize="+executor.getPoolSize());
			System.out.println("线程池中曾经有过的最大线程数量: getLargestPoolSize="+executor.getLargestPoolSize());
			System.out.println("未完成的任务数量: getTaskCount="+executor.getTaskCount());
			System.out.println("完成的任务数量: getCompletedTaskCount="+executor.getCompletedTaskCount());
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
executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, 
			new ArrayBlockingQueue<Runnable>(10), new DiscardOldestPolicy()); 
-------------------->name-0
-------------------->name-1
-------------------->name-2
-------------------->name-3
-------------------->name-4
-------------------->name-5
-------------------->name-6
pool-1-thread-1-10- 正在执行[name-0]任务
-------------------->name-7
-------------------->name-8
-------------------->name-9
-------------------->name-10
-------------------->name-11
-------------------->name-12
pool-1-thread-2-11- 正在执行[name-11]任务
-------------------->name-13
pool-1-thread-3-12- 正在执行[name-12]任务
-------------------->name-14
pool-1-thread-4-13- 正在执行[name-13]任务
-------------------->name-15
pool-1-thread-5-14- 正在执行[name-14]任务
-------------------->name-16
pool-1-thread-6-15- 正在执行[name-15]任务
-------------------->name-17
pool-1-thread-7-16- 正在执行[name-16]任务
-------------------->name-18
pool-1-thread-8-17- 正在执行[name-17]任务
-------------------->name-19
pool-1-thread-9-18- 正在执行[name-18]任务
-------------------->name-20
pool-1-thread-10-19- 正在执行[name-19]任务
-------------------->name-21
-------------------->name-22
-------------------->name-23
-------------------->name-24
-------------------->name-25
-------------------->name-26
-------------------->name-27
-------------------->name-28
-------------------->name-29
......（略）......
-------------------->name-90
-------------------->name-91
-------------------->name-92
-------------------->name-93
-------------------->name-94
-------------------->name-95
-------------------->name-96
-------------------->name-97
-------------------->name-98
-------------------->name-99
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=10
线程池中队列的大小: getQueue().size()=10
线程池中当前线程数量: getPoolSize=10
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=0
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-90]任务
pool-1-thread-3-12- 正在执行[name-91]任务
pool-1-thread-7-16- 正在执行[name-92]任务
pool-1-thread-4-13- 正在执行[name-93]任务
pool-1-thread-8-17- 正在执行[name-94]任务
pool-1-thread-5-14- 正在执行[name-95]任务
pool-1-thread-6-15- 正在执行[name-96]任务
pool-1-thread-2-11- 正在执行[name-97]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=10
线程池中队列的大小: getQueue().size()=2
线程池中当前线程数量: getPoolSize=10
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=8
------------------------------end----------------------
pool-1-thread-10-19- 正在执行[name-98]任务
pool-1-thread-9-18- 正在执行[name-99]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=2
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=10
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=18
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=10
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=20
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=10
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=20
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=10
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=20
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=10
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=20
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=20
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=10
未完成的任务数量: getTaskCount=20
完成的任务数量: getCompletedTaskCount=20
------------------------------end----------------------

---------------------------------------分割线-------------------------------------------------------

executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, 
		new ArrayBlockingQueue<Runnable>(100), new DiscardOldestPolicy()); 
-------------------->name-0
-------------------->name-1
-------------------->name-2
-------------------->name-3
-------------------->name-4
-------------------->name-5
-------------------->name-6
-------------------->name-7
pool-1-thread-1-10- 正在执行[name-0]任务
-------------------->name-8
-------------------->name-9
-------------------->name-10
-------------------->name-11
-------------------->name-12
-------------------->name-13
-------------------->name-14
-------------------->name-15
-------------------->name-16
-------------------->name-17
-------------------->name-18
......（略）......
-------------------->name-44
-------------------->name-45
-------------------->name-46
-------------------->name-47
-------------------->name-68
-------------------->name-69
-------------------->name-70
-------------------->name-71
......（略）......
-------------------->name-97
-------------------->name-98
-------------------->name-99
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=99
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=0
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-1]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=98
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=1
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-2]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=97
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=2
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-3]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=96
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=3
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-4]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=95
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=4
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-5]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=94
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=5
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-6]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=93
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=6
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-7]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=92
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=7
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-8]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=91
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=8
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-9]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=90
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=9
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-10]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=89
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=10
------------------------------end----------------------
......（略）......
pool-1-thread-1-10- 正在执行[name-95]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=4
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=95
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-96]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=3
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=96
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-97]任务
pool-1-thread-1-10- 正在执行[name-98]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=1
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=98
------------------------------end----------------------
pool-1-thread-1-10- 正在执行[name-99]任务
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=1
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=99
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=100
------------------------------end----------------------
------------------------------start----------------------
线程池中alive的线程数量: getActiveCount=0
线程池中队列的大小: getQueue().size()=0
线程池中当前线程数量: getPoolSize=1
线程池中曾经有过的最大线程数量: getLargestPoolSize=1
未完成的任务数量: getTaskCount=100
完成的任务数量: getCompletedTaskCount=100
------------------------------end----------------------
*/
