package com.ilucky.test.jdk.util.concurrent3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author IluckySi
 *
 */
public class ReadWriteLockTest {

	private static Lock reentrantLock = new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private int value;
	
	public static void main(String[] args) {
		final ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
		final Random r = new Random();
		Runnable readRunnable = new Runnable() {

			public void run() {
				try {
					readWriteLockTest.handleRead(reentrantLock);
					readWriteLockTest.handleRead(readLock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable writeRunnable = new Runnable() {

			public void run() {
				try {
					readWriteLockTest.handleWrite(reentrantLock, r.nextInt());
					readWriteLockTest.handleWrite(writeLock, r.nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		for(int i=0; i<10; i++) {
			new Thread(readRunnable).start();
			new Thread(writeRunnable).start();
		}
		
	}
	
	public Object handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			// 模拟读
			Thread.sleep(1000);
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public void handleWrite(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			// 模拟写
			Thread.sleep(1000);
			this.value = index;
		} finally {
			lock.unlock();
		}
	}
}
