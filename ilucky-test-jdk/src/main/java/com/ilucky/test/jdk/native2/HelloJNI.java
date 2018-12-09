package com.ilucky.test.jdk.native2;

public class HelloJNI {
    
    native void printHello();
    native void printString(String str);

    static {
        System.loadLibrary("hello");
    }

    public static void main(String args[]) {
        HelloJNI myJNI = new HelloJNI();

        myJNI.printHello();
        myJNI.printString("Hello World from printString fun");
    }
}
