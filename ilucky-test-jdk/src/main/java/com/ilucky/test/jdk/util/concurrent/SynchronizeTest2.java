package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;

/**
 * 使用synchronized同步
 * 
 * @author IluckySi
 *
 */
public class SynchronizeTest2 {

	public static String SYN = "syn";
	
	public static void main(String[] args) {
	    System.out.println(new Date());
		new SynchrnizedTest2T1().start();
		new SynchrnizedTest2T2().start();
	}
}

/**
 * 在线程里给代码块加同步
 * @author IluckySi
 *
 */
class SynchrnizedTest2T1 extends Thread {

	public void run() {
		try {
		    // synchronized (SynchronizeTest2.SYN) { 
		    // synchronized (SynchronizeTest2.class) {
		    synchronized (SynchrnizedTest2T1.class) {
		        Thread.sleep(2000);
		        System.out.println(new Date() + "--->" + Thread.currentThread().getName());
		    }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SynchrnizedTest2T2 extends Thread {

	public void run() {
		try {
		    // synchronized (SynchronizeTest2.SYN) { 
		    // synchronized (SynchronizeTest2.class) {
		    synchronized (SynchrnizedTest2T2.class) {
		        Thread.sleep(3000);
		        System.out.println(new Date() + "--->" + Thread.currentThread().getName());
		    }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
注意观察时间间隔:
synchronized (SynchronizeTest2.SYN) { 
Thu Jul 26 10:54:53 GMT+08:00 2018
Thu Jul 26 10:54:55 GMT+08:00 2018--->Thread-0
Thu Jul 26 10:54:58 GMT+08:00 2018--->Thread-1

synchronized (SynchronizeTest2.class) {
Thu Jul 26 11:12:59 GMT+08:00 2018
Thu Jul 26 11:13:01 GMT+08:00 2018--->Thread-0
Thu Jul 26 11:13:04 GMT+08:00 2018--->Thread-1

注意: 如下写法是有问题的, 因为每个线程都有自己的锁, 所以不能达到同步的目的
synchronized (SynchrnizedTest2T1.class) {
synchronized (SynchrnizedTest2T2.class) {
Thu Jul 26 11:13:44 GMT+08:00 2018
Thu Jul 26 11:13:46 GMT+08:00 2018--->Thread-0
Thu Jul 26 11:13:47 GMT+08:00 2018--->Thread-1
*/