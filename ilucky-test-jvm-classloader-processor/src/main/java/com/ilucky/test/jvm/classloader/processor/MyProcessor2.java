package com.ilucky.test.jvm.classloader.processor;

import java.lang.reflect.Method;

import com.ilucky.test.jvm.classloader.MyTomcatModel2;

/**
 * 模拟JavaAgent数据处理器
 * 
 * @author IluckySi
 *
 */
public class MyProcessor2 {

    public void process(Object o) {
        System.out.println("反射......");
        try {
            Method getIp = o.getClass().getMethod("getIp");
            System.out.println(getIp.invoke(o));
            Method getPort = o.getClass().getMethod("getPort");
            System.out.println(getPort.invoke(o));
            Method getUri = o.getClass().getMethod("getUri");
            System.out.println(getUri.invoke(o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void process2(Object o) {
        System.out.println("强转......");
        MyTomcatModel2 m = (MyTomcatModel2)o;
        System.out.println(m.getIp());
        System.out.println(m.getPort());
        System.out.println(m.getUri());
    }
}
