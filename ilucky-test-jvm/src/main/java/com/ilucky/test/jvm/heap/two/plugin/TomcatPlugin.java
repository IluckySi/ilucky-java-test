package com.ilucky.test.jvm.heap.two.plugin;

import java.util.Random;

public class TomcatPlugin {

	public CompleteClass c;
	private String name = "1";
	private String name2 = "2";
	private String name3 = "3";
	private String name4 = "4";
	private String name5 = "5";
	private String name6 = "6";
	private String name7 = "7";
	private String name8 = "8";
	private String name9 = "9";
	private String name10 = "10";
	private String name11 = "11";
	private String name12 = "12";
	
	public TomcatPlugin( CompleteClass c) {
		this.c = c;
	}
	
	public void plugin() {
		// System.out.println("tomcat");
		String s = new String(""+new Random(Integer.MAX_VALUE).nextInt());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}