package com;

public class SystemProperty {

	public static void main(String[] args) {
		 Object o = System.getProperty("sendProxy");
		 System.out.println(o==null);
	}
}
