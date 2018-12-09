package com.ilucky.test.jvm.classloader.four;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器，加载指定路径下的类文件。
 * 注意: User类不能存在于PathClassLoader所在项目中（也不能再src/main/resources目录下），
 * 否则debug的时候是不会走findClass方法的。
 * 
 * @author IluckySi
 *
 */
public class PathClassLoader extends ClassLoader {
    
    private String classPath;

    public PathClassLoader(String classPath) {
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

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader pcl = new PathClassLoader("D:/");
        Class c = pcl.loadClass("com.ilucky.test.jvm.classloader.four.User");
        System.out.println(c.newInstance());
    }
}
/**
1. 
加载代码如下:
ClassLoader pcl = new PathClassLoader("D:/");
Class c = pcl.loadClass("com.test.User");

报错如下:
Exception in thread "main" java.lang.NoClassDefFoundError: com/test/User (wrong name: com/ilucky/test/jvm/classloader/four/User)
    at java.lang.ClassLoader.defineClass1(Native Method)
    at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
    at java.lang.ClassLoader.defineClass(ClassLoader.java:642)
    at com.ilucky.test.jvm.classloader.four.PathClassLoader.findClass(PathClassLoader.java:30)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
    at com.ilucky.test.jvm.classloader.four.PathClassLoader.main(PathClassLoader.java:53)

2.
加载代码如下:
ClassLoader pcl = new PathClassLoader("D:/");
Class c = pcl.loadClass("com.ilucky.test.jvm.classloader.four.User");

输出结果:
com.ilucky.test.jvm.classloader.four.User@15db9742

*/