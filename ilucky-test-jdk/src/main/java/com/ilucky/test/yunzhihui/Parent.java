//package com.ilucky.test.yunzhihui;
//
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class Parent {
//
//    private static LinkedBlockingQueue<String> l = new LinkedBlockingQueue<String>();
//    
//    public static void main(String[] args) {
//        getTestData();
//        Parent p = new Parent();
//        new Thread(new Child(p, l)).start();
//        new Thread(new Child(p, l)).start();
//        new Thread(new Child(p, l)).start();
//    }
//    
//    
//    private static void getTestData() {
//        for(int i=0; i<=100; i++) {
//            l.add("str="+i);
//        }
//    }
//
//    public void process(String s) {
//        try {
//            Thread.sleep(50);
//            System.out.println("---s="+s);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
