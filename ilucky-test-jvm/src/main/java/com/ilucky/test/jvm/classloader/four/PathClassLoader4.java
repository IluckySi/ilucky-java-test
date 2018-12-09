package com.ilucky.test.jvm.classloader.four;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ilucky.test.jvm.classloader.four.agent.MyTomcat;

/**
 * 模拟JavaAgent环境: 
 * Tomcat入口(传输参数HttpServletRequest)---》Processor(处理HttpServletRequest参数)
 * MyTomcat(传输参数MyTomcatModel)---》MyProcessor(处理MyTomcatModel参数)
 * 
 * @author IluckySi
 *
 */
public class PathClassLoader4 extends ClassLoader {
    
    private String classPath;

    public PathClassLoader4(String classPath) {
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
            // TODO: 这样测试有问题，因为如果将MyProcessor类引入到class.path
            // 是不会被自定类加载器加载的。但是不引入进来，又编译不过去。
            // 所以， 需要将每部分独立成项目进行测试
//            PathClassLoader4 pcl = new PathClassLoader4("D:/cw/");
//            Class c = pcl.loadClass("com.cloudwise.javaagent.MyProcessor");
           
            MyTomcat mTomcat = new MyTomcat();
//            mTomcat.test();
//            mTomcat.test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
