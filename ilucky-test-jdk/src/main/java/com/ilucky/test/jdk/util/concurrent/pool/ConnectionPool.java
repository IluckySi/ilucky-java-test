package com.ilucky.test.jdk.util.concurrent.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 数据库连接池
 * @author IluckySi
 *
 */
public class ConnectionPool {

	private LinkedList<Connection> pool = new LinkedList<Connection>();
	
	/**
	 * 初始化连接池
	 * @param initialSize
	 */
	public ConnectionPool(int initialSize) {
		if(initialSize > 0) {
			for(int i=0; i<initialSize; i++) {
				pool.addLast(ConnectionDriver.createConnection());
			}
		}
	}
	
	/**
	 * 如果mills时间内无法获取到连接，返回null
	 * @param mills
	 * @return
	 * @throws InterruptedException 
	 */
	public Connection fetchConnection(long mills) throws InterruptedException {
		synchronized(pool) {
			// 完全超时, 一直等待空闲链接.
			if(mills <= 0) {
				while(pool.isEmpty()) {
					pool.wait();
				}
				return pool.removeFirst();
			} else {
				long future = System.currentTimeMillis() + mills;
				long remaining = mills;
				while(pool.isEmpty() && remaining > 0) {
				    // 注意: 这里一直困扰的一个问题？ 是不是线程等待remaining时间之后, 才会去尝试获取数据库连接呢?
				    // 不是的, 即如果remaining=3秒. 可能的情况有如下两种:
				    // 1. 等了1秒之后, 有线程releaseConnection了, 即pool.notifyAll了, 此时当前线程试着获取锁,
				    // 如果能拿到继续判断pool是不是空的,如果是空的(说明其他线程获取了锁,并获取了Connection), 
				    // 再判断时间remaining, 如果依旧大于0, 继续wait; 如果不是空的, 则去pool里面获取Connection
				    // 2. 等了3秒之后, 不再继续等待
					pool.wait(remaining);
					remaining = future - System.currentTimeMillis();
				}
				Connection connection = null;
				if(!pool.isEmpty()) {
					connection = pool.removeFirst();
				}
				return connection;
			}
		}
	}
	
	/**
	 * 释放连接
	 * @param connection
	 */
	public void releaseConnection(Connection connection) {
		if(connection != null) {
			synchronized(pool) {
				pool.add(connection);
				// 释放连接后需要进行通知, 这样其他消费者能够感知到连接池中已经归还了一个链接。
				pool.notifyAll();
			}
		}
	}
	
}
