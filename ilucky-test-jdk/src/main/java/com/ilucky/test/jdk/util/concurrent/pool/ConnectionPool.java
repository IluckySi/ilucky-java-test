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
