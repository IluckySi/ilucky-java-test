package com.ilucky.test;

import java.util.Map;

/**
 * 注意: &&和&的区别
 * @author IluckySi
 *
 */
public class Test {

	public static void main(String[] args) {
	    Map<Thread, StackTraceElement[]> map = Thread.currentThread().getAllStackTraces();
	    
	    Thread.currentThread().dumpStack();
	   // Thread.currentThread().
	    
		if(a() || b()) { // if(a() | b()) {
			System.out.println("--a() && b()--");
		}
	}
	
	public static boolean a() {
		System.out.println("a");
		return true;
	}
	
	public static boolean b() {
		System.out.println("b");
		return false;
	}
}
/**
if(a() || b()) { 
a
--a() && b()--

if(a() | b()) { 
a
b
--a() && b()--
*/