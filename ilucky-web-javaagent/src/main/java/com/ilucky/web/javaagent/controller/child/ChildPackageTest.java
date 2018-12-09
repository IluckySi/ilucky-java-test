package com.ilucky.web.javaagent.controller.child;

public class ChildPackageTest {

    public void testChild(long a, int b, String c) {
        try {
            System.out.println("=============testChild");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
