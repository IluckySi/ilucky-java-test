package com.ilucky.test.jdk.lang.thread;

/**
 * 此demo通过测试验证了sleep(0)给jvm带来的性能影响: 导致jvm进程cpu使用率大幅度升高. 参考截图, 原因是什么呢?
 * Thread.sleep(0)的作用不单单是让线程睡了0ms, 它会硬性触发CPU的一个动作:
 * 计算所有等待线程的优先级,以确定下一个CPU控制权的获得者.这样cpu基本是没有空闲的时间, 从而导致了cpu占用率居高不下
 * 
 * 参考:
 * https://blog.csdn.net/lqg1122/article/details/7916844
 * 
 * @author IluckySi
 *
 */
public class SleepTest2 {

    public static void main(String[] args) {
        Thread t = new Thread(new SleepThread2());
        t.start();
    }
}


class SleepThread2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                // Thread.sleep(0);
                // Thread.sleep(2);
                Thread.sleep(5);
                // Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
