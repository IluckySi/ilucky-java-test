package com.ilucky.test.jvm.other;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 * -XX:+PrintClassHistogram: 运行时查看系统中类的分布情况。
 * 通过这个jvm参数可以看到系统中占用字节最多的对象，以及实例数量和大小。
 * @author IluckySi
 *
 */
public class ClassHistogramTest {
	
	public static void main(String[] args) {
		int i =0;
		long j = 10l;
		boolean is = true;
		String name = "sidongxue";
		List<User> uList = new ArrayList<User>();
		User u = new User("IluckySi", "123456");
		User u2 = new User("IluckySi2", "223456");
		User u3 = new User("IluckySi3", "323456");
		uList.add(u);
		uList.add(u2);
		uList.add(u3);
		List<Product> pList = new ArrayList<Product>();
		Product p = new Product("p", 1);
		Product p2 = new Product("p2", 2);
		Product p3 = new Product("p3", 3);
		pList.add(p);
		pList.add(p2);
		pList.add(p3);
		System.out.println("=");
		while(true);
	}
	
	static class User {
		private String username;
		private String password;
		public User(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}
	}
	
	static class Product {
		private String name;
		private int price;
		
		public Product(String name, int price) {
			super();
			this.name = name;
			this.price = price;
		}
	}
}