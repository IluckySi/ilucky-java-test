package com.ilucky.test.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存配置: 
 * -XX:+HeapDumpOnOutOfMemoryError 参数表示当JVM发生OOM时，自动生成DUMP文件。
 * -XX:HeapDumpPath=F:\oom 参数表示生成DUMP文件的路径, 如果不指定文件名，会有默认值。
 * 注意:
 * 除了使用上面的方式导出堆文件，还可以在服务运行时，执行如下指令，导出堆文件:
 * jmap -dump:live,format=b,file=heap.hprof 3514
 * @author IluckySi
 *
 */
public class HeapDumpTest {

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
1. -Xms4M -Xmx4M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=F:\oom
=========>1MB
=========>2MB
=========>3MB
=========>4MB
=========>5MB
java.lang.OutOfMemoryError: Java heap space
Dumping heap to F:\oom\java_pid8600.hprof ...
Heap dump file created [6416525 bytes in 0.117 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at com.ilucky.test.jvm.heap.HeapDumpTest.main(HeapDumpTest.java:20)

然后，查看F:\oom目录: 发现java_pid8600.hprof文件。
*/
