package com.ilucky.test.jvm.classloader.one;

public class MyClassLoader {

    public void load() {
       System.out.println("I am in BootStrap ClassLoader");
       // System.out.println("I am in App ClassLoader");
    }
}
