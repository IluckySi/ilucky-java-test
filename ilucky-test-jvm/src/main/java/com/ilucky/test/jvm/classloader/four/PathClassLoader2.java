package com.ilucky.test.jvm.classloader.four;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * java.lang.LinkageError
 * 
 * @author IluckySi
 *
 */
public class PathClassLoader2 extends ClassLoader {
    
    private String classPath;

    public PathClassLoader2(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        try {
            PathClassLoader2 pcl = new PathClassLoader2("D:/");
            Class c = pcl.findClass("com.ilucky.test.jvm.classloader.four.User");
            System.out.println(c.newInstance());
            Class c2 = pcl.findClass("com.ilucky.test.jvm.classloader.four.User");
            System.out.println(c2.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
com.ilucky.test.jvm.classloader.four.User@7852e922
Exception in thread "main" java.lang.LinkageError: loader (instance of  com/ilucky/test/jvm/classloader/four/PathClassLoader2): attempted  duplicate class definition for name: "com/ilucky/test/jvm/classloader/four/User"
    at java.lang.ClassLoader.defineClass1(Native Method)
    at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
    at java.lang.ClassLoader.defineClass(ClassLoader.java:642)
    at com.ilucky.test.jvm.classloader.four.PathClassLoader2.findClass(PathClassLoader2.java:29)
    at com.ilucky.test.jvm.classloader.four.PathClassLoader2.main(PathClassLoader2.java:55)

*/