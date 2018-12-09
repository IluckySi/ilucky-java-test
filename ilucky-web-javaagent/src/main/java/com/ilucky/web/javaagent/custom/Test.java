package com.ilucky.web.javaagent.custom;

import com.ilucky.web.javaagent.controller.session.User;

public class Test {

    // OK
    public void test() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test");
    }
    
    // OK
    public void test(String a) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
    
    // OK
    public void test(String a, int b) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a +"-"+b);
    }
    
    // OK
    public void test(String a, Object o) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a +"-"+o);
    }
    
    // OK
    public void test(String a, User u) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        u = new User();
        u.setUsername("IluckySi");
        u.setPassword("123456");
        System.out.println(a +"-"+u);
    }
    
    // OK   
    public int test(int a, int b) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a+b);
        return a+b;
    }
    
    // OK
    public User test2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User u = new User();
        u.setUsername("IluckySi");
        u.setPassword("123456");
        return u;
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
