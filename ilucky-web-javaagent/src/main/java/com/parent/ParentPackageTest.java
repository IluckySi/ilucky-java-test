package com.parent;

public class ParentPackageTest {

    public void testParent(long a, int b, String c) {
        try {
            System.out.println("=============testParent");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
