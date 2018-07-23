package com.ilucky.test.jdk.util.list;

import java.util.LinkedList;

/**
 * @author IluckySi
 *
 */
public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.add("s");
		list.add("d");
		list.add("x");
		list.add(1, "O");
		System.out.println(list); // [s, O, d, x]
	}
}
