package com.ilucky.test.jvm.classloader;

import java.lang.reflect.Method;

/**
 * 模拟入口
 * 
 * @author IluckySi
 *
 */
public class MyTomcat2 {

    private Object processor;
    
    public MyTomcat2(Object processor) {
        this.processor = processor;
    }
    
    public void test() {
        try {
            Method method = processor.getClass().getMethod("process", new Class[]{Object.class});
            MyTomcatModel2  m = new MyTomcatModel2();
            m.setIp("1.2.3.4");
            m.setPort("8080");
            m.setUri("/test/user/login");
            method.invoke(processor, new Object[]{m});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void test2() {
        try {
            Method method = processor.getClass().getMethod("process2", new Class[]{Object.class});
            MyTomcatModel2  m = new MyTomcatModel2();
            m.setIp("1.2.3.4");
            m.setPort("9090");
            m.setUri("/test/user/logout");
            method.invoke(processor, new Object[]{m});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
