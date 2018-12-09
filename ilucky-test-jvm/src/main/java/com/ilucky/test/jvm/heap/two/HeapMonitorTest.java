package com.ilucky.test.jvm.heap.two;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class HeapMonitorTest implements Runnable  {

	public static void main(String[] args) {
		// 注意： 线程start和run的区别
		new Thread(new HeapAddTest()).start();
		new Thread(new HeapMonitorTest()).start();
	}

	@Override
	public void run() {
		   while (true) {
			   MemoryMXBean mxbean = ManagementFactory.getMemoryMXBean();
		        System.out.println("getObjectName:" + mxbean.getObjectName());
		        MemoryUsage heap = mxbean.getHeapMemoryUsage();
		        System.out.println("getCommitted:" + heap.getCommitted());
		        System.out.println("getInit:" + heap.getInit());
		        System.out.println("getMax:" + heap.getMax());
		        System.out.println("getUsed:" + heap.getUsed());
		        System.out.println("============================");
		        try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		   }
	}
}
