package com.ilucky.test.asm.methodvisitor.three;

/**
 * 原始类
 * @author IluckySi
 *
 */
public class Monitor {

    public int monitor() {
        int i = 0;
        int r = 0;
        try {
            r = 10/i;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return r;
    }
}