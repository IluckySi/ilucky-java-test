package com.ilucky.test.jvm.vm;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * -Xmx64m
 * 
 * @author IluckySi
 *
 */
public class HeapTest {

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<String, String>();
		while (true) {
			Random r = new Random();
			r.nextLong();
			cache.put(String.valueOf(r), String.valueOf(r));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
