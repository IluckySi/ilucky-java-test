package com.ilucky.test.jdk.util.concurrent;

import java.util.UUID;
import java.util.concurrent.Exchanger;

/**
 * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
 * 当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了exchange()方法，
 * 然后以线程安全的方式交换数据，之后线程A和B继续运行
 * 参考:
 * http://blog.csdn.net/andycpp/article/details/8854593
 * @author IluckySi
 *
 */
public class ExchangerTest {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<String>();  
		new ExchangerTestConsumer(exchanger).start();
		new ExchangerTestProducer(exchanger).start();
	}
}

// 数据消费者
class ExchangerTestConsumer extends Thread {
	
	private Exchanger<String> exchanger;
	
	ExchangerTestConsumer(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}
	
	public void run() {
        try {  
        	String str = null;
        	while(true) {
        		 System.out.println(exchanger.exchange(str));  
        	}
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
	}
}

// 数据生产者
class ExchangerTestProducer extends Thread {
	
	private Exchanger<String> exchanger;
		
	ExchangerTestProducer(Exchanger<String> exchanger) {
			this.exchanger = exchanger;
	}
	
	public void run() {
        try {  
        	while(true) {
        		 exchanger.exchange(UUID.randomUUID().toString());  
        		 Thread.sleep(2000);
        	}
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
	}
}
/**
输出结果:
16bc8599-e711-4608-9a33-52fc878943bb
78182875-059f-4415-a6dd-4669e3d64233
99566a46-6a6f-4121-99a4-b1a56d9d4775
*/
