package com.ilucky.test.jvm.stack;

/**
 * -Xss: 指定线程的最大栈空间，这个参数决定了函数调用的深度。栈越大，函数可以支持的嵌套调用就越多。
 * 另外，如果函数的参数和局部变量较多，会使得局部变量表膨胀，
 * 从而每一次函数调用就会占用更多的栈空间，最终导致函数的嵌套调用次数减少。
 * @author IluckySi
 *
 */
public class XssTest {
	
	public static int count=0;
	
	public static void main(String[] args) {
		try {
			test();
			// test2(1, 2 ,3, 4);
		} catch (Throwable e) {
			System.out.println("Deep of calling = "+count);
			e.printStackTrace();
		}
	}
	
	public static void test() {
		count++;
		test();
	}
	
	public static void test2(int a, int b, int c, int d) {
		count++;
		long aa=1l, bb=2l, cc=3l, dd=4l;
		test2(1, 2, 3, 4);
	}
}
/**
test
-Xss120k
Deep of calling = 1102
java.lang.StackOverflowError
	at com.ilucky.test.jvm.stack.XssTest.test(XssTest.java:26)
	at com.ilucky.test.jvm.stack.XssTest.test(XssTest.java:27)
	......

test
-Xss200K
Deep of calling = 2739
java.lang.StackOverflowError
	at com.ilucky.test.jvm.stack.XssTest.test(XssTest.java:26)
	at com.ilucky.test.jvm.stack.XssTest.test(XssTest.java:27)
	......

test2
-Xss120k
Deep of calling = 497
java.lang.StackOverflowError
	at com.ilucky.test.jvm.stack.XssTest.test2(XssTest.java:31)
	at com.ilucky.test.jvm.stack.XssTest.test2(XssTest.java:33)

test2
-Xss200K
Deep of calling = 1244
java.lang.StackOverflowError
	at com.ilucky.test.jvm.stack.XssTest.test2(XssTest.java:31)
	at com.ilucky.test.jvm.stack.XssTest.test2(XssTest.java:33)
*/
