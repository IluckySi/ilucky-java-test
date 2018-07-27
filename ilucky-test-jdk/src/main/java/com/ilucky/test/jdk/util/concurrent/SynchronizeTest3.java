package com.ilucky.test.jdk.util.concurrent;

import java.util.Date;

/**
 * 使用synchronized同步
 * 
 * @author IluckySi
 *
 */
public class SynchronizeTest3 implements Runnable {

    // public String SYN = "syn";
    // public static String SYN = "syn";
    public String SYN = new String("syn");
    
	public static void main(String[] args) {
	    System.out.println(new Date());
		new Thread(new SynchronizeTest3()).start();
		new Thread(new SynchronizeTest3()).start();
	}
	

    @Override
    public void run() {
        syn();
    }
    
    public void syn() {
	// public synchronized void syn() {
	// public static synchronized void syn() {
        synchronized(SYN) {
        // synchronized(SynchronizeTest3.class) {
    	    try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new Date() + "--->" + Thread.currentThread().getName());
	}
}
/**
 * 输出结果如下, 注意时间间隔
 * 
 * 同步失败: 实例方法是对象(实例)级别的
 * public void syn() {
 * Thu Jul 26 11:17:35 GMT+08:00 2018
 * Thu Jul 26 11:17:37 GMT+08:00 2018--->Thread-1
 * Thu Jul 26 11:17:37 GMT+08:00 2018--->Thread-0
 * 
 * 同步失败: 实例方法是对象(实例)级别的
 * public synchronized void syn() {
 * Thu Jul 26 11:33:24 GMT+08:00 2018
 * Thu Jul 26 11:33:26 GMT+08:00 2018--->Thread-1
 * Thu Jul 26 11:33:26 GMT+08:00 2018--->Thread-0
 * 
 * 同步成功: 静态方法是类级别的
 * public static synchronized void syn() {
 * Thu Jul 26 11:00:10 GMT+08:00 2018
 * Thu Jul 26 11:00:12 GMT+08:00 2018--->Thread-0
 * Thu Jul 26 11:00:14 GMT+08:00 2018--->Thread-1
 * 
 * 同步成功: SynchronizeTest3是类级别的
 * public void syn() {
 * synchronized(SynchronizeTest3.class) {
 * Thu Jul 26 11:19:29 GMT+08:00 2018
 * Thu Jul 26 11:19:31 GMT+08:00 2018--->Thread-0
 * Thu Jul 26 11:19:33 GMT+08:00 2018--->Thread-1
 * 
 * 主要关注如下情况: 深入理解 String SYN = "syn";和String SYN = new String("syn");的区别
 * 同步成功: 明明String是对象(实例)级别的, 为什么还能同步成功呢? 和String本身有关系,下面会做分析.
 * public void syn() {
 * public String SYN = "syn";
 * synchronized(SYN) { 
 * Thu Jul 26 11:19:29 GMT+08:00 2018
 * Thu Jul 26 11:19:31 GMT+08:00 2018--->Thread-0
 * Thu Jul 26 11:19:33 GMT+08:00 2018--->Thread-1
 * 
 * 同步成功
 * public void syn() {
 * public static String SYN = "syn";
 * synchronized(SynchronizeTest3.SYN) { 
 * Thu Jul 26 11:19:29 GMT+08:00 2018
 * Thu Jul 26 11:19:31 GMT+08:00 2018--->Thread-0
 * Thu Jul 26 11:19:33 GMT+08:00 2018--->Thread-1
 * 
 * 同步失败
 * public String SYN = new String("syn");
 * synchronized(SYN) {
 * Thu Jul 26 11:25:43 GMT+08:00 2018
 * Thu Jul 26 11:25:45 GMT+08:00 2018--->Thread-0
 * Thu Jul 26 11:25:45 GMT+08:00 2018--->Thread-1
 * 
 * 补充:
 * String是一个对象,在一个类中如果声明了String SYN = "syn"; 
 * JVM会在常量池中先查找有有没有一个值为"syn"的对象,如果有,就会把它赋给当前引用,即原来那个引用和现在这个引用指点向了同一对象.
 * 在一个类中如果声明了String SYN = new String("syn");创建对象(实例)时会在堆上分别为每个对象创建一个SYN对象,即不同的对象. 
 */
