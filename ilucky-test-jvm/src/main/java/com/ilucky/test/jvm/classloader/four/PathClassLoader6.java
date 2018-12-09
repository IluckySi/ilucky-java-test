package com.ilucky.test.jvm.classloader.four;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.ilucky.test.jvm.classloader.MyTomcat2;

/**
 * 加载jar文件
 * 
 * @author IluckySi
 *
 */
public class PathClassLoader6 extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // jar
        String jarPath = "D:/cw/ilucky-test-jvm-classloader-processor-0.0.1-SNAPSHOT.jar";
        File f = new File(jarPath);
        
        // class
        String path = name.replace('.', '/').concat(".class");
        System.out.println("path = " + path);
        
        try {
            JarFile jarFile = new JarFile(f);
            JarEntry entry = jarFile.getJarEntry(path);
            if (entry != null) {
                URL classFileUrl = new URL("jar:file:" + f.getAbsolutePath() + "!/" + path);
                System.out.println("classFileUrl = " + classFileUrl);
                byte[] data = null;
                BufferedInputStream is = null;
                ByteArrayOutputStream baos = null;
                try {
                    is = new BufferedInputStream(classFileUrl.openStream());
                    baos = new ByteArrayOutputStream();
                    int ch = 0;
                    while ((ch = is.read()) != -1) {
                        baos.write(ch);
                    }
                    data = baos.toByteArray();
                } finally {
                    if (is != null)
                        try {
                            is.close();
                        } catch (IOException ignored) {
                        }
                    if (baos != null)
                        try {
                            baos.close();
                        } catch (IOException ignored) {
                        }
                }
                return defineClass(name, data, 0, data.length);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        try {
            PathClassLoader6 pcl = new PathClassLoader6();
            Class c = pcl.loadClass("com.ilucky.test.jvm.classloader.processor.MyProcessor2");
            Object o = c.newInstance();

            MyTomcat2 mTomcat = new MyTomcat2(o);
            mTomcat.test();
            mTomcat.test2();
            
            // 注意区别
            System.out.println(mTomcat.getClass().getClassLoader());
            System.out.println(c.getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
path = com/ilucky/test/jvm/classloader/processor/MyProcessor2.class
classFileUrl = jar:file:D:\cw\ilucky-test-jvm-classloader-processor-0.0.1-SNAPSHOT.jar!/com/ilucky/test/jvm/classloader/processor/MyProcessor2.class
反射......
1.2.3.4
8080
/test/user/login
强转......
1.2.3.4
9090
/test/user/logout
*/