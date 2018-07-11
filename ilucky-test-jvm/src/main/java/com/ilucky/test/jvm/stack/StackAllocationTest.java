package com.ilucky.test.jvm.stack;

/**
 * 栈上分配: 逃逸分析
 * @author IluckySi
 *
 */
public class StackAllocationTest {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i=0; i<= 10000; i++) {
			alloc();
		}
		long end = System.currentTimeMillis();
		System.out.println("======>"+(end - start));
	}
	
	static User u = null;
	public static void alloc() {
		// User u = new User();
		u = new User();
		u.username = "IluckySi";
		u.password="321";
	}
	
	public static class User{
		public String username= "sdx";
		public String password = "123";
	}
}