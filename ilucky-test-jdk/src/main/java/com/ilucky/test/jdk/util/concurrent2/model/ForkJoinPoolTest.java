//package com.ilucky.test.jdk.util.concurrent2.model;
//
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.Future;
//import java.util.concurrent.RecursiveTask;
//
///**
// * 把一个大的任务分(fork)成几个小的任务，最后再把这些小任务的执行结果合并(join)起来。
// * 分而治之的思想: 例如: mapreduce就是用的这种思想。
// * 
// * @author IluckySi
// *
// */
//public class ForkJoinPoolTest {
//
//	/**
//	 * 计算1-10亿的和。
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		System.out.println(test());
//		System.out.println(test2());
//	}
//	
//	public static long test() {
//		long start = System.currentTimeMillis();
//		long sum = 0;
//		for(int i=0; i<1000000000; i++) {
//			sum+=i;
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("======>"+(end - start));
//		return sum;
//	}
//	
//	public static long test2() {
////		ForkJoinPool pool = new ForkJoinPool();
////        Future<Long> future = pool.submit(new SumTask(arr , 0 , arr.length));
////		return future.get();
//		return 1l;
//	}
//	
//	class SumTask extends RecursiveTask<Long> {
//
//		SumTask () {
//			
//		}
//		
//		@Override
//		protected Long compute() {
//			
//			return null;
//		}
//		
//	}
//}
