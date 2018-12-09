package com.ilucky.web.javaagent.service;

public abstract class MyIAbs implements MyInterface {

    public abstract void sayHello(); 
    
    public void sayHello2 () {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
