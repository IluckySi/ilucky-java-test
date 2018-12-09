package com.ilucky.test.jvm.classloader.three;

/**
 * 加载器父子关系
 * 
 * @author IluckySi
 *
 */
public class LoaderTest2 {  
  
    public static void main(String[] args) {  
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader(); 
        System.out.println("SystemClassLoader：" + systemClassLoader);
        System.out.println(System.getProperty("java.class.path"));
       
        ClassLoader extClassLoader = systemClassLoader.getParent();  
        System.out.println("extClassLoader：" + extClassLoader); 
        System.out.println(System.getProperty("java.ext.dirs"));
        
        ClassLoader bootClassLoader = extClassLoader.getParent();  
        System.out.println("bootClassLoader：" + bootClassLoader); 
        System.out.println(System.getProperty("sun.boot.class.path"));
    }  
} 
/**
SystemClassLoader：sun.misc.Launcher$AppClassLoader@73d16e93
D:\core\git2018\ilucky-java-test\ilucky-test-jvm\target\classes
extClassLoader：sun.misc.Launcher$ExtClassLoader@15db9742
E:\software\jdk\jdk8\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
bootClassLoader：null
E:\software\jdk\jdk8\jre\lib\resources.jar;E:\software\jdk\jdk8\jre\lib\rt.jar;E:\software\jdk\jdk8\jre\lib\sunrsasign.jar;E:\software\jdk\jdk8\jre\lib\jsse.jar;E:\software\jdk\jdk8\jre\lib\jce.jar;E:\software\jdk\jdk8\jre\lib\charsets.jar;E:\software\jdk\jdk8\jre\lib\jfr.jar;E:\software\jdk\jdk8\jre\classes
*/