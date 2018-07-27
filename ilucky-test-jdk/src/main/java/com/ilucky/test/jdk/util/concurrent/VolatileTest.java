package com.ilucky.test.jdk.util.concurrent;


/**
 * volatile作用是在多处理器开发中保证了共享变量的可见性。
 * 可见性的意思是: 当一个线程修改一个共享变量时，另外一个线程能读到这个修改的值。
 * 
 * volatile之所以能实现多线程共享变量的可见性，原理如下:
 * 当一个线程操作volatile修饰的变量时, 会发生如下两件事情:
 * 1. 将当前处理器缓存行的数据写回到系统内存.
 * 2. 这个写回内存的操作会使在其他cpu里缓存了该内存地址的数据无效。
 * 
 * volatile和synchronized的区别: volatile是轻量级的锁，synchronized是重量级的锁,
 * volatile的执行成本更低，因为不会引起线程上下文的切换。
 * 另外，他们都能确保程序的可见性，但是也是有区别的:
 * 如果多个线程都进行读写, 一定要在用synchronized, volatile只确保了可见性，并不能确保原子性。
 * 即volatile的使用场景是: 有一个线程进行操作，其他线程可以感知到变化。
 * 因此, 单独使用volatile还不足以实现计数器、互斥锁或任何具有与多个变量相关的不变式.
 * 
 * 总结说来，synchronized实现的同步能确保线程安全，实现可见性和原子性；但是代价大，效率低，更慢；
 * volatile能够实现多线程操作产生变化的可见性，并且效率高, 但是不能实现原子性。
 * atomic类是一种更轻量级的方法实现可见性和原子性, 效率要远远高于synchronized, 
 * 原因是synchronized实现原子操作的方式是通过加锁。后面会详细介绍atomic

 * 参考:
 * https://segmentfault.com/a/1190000002679340
 * @author IluckySi
 *
 */
public class VolatileTest {

    private boolean isStop = false;
    // volatile private boolean isStop = false;
    
    private void changeStatus() {
        isStop = !isStop;
    }
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("尝试退出开始");
                while (true) {
                    // 一直检测isStop变量是否更新
                    if (test.isStop) {
                        System.out.println("退出成功");
                        System.exit(0);
                    }
                    // 添加休眠时间
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };
        thread1.start();
 
        try {
            Thread.sleep(3000);
            test.changeStatus();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 
private boolean isStop = false;
一直不退出

volatile private boolean isStop = false;
3秒之后退出

private boolean isStop = false;
// 添加休眠时间
try {
    Thread.sleep(5);
} catch (InterruptedException e) {
    e.printStackTrace();
}
3秒之后退出

补充: 线程循环时,为什么需要适当的sleep, 哪怕是5毫秒
参考: https://blog.csdn.net/lqg1122/article/details/7916844
Thread.sleep(0)的作用不是单单的说线程睡了0ms，它会硬性触发CPU的一个动作,
计算所有等待线程的优先级，以确定下一个CPU控制权的获得者,
不断的计算，这样cpu基本是没有空闲的时间，导致了cpu占用率居高不下
*/
