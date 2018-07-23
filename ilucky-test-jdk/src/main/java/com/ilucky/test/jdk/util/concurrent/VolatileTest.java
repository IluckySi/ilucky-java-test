package com.ilucky.test.jdk.util.concurrent;

/**
 * volatile作用是在多处理器开发中保证了共享变量的可见性。
 * 可见性的意思是: 当一个线程修改一个共享变量时，另外一个线程能读到这个修改的值。
 * 
 * volatile之所以能实现多线程共享变量的可见性，原理如下:
 * 当一个线程操作volatile修饰的变量时, 会发生如下两件事情:
 * 1. 将当前处理器缓存行的数据写回到系统内存.
 * 2. 这个写回内存的操作会使在其他cpu里缓存了该内存地址的数据无效。
 * 
 * volatile和synchronized的区别: volatile是轻量级的锁，synchronized是重量级的锁,
 * volatile的执行成本更低，因为不会引起线程上下文的切换。
  * 另外，他们都能确保程序的可见性，但是也是有区别的:
  * 如果多个线程都进行读写, 一定要在用synchronized, volatile只确保了可见性，并不能确保原子性。
  * 即volatile的使用场景是: 有一个线程进行操作，其他线程可以感知到变化。
  * 
  * 总结说来，synchronized实现的同步能确保线程安全，实现可见性和原子性；但是代价大，效率低，更慢；
  * volatile能够实现多线程操作产生变化的可见性，并且效率高, 但是不能实现原子性。
  * atomic类是一种更轻量级的方法实现可见性和原子性
  * 参考:
  * https://segmentfault.com/a/1190000002679340
 * @author IluckySi
 * 
 * TODO://测试用例????
 *
 */
public class VolatileTest {

	volatile public String vol;
	public static String sta;
	
	public static void main(String[] args) {
		new WriteThread().start();
		new ReadThread().start();
	}
}

// 修改变量
class WriteThread extends Thread {
	
	public void run() {
		try {
			while(true) {
//				VolatileTest.vo ="volatile-" + UUID.randomUUID().toString();
//				System.out.println(new Date() + "-" + VolatileTest.vo);
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// 读取变量
class ReadThread extends Thread {
	
	public void run() {
		try {
			while(true) {
//				System.out.println(new Date() + "-" + VolatileTest.vo);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
补充:
volatile变量具有synchronized的可见性特性, 但是不具备原子特性。
volatile变量可用于提供线程安全, 但是应用场景非常局限: 多个变量之间或者某个变量的当前值与修改后值之间没有约束。
因此, 单独使用volatile还不足以实现计数器、互斥锁或任何具有与多个变量相关的不变式.
再有就是实现原子性操作, Atomic类的效率要远远高于synchronized, 原因是synchronized实现原子操作的方式是通过加锁。

-----------------------------CAS-----------------------------------
CAS指的是现代CPU广泛支持的一种对内存中的共享数据进行操作的一种特殊指令。这个指令会对内存中的共享数据做原子的读写操作。
简单介绍一下这个指令的操作过程：首先, CPU会将内存中将要被更改的数据与期望的值做比较。然后, 当这两个值相等时，
CPU才会将内存中的数值替换为新的值。否则便不做操作。最后, CPU会将旧的数值返回。这一系列的操作是原子的。
它们虽然看似复杂, 但却是Java 5并发机制优于原有锁机制的根本。
简单来说: CAS的含义是: 我认为原有的值应该是什么, 如果是, 则将原有的值更新为新值. 否则不做修改, 并告诉我原来的值是多少。 
CSA的优点: Compare and Set 是一个非阻塞的算法, 这是它的优势。因为使用的是 CPU支持的指令，提供了比原有的并发机制更好的性能和伸缩性。
可以认为一般情况下性能更好，并且也更容易使用。

补充: 
cas(key, expect, new); 将内存中key的值与expect作比较, 如果一样, 才会将key改为new对应的值。
例如: 电商中的秒杀页面, 可以通过引入版本号的方式来实现, 即用户A秒杀时,会获取缓存中的库存数量对应的版本, 然后会拿着版本去减库存.
如果版本不一致,说明在高并发环境下,已经有用户修改了。此时需要重试。
*/
