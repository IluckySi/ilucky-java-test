package com.ilucky.test.jdk.util.concurrent.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 模拟数据库连接驱动
 * 即通过代理的方式，模拟向数据库提交数据需要100毫秒。
 * 
 * @author IluckySi
 *
 */
public class ConnectionDriver {

	/**
	 * 内部类
	 * 代理类关联的InvocationHandler。
	 * @author IluckySi
	 *
	 */
	static class ConnectionHandler implements InvocationHandler {

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if(method.getName().equals("commit")) {
				TimeUnit.MICROSECONDS.sleep(100);
			}
			return null;
		}
	}

	/**
	 * 创建一个Connection的代理, 在commit时休眠100毫秒.
	 * @return
	 */
	public static final Connection createConnection() {
		return (Connection)Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
				new Class<?>[]{Connection.class}, new ConnectionHandler());
	}
}
