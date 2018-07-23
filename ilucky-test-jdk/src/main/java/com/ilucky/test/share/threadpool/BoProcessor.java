//package com.ilucky.test.share.threadpool;
//
//import java.util.concurrent.BlockingQueue;
//
//public class BoProcessor implements Runnable {
//
//	BlockingQueue<Bo> kafkaQueue;
//	BoProcessor(BlockingQueue<Bo> kafkaQueue) {
//		this.kafkaQueue = kafkaQueue;
//	}
//	
//	@Override
//	public void run() {
//		try {
//			while(true) {
//				Bo bo = kafkaQueue.take();
//				System.out.println(Thread.currentThread().getId() + "----" + bo);
//				
//				// 模拟业务处理
//				Thread.sleep(400);
//			}
//		
//		} catch (InterruptedException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//	}
//}
