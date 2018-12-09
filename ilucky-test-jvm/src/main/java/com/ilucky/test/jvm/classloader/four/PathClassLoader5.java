package com.ilucky.test.jvm.classloader.four;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ilucky.test.jvm.classloader.MyTomcat2;

/**
 * 模拟JavaAgent环境: 
 * Tomcat入口(传输参数HttpServletRequest)---》Processor(处理HttpServletRequest参数)
 * MyTomcat(传输参数MyTomcatModel)---》MyProcessor(处理MyTomcatModel参数)
 * 
 * @author IluckySi
 *
 */
public class PathClassLoader5 extends ClassLoader {
    
    private String classPath;

    public PathClassLoader5(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            System.out.println("Find class = " + name +", lenght = "+ classData.length);
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
            PathClassLoader5 pcl = new PathClassLoader5("D:/cw");
            Class c = pcl.loadClass("com.ilucky.test.jvm.classloader.processor.MyProcessor2");
            Object o = c.newInstance();
            
            MyTomcat2 mTomcat = new MyTomcat2(o);
            mTomcat.test();
            mTomcat.test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
