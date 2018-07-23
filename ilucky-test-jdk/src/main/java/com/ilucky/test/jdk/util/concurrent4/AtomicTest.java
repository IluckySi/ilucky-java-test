package com.ilucky.test.jdk.util.concurrent4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 在这里，CAS 指的是现代 CPU 广泛支持的一种对内存中的共享数据进行操作的一种特殊指令。这个指令会对内存中的共享数据做原子的读写操作。简单介绍一下这个指令的操作过程：首先，CPU 会将内存中将要被更改的数据与期望的值做比较。然后，当这两个值相等时，CPU 才会将内存中的数值替换为新的值。否则便不做操作。最后，CPU 会将旧的数值返回。这一系列的操作是原子的。它们虽然看似复杂，但却是 Java 5 并发机制优于原有锁机制的根本。简单来说，CAS 的含义是 “我认为原有的值应该是什么，如果是，则将原有的值更新为新值，否则不做修改，并告诉我原来的值是多少”。 CSA的优点：Compare and Set 是一个非阻塞的算法，这是它的优势。因为使用的是 CPU 支持的指令，提供了比原有的并发机制更好的性能和伸缩性。可以认为一般情况下性能更好，并且也更容易使用
 * @author IluckySi
 *
 */
public class AtomicTest {

	public static void main(String[] args) {
		//以AtomicLong为例.
		testAtomicLong();
	}

	private static void testAtomicLong() {
		AtomicLong atomicLong = new AtomicLong(0);
		System.out.println("get=" + atomicLong.get());
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("incrementAndGet=" + atomicLong.incrementAndGet());
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("addAndGet=" + atomicLong.addAndGet(2));
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("getAndAdd=" + atomicLong.getAndAdd(3));
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("incrementAndGet=" + atomicLong.incrementAndGet());
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("getAndIncrement=" + atomicLong.getAndIncrement());
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("decrementAndGet=" + atomicLong.decrementAndGet());
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("getAndDecrement=" + atomicLong.getAndDecrement());
		System.out.println("longValue=" + atomicLong.longValue());
		System.out.println("-----------------------------------------------------------");

		System.out.println("get=" + atomicLong.get());
	}
}
