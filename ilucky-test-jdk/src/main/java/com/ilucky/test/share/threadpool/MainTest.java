//package com.ilucky.test.share.threadpool;
//
//import java.util.UUID;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * 普通生产者和消费者模式与线程池的区别
// * @author IluckySi
// *
// */
//public class MainTest {
//
//	public static void main(String[] args) {
//
//		// 模拟从kafka拉出来数据源
//		BlockingQueue<Bo> kafkaQueue = new LinkedBlockingQueue<Bo>(10000);
//		Executor kafkaQueueExecutor = Executors.newSingleThreadExecutor();
//		kafkaQueueExecutor.execute(new Runnable() {
//
//			@Override
//			public void run() {
//				while (true) {
//					try {
//						String uuid = UUID.randomUUID().toString();
//						kafkaQueue.put(new Bo("1", uuid, "1.2.3.4", "中国", "北京", "北京"));
//						System.out.println("=====队列大小======"+ kafkaQueue.size());
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		
//		// 第一种方法: 从kafka接入数据放到队列中。
//		int threadNum = 1;
//		Executor FirstQueueExecutor = Executors.newFixedThreadPool(100);
//		for(int i=0; i<threadNum; i++) {
//			FirstQueueExecutor.execute(new BoProcessor(kafkaQueue));
//		}
//		// 打印线程池信息。。。。。。
//		
//		// 第二种方法
//		
//	}
//}
