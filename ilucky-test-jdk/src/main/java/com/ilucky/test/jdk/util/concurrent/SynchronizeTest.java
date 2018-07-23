package com.ilucky.test.jdk.util.concurrent;

/**
 * 死锁
 * 如下这段代码会引起死锁，两个线程会互相等待对方释放锁。
 * 一般发生的原因是: 某个线程拿到锁后, 因为一些异常没有释放锁。
 * 避免死锁的方法:
 * 避免一个线程同时获取多个锁。
 * 避免一个线程同时占用多个资源。
 * 尝试使用定时锁，使用tryLock(timeout)来替代内部锁机制。
 * @author IluckySi
 *
 */
public class SynchronizeTest {

	public static String A = "a";
	public static String B = "b";
	
	public static void main(String[] args) {
		new SynchrnizedTest1T1().start();
		new SynchrnizedTest1T2().start();
	}
}

class SynchrnizedTest1T1 extends Thread {

	public void run() {
		try {
			System.out.println("--->" + Thread.currentThread().getName());
			synchronized(SynchronizeTest.A) {
				System.out.println("SynchrnizedTest1T1 A");
				Thread.sleep(1000);
				synchronized(SynchronizeTest.B) {
					System.out.println("SynchrnizedTest1T1 B");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SynchrnizedTest1T2 extends Thread {

	public void run() {
		try {
			System.out.println("--->" + Thread.currentThread().getName());
			synchronized(SynchronizeTest.B) {
				System.out.println("SynchrnizedTest1T2 A");
				Thread.sleep(1000);
				synchronized(SynchronizeTest.A) {
					System.out.println("SynchrnizedTest1T2 B");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
通过jstack指令查看dump线程信息, 从而定位问题。
C:\Users\IluckySi>jstack 11572
2017-05-21 18:15:58
Full thread dump Java HotSpot(TM) 64-Bit Server VM (24.80-b11 mixed mode):

"DestroyJavaVM" prio=6 tid=0x00000000004bd800 nid=0x248c waiting on condition [0
x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Thread-1" prio=6 tid=0x000000000b092800 nid=0x16b8 waiting for monitor entry [0
x000000000bc8f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.ilucky.test.jdk.util.concurrent.SynchrnizedTest1T2.run(Synchroniz
eTest.java:52)
        - waiting to lock <0x00000000e0a16968> (a java.lang.String)
        - locked <0x00000000e0a16998> (a java.lang.String)

"Thread-0" prio=6 tid=0x000000000b08f000 nid=0x2df0 waiting for monitor entry [0
x000000000bb0f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.ilucky.test.jdk.util.concurrent.SynchrnizedTest1T1.run(Synchroniz
eTest.java:34)
        - waiting to lock <0x00000000e0a16998> (a java.lang.String)
        - locked <0x00000000e0a16968> (a java.lang.String)

"Service Thread" daemon prio=6 tid=0x000000000b074800 nid=0x1c4c runnable [0x000
0000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" daemon prio=10 tid=0x000000000b072000 nid=0x25d8 waiting on
 condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" daemon prio=10 tid=0x0000000009a0f800 nid=0x232c waiting on
 condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" daemon prio=10 tid=0x0000000009a0a000 nid=0x2598 waiting on co
ndition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" daemon prio=10 tid=0x0000000009a05000 nid=0x2004 runnable [0
x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" daemon prio=8 tid=0x000000000999a000 nid=0x2c38 in Object.wait() [0x
000000000abff000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000e0904858> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:135)
        - locked <0x00000000e0904858> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:151)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

"Reference Handler" daemon prio=10 tid=0x0000000009993000 nid=0x2b80 in Object.w
ait() [0x000000000a8ef000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000e0904470> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Object.java:503)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:133)
        - locked <0x00000000e0904470> (a java.lang.ref.Reference$Lock)

"VM Thread" prio=10 tid=0x000000000998d800 nid=0x2298 runnable

"GC task thread#0 (ParallelGC)" prio=6 tid=0x000000000235c800 nid=0xe0c runnable


"GC task thread#1 (ParallelGC)" prio=6 tid=0x000000000235e000 nid=0x2274 runnabl
e

"GC task thread#2 (ParallelGC)" prio=6 tid=0x000000000235f800 nid=0x23fc runnabl
e

"GC task thread#3 (ParallelGC)" prio=6 tid=0x0000000002361800 nid=0x1b2c runnabl
e

"VM Periodic Task Thread" prio=10 tid=0x000000000b085800 nid=0x1b6c waiting on c
ondition

JNI global references: 121


Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x0000000009999a68 (object 0x00000000e0a16968, a java.
lang.String),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x0000000009998728 (object 0x00000000e0a16998, a java.
lang.String),
  which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
        at com.ilucky.test.jdk.util.concurrent.SynchrnizedTest1T2.run(Synchroniz
eTest.java:52)
        - waiting to lock <0x00000000e0a16968> (a java.lang.String)
        - locked <0x00000000e0a16998> (a java.lang.String)
"Thread-0":
        at com.ilucky.test.jdk.util.concurrent.SynchrnizedTest1T1.run(Synchroniz
eTest.java:34)
        - waiting to lock <0x00000000e0a16998> (a java.lang.String)
        - locked <0x00000000e0a16968> (a java.lang.String)

Found 1 deadlock.


C:\Users\IluckySi>
*/
