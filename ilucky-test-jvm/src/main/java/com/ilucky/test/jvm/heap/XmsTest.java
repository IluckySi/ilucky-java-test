package com.ilucky.test.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存配置: 
 * -Xms: jvm堆内存
 * -Xmx: jvm最大可使用堆内存
 * 注意: 这两个参数一般配置为一样的，避免每次垃圾回收完成后，jvm重新分配内存
 * @author IluckySi
 *
 */
public class XmsTest {

	public static void main(String[] args) {
		 List<byte[]> bList = new ArrayList<byte[]>();
		int i = 0;
		while (true) {
			// 一次向集合中添加1024*1024 byte=1024KB=1MB.
			byte[] b = new byte[1024 * 1024];
			bList.add(b);
			System.out.println("=========>" +(++i) + "MB");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/**
1. -Xms4M -Xmx4M
=========>1MB
=========>2MB
=========>3MB
=========>4MB
=========>5MB
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at com.ilucky.test.jvm.heap.HeapTest.main(HeapTest.java:18)

2. -Xms4M
=========>1MB
=========>2MB
=========>3MB
=========>4MB
=========>5MB
=========>6MB
=========>7MB
=========>8MB
...

3. -Xmx4M
=========>1MB
=========>2MB
=========>3MB
=========>4MB
=========>5MB
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at com.ilucky.test.jvm.heap.HeapTest.main(HeapTest.java:18)

*/