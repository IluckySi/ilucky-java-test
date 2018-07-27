package com.ilucky.test.jdk.lang.thread;

/**
 * sleep和interrupt
 * 
 * @author IluckySi
 *
 */
public class SleepTest {

    public static void main(String[] args) {
        Thread t = new Thread(new SleepThread());
        t.start();
        t.interrupt();
    }
}


class SleepThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Start......");
        try {
            Thread.sleep(3000);
            System.out.println("Stop......");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error......");
        }
    }
}
/**
输出结果:
Start......
java.lang.InterruptedException: sleep interrupted

at java.lang.Thread.sleep(Native Method)
at com.ilucky.test.jdk.lang.thread.SleepThread.run(SleepTest.java:25)
at java.lang.Thread.run(Thread.java:745)
Error......
*/