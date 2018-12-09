package com.ilucky.test.jvm.classloader.two;

/**
 * 当前线程的ClassLoader和当前类的ClassLoader
 * 
 * @author IluckySi
 *
 */
public class CurrentClassLoaderTest {

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        ClassLoader tcl = t.getContextClassLoader();
        System.out.println(tcl);
        
        ClassLoader ccl = CurrentClassLoaderTest.class.getClassLoader();
        System.out.println(ccl);
        
        System.out.println(tcl == ccl);
    }

}
/**
sun.misc.Launcher$AppClassLoader@73d16e93
sun.misc.Launcher$AppClassLoader@73d16e93
true
*/