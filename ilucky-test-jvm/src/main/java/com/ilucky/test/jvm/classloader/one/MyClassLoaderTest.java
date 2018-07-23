package com.ilucky.test.jvm.classloader.one;

/**
 * 
 * @author IluckySi
 */
public class MyClassLoaderTest {

    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader();
        loader.load();
    }
}
