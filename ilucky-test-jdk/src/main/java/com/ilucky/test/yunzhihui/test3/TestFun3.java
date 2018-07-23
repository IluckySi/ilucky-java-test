package com.ilucky.test.yunzhihui.test3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 效率问题比较
 * putAll方法不是深度复制，所以需要解耦
 * @author IluckySi
 *
 */
public class TestFun3 implements Runnable {

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> l = new ArrayList<String>();
        for(int i=0;i<=10;i++) {
            l.add("a"+i);
        }
        for(int i=0;i<=10;i++) {
            map.put("key"+i, l);
        }
        
        long st= System.currentTimeMillis();
        //for(int i=0;i<=100000;i++) {
        
           // 有问题，List没有进行深度复制
           // Map<String, List<String>> s2 = new HashMap<String, List<String>>();
           // s2.putAll(map);
           
           // 需要用如下元素进行深度复制
           Map<String, List<String>> s2 = deepCloneObject(map);
        // }
        long et=System.currentTimeMillis();
        // System.out.println(et-st);
        
        l.clear();
        for(int i=0;i<=10;i++) {
            l.add("bcd"+i);
        }
        
        new Thread(new TestFun3(s2)).start();
        
        // 更改里面的值
        for(int i=0;i<10;i++) {
            System.out.println(l.get(i));
        }
    }
    
    private Map<String, List<String>> map;
    
    TestFun3 (Map<String, List<String>> map) {
        this.map=map;
    }

    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("test"));
    }
    
    @SuppressWarnings("unchecked")  
    public static <T> T deepCloneObject(T obj) {  
        try {  
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
            ObjectOutputStream out = new ObjectOutputStream(byteOut);  
            out.writeObject(obj);  
            
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
            ObjectInputStream in =new ObjectInputStream(byteIn);  

            return (T)in.readObject();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
}
