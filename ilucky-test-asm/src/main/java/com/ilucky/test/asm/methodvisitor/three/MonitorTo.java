package com.ilucky.test.asm.methodvisitor.three;

/**
 * 目标类
 * @author IluckySi
 *
 */
public class MonitorTo {
   
    public int monitor() {
        long start = System.currentTimeMillis();
        Submitter.start("className", "methodName", start);
        System.out.println("---begin");
        int i = 1;
        int j = 2;
        int r = i + j;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            long end = System.currentTimeMillis();
            Submitter.end("className", "methodName",end, null, e);
        }
        System.out.println("---end");
        long end = System.currentTimeMillis();
        Submitter.end("className", "methodName",end, r, null);
        return r;
    }
}