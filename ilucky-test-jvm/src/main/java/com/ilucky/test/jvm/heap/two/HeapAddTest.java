package com.ilucky.test.jvm.heap.two;

import com.ilucky.test.jvm.heap.two.plugin.CompleteClass;

public class HeapAddTest  implements Runnable {

	@Override
	public void run() {
		while(true) {
			CompleteClass c = new CompleteClass();
			c.plugin();
		   try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
