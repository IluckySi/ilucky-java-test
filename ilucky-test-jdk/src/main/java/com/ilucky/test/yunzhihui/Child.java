//package com.ilucky.test.yunzhihui;
//
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class Child implements Runnable {
//
//    private Parent p;
//    private LinkedBlockingQueue<String> l;
//    
//    public Child(Parent p, LinkedBlockingQueue<String> l) {
//        this.p = p;
//        this.l = l;
//    }
//    
//    @Override
//    public void run() {
//        while(true) {
//            String s = l.poll();
//            // p.process(s);
//            process(s);
//            if(s==null) {
//                break;
//            }
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
