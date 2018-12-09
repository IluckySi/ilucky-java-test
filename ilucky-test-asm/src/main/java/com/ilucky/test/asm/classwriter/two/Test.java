package com.ilucky.test.asm.classwriter.two;

@AccountClasss(name="name",password="password")
public class Test {

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
    
    public void test(String a, int b) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a +"-"+b);
    }
    
    public void test(String a, Object o) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a +"-"+o);
    }
    
    public int test(int a, int b) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a+b);
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
