package com.ilucky.test.yunzhihui.test3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class TestFun2 implements Runnable {

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
    
    public static void main(String[] args) {
        Map<String, String> s = new HashMap<String, String>();
        s.put("test", "a");
        
        // 深度复制一个对象
       Map<String, String> s2 = deepCloneObject(s);
        
        // 复制一个map集合
//        Map<String, String> s2 = new HashMap<String, String>();
//        s2.putAll(s);
        
        new Thread(new TestFun2(s2)).start();
        s.put("test", "b");
    }
    
    private Map<String, String> map;
   
    TestFun2(Map<String, String> map) {
        this.map=map;
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("test"));
    }
}

