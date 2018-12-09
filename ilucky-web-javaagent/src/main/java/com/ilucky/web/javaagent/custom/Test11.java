package com.ilucky.web.javaagent.custom;

public class Test11 implements TestInter {

    public void test() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test");
    }
    
    public void test(String a) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
    
    public int test(int a, int b) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       return a+b;
    }
    
    @Account(name="name",password="password")
    public void test3() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("annotation");
    }
}

