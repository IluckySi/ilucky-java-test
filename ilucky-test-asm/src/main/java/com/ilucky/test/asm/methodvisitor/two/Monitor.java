package com.ilucky.test.asm.methodvisitor.two;

/**
 * 原始类
 * 
 * @author IluckySi
 *
 */
public class Monitor {

    public void monitor() {
        System.out.println("---begin");
        int i = 1;
        int j = 2;
        int r = i + j;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---end");
    }
}
