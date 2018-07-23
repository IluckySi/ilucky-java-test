package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * CountDownLatch能使一个或一组线程等另一组线程都跑完了再继续跑.
 * CyclicBarrier使一组线程在一个时间点上达到同步.
 * Semaphore只允许一定数量的线程同时执行一段任务.
 * 
 * CountDownLatch, CylicBarrier和SemapHore的区别: http://developer.51cto.com/art/201403/432095.htm
 * 这三个是JUC(java.util.concurrent)中较为常用的同步器, 通过它们可以方便地实现很多线程之间协作的功能.
 * 
 * SemapHore直接翻译是信号量的意思, 可能称它是许可量更容易理解。
 * 通过acquire方法获得许可, release方法释放许可, 还有tryAcquire和acquireUninterruptibly方法, 可以根据自己的需要选择.
 * 
 * 应用场景是: 限制某个资源可被同时访问的个数, 即限流, 比如在Windows下可以设置共享文件的最大客户端访问个数。
 * 
 * 信号量(semaphore)是一个停车场, 当前值是停车场里还剩下多少个空车位, 最大值是停车场里最多能容纳多少个车位。
 * 当汽车进入停车场时，首先要在门口排队(sem_wait)，得到进入许可后才能进入，原则上先到先得。
 * 每进一辆车，停车场就少了1个停车位，即信号量当前值-1, 当前值为0时，停车场停满了，所有车不得进入, 统统在门口排队等。
 * 当一辆车离开后，释放其所占据的停车位(sem_post)，信号量当前值+1
 * 信号量值得到释放后，如果门口有正在排队的车，那么就放进来，每放进来一个就重复前面的步骤。
 * 参考: http://blog.csdn.net/a1042185842b/article/details/51094718
 * @author IluckySi
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		//Semaphore s = new Semaphore(1);
		Semaphore s = new Semaphore(2);
		new SemaphoreTestT1(s).start();
		new SemaphoreTestT2(s).start();
		new SemaphoreTestT3(s).start();
	}
}

class SemaphoreTestT1 extends Thread {

	private Semaphore s;
	
	SemaphoreTestT1(Semaphore s) {
		this.s = s;
	}
	
	public void run() {
		try {
			while(true) {
				s.acquire();
				System.out.println(new Date() + "name =" + Thread.currentThread().getName());
				Thread.sleep(2000);
				s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SemaphoreTestT2 extends Thread {

	private Semaphore s;
	
	SemaphoreTestT2(Semaphore s) {
		this.s = s;
	}
	
	public void run() {
		try {
			while(true) {
				s.acquire();
				System.out.println(new Date() + "name =" + Thread.currentThread().getName());
				Thread.sleep(2000);
				s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SemaphoreTestT3 extends Thread {
	
	private Semaphore s;
	
	SemaphoreTestT3(Semaphore s) {
		this.s = s;
	}
	
	public void run() {
		try {
			while(true) {
				s.acquire();
				System.out.println(new Date() + "name =" + Thread.currentThread().getName());
				Thread.sleep(2000);
				s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
/**
分别观察Semaphore s = new Semaphore(1);和Semaphore s = new Semaphore(2);运行结果, 
会发现限流成功了, 注意观察时间间隔.

Semaphore s = new Semaphore(1);运行结果:
Fri May 12 11:13:04 GMT+08:00 2017name =Thread-0
Fri May 12 11:13:06 GMT+08:00 2017name =Thread-0
Fri May 12 11:13:08 GMT+08:00 2017name =Thread-1
Fri May 12 11:13:10 GMT+08:00 2017name =Thread-2

Semaphore s = new Semaphore(2);运行结果:
Fri May 12 11:13:44 GMT+08:00 2017name =Thread-0
Fri May 12 11:13:44 GMT+08:00 2017name =Thread-1
Fri May 12 11:13:46 GMT+08:00 2017name =Thread-1
Fri May 12 11:13:46 GMT+08:00 2017name =Thread-0
Fri May 12 11:13:48 GMT+08:00 2017name =Thread-0
Fri May 12 11:13:48 GMT+08:00 2017name =Thread-2
Fri May 12 11:13:50 GMT+08:00 2017name =Thread-1
Fri May 12 11:13:50 GMT+08:00 2017name =Thread-2
*/
