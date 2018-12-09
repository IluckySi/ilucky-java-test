package com.ilucky.test.asm.methodvisitor.three;

/**
 * 原始类
 * @author IluckySi
 *
 */
public class Monitor {

    public void monitor() {
        System.out.println("---begin");
        int i = 12;
        int j = 2;
        int r = i + j;
        try {
            Thread.sleep(1000);
            System.out.println("---r="+r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---end");
    }
    
    // 有返回值
    public int monitor2() {
        System.out.println("---begin");
        int i = 12;
        int j = 2;
        int r = i + j;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---end");
        return r;
    }
    
    // 多个参数
    public String monitor3(String s1, long l1, long l2, int i, long l3) {
        System.out.println("---begin");
        String hello = "Hello";
        String world = "World~";
        String r = s1 + "-" + (l1 + l2 + l3 + i) + ":" + hello + "-" + world;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---end");
        return r;
    }
    
    // 模拟异常
    public int monitor4() {
        System.out.println("---begin");
        int i = 12;
        int j = 2;
        int r = i + j;
        try {
            r = r/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---end");
        return r;
    }
    
    // 模拟异常
    public int monitor5() {
        System.out.println("---begin");
        int i = 12;
        int j = 2;
        int r = i + j;
        r = r/0;
        System.out.println("---end");
        return r;
    }
}
