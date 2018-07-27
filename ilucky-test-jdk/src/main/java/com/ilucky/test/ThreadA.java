package com.ilucky.test;

public class ThreadA {
    
    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        System.out.println("ThreadB is start....");
        b.start();
        synchronized (b) {
            try {
                b.wait();
                System.out.println("ThreadB is Completed. Now back to main thread");
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Total is :" + b.total);
    }
}

class ThreadB extends Thread {
    
    int total;
    public void run() {
        synchronized (this) {
            System.out.println("ThreadB is running..");
            try {
                // 模拟业务
                Thread.sleep(2000);
                for (int i = 0; i <= 1000; i++) {
                    total += i;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB total is " + total);
            notify();
        }
    }
}  
/**
运行结果:
ThreadB is start....
ThreadB is running..
ThreadB total is 500500
ThreadB is Completed. Now back to main thread
Total is :500500

*/