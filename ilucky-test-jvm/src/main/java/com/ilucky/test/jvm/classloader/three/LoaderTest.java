package com.ilucky.test.jvm.classloader.three;

/**
 * 加载器父子关系
 * 
 * @author IluckySi
 *
 */
public class LoaderTest {  
  
    public static void main(String[] args) {  
        ClassLoader currentLoader = LoaderTest.class.getClassLoader();  
        System.out.println("当前类的加载器名称：" + currentLoader);  
       
        ClassLoader fatherLoader = currentLoader.getParent();  
        System.out.println("当前类的加载器的父加载器：" + fatherLoader); 
        
        ClassLoader grandFatherLoader = fatherLoader.getParent();  
        System.out.println("当前类的加载器的父加载器的父加载器：" + grandFatherLoader); 
    }  
} 
/**
当前类的加载器名称：sun.misc.Launcher$AppClassLoader@73d16e93
当前类的加载器的父加载器：sun.misc.Launcher$ExtClassLoader@15db9742
当前类的加载器的父加载器的父加载器：null
*/