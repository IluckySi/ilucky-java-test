package com.ilucky.web.javaagent.service;

public class MyInterfaceImpl extends MyIAbs {

    public void sayHello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}
