package com.ilucky.test.jvm.classloader.four;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 用自定义加载器加载的类(User), 调用默认加载器加载的类(Car)
 * 
 * @author IluckySi
 *
 */
public class PathClassLoader3 extends ClassLoader {

    private String classPath;

    public PathClassLoader3(String classPath) {
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
            // User类是用自定义类加载器加载的，Car是用默认类加载器加载的。
            PathClassLoader3 pcl = new PathClassLoader3("D:/");
            Class c = pcl.loadClass("com.ilucky.test.jvm.classloader.four.User");

            // 通过反射调用say方法
            Object u = c.newInstance();
            System.out.println(u);

            Method m = u.getClass().getMethod("say");
            m.invoke(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
com.ilucky.test.jvm.classloader.four.User@15db9742
I am driving MBW
 */
