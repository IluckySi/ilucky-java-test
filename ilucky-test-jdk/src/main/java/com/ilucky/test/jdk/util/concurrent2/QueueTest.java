package com.ilucky.test.jdk.util.concurrent2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author IluckySi
 * @since 20160204
 * 队列遵循先进先出的原则.分为如下几种:
 * LinkedBlockingQueue
 * ArrayBlockingQueue
 * PriorityBlockingQueue
 * SynchronousQueue
 */
public class QueueTest {

	public static void main(String[] args) throws Exception {
		test();
		
	}
	
	private static void test() throws Exception{
		LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();
		//除了如下向队列中添加元素的方法,还有put,但是put没有返回值,put,add和offer有什么区别呢?
		//void put(E e),如果BlockQueue没有空间,则调用此方法的线程被阻塞直到BlockingQueue里面有空间再继续.
		//boolean add(E e),如果BlockingQueue有空间,则返回true,否则抛出异常.
		//boolean offer(E e),如果BlockingQueue有空间,则返回true,否则返回false.
		System.out.println("add="+linkedBlockingQueue.add("a"));
		System.out.println("contains="+linkedBlockingQueue.contains("a"));
		System.out.println("size="+linkedBlockingQueue.size());
		System.out.println("offer="+linkedBlockingQueue.offer("b"));
		System.out.println("contains="+linkedBlockingQueue.contains("b"));
		System.out.println("size="+linkedBlockingQueue.size());
		System.out.println("=====================================");
		
		//可以向一个队列中添加一个集合.
		List<String> list1 = new ArrayList<String>();
		list1.add("c");
		list1.add("d");
		list1.add("e");
		list1.add("f");
		list1.add("g");
		list1.add("h");
		System.out.println("addAll="+linkedBlockingQueue.addAll(list1));
		System.out.println("containsAll="+linkedBlockingQueue.containsAll(list1));
		System.out.println("size="+linkedBlockingQueue.size());
		System.out.println("=====================================");
		
		//如下是从队列中取元素的几种方法.
		//E poll(E e),取走BlockingQueue里排在首位的对象,若BlockingQueue为空,则返回null.
		//E take(E e),取走BlockingQueue里排在首位的对象,若BlockingQueue为空,则调用此方法的线程被阻塞直到BlockingQueue有新的数据被加入.
		//E peek(E e),功能和take类似,但是不会真正从队列中移除,只是获取排在首位的对象.
		//int drainTo(E e),一次性从BlockingQueue获取所有可用的数据对象(还可以指定获取数据的个数),通过该方法,可以提升获取数据效率,
		//不需要多次分批加锁或释放锁.
		printQueue(linkedBlockingQueue);
		System.out.println("poll="+linkedBlockingQueue.poll());
		printQueue(linkedBlockingQueue);
		System.out.println("take="+linkedBlockingQueue.take());
		printQueue(linkedBlockingQueue);
		System.out.println("peek="+linkedBlockingQueue.peek());
		printQueue(linkedBlockingQueue);
		ArrayList<String> drainTo1 = new ArrayList<String>();
		System.out.println("drainTo="+linkedBlockingQueue.drainTo(drainTo1	, 2));
		printList(drainTo1);
		printQueue(linkedBlockingQueue);
		ArrayList<String> drainTo2 = new ArrayList<String>();
		System.out.println("drainTo="+linkedBlockingQueue.drainTo(drainTo2));
		printList(drainTo2);
		printQueue(linkedBlockingQueue);
		System.out.println("=====================================");
		
		// 通过如下实例查看add和offer的区别.
		LinkedBlockingQueue<String> linkedBlockingQueue2 = new LinkedBlockingQueue<String>(2);
		System.out.println(linkedBlockingQueue2.add("1"));
		System.out.println(linkedBlockingQueue2.add("2"));
		printQueue(linkedBlockingQueue2);
		//System.out.println(linkedBlockingQueue2.add("3"));
		//printQueue(linkedBlockingQueue2);
		LinkedBlockingQueue<String> linkedBlockingQueue3 = new LinkedBlockingQueue<String>(2);
		System.out.println(linkedBlockingQueue3.offer("1"));
		System.out.println(linkedBlockingQueue3.offer("2"));
		printQueue(linkedBlockingQueue3);
		System.out.println(linkedBlockingQueue3.offer("3"));
		printQueue(linkedBlockingQueue3);
		System.out.println("=====================================");
		// void clear() 清空队列的方法.
		LinkedBlockingQueue<String> linkedBlockingQueue4 = new LinkedBlockingQueue<String>(1);
		System.out.println(linkedBlockingQueue4.offer("1"));
		printQueue(linkedBlockingQueue4);
		linkedBlockingQueue4.clear();
		printQueue(linkedBlockingQueue4);
	}
	
	@SuppressWarnings("rawtypes")
	private static void printQueue(Queue queue) {
		if(!queue.isEmpty()) {
			Iterator<?> iterator = queue.iterator();
			while(iterator.hasNext()) {
				Object o = iterator.next();
				System.out.print(o);
			}
			System.out.println("");
		} else {
			System.out.println("Queue is empty!");
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static void printList(List list) {
		if(!list.isEmpty()) {
			Iterator<?> iterator = list.iterator();
			while(iterator.hasNext()) {
				Object o = iterator.next();
				System.out.print(o);
			}
			System.out.println("");
		} else {
			System.out.println("List is empty!");
		}
	}
}
