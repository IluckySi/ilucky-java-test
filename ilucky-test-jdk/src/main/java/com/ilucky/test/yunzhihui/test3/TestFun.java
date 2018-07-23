package com.ilucky.test.yunzhihui.test3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestFun implements Runnable {

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
        User u = new User();
        u.setName("a");
        
        // 深度复制一个对象
        User u2 = deepCloneObject(u);
        new Thread(new TestFun(u2)).start();
        u.setName("b");
    }
    
    private User u;
    TestFun(User u) {
        this.u=u;
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(u.getName());
    }
}

class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
