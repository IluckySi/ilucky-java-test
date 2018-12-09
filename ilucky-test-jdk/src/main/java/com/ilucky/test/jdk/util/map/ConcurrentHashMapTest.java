package com.ilucky.test.jdk.util.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap引起的内存泄露
 * 
 * @author IluckySi
 *
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        // Returns the maximum amount of memory that the Java virtual machine will attempt to use. 
        // If there is no inherent limit then the value java.lang.Long.MAX_VALUE will be returned.
        System.out.println("Max memory is " + Runtime.getRuntime().maxMemory()/(1024*1024) + "M");
        
        // Returns the total amount of memory in the Java virtual machine. 
        // The value returned by this method may vary over time, depending on the host environment. 
        System.out.println("Total memory is " + Runtime.getRuntime().totalMemory()/(1024*1024) + "M");
        
        // Returns the amount of free memory in the Java Virtual Machine. 
        // Calling the gc method may result in increasing the value returned by freeMemory.
        System.out.println("free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
        
        System.out.println("--------------------[TEST]-------------------------");
        
        // 向ConcurrentHashMap中添加数据
        ConcurrentHashMap<String, Object> c = new ConcurrentHashMap<String, Object>(1000);
        // Map<String, Object> c = new HashMap<String, Object>();
        for(int i = 0; i <= 400000; i++) {
            c.put(String.valueOf(i), new User(i, String.valueOf(i), i/2==0, i,  String.valueOf(i)));
        }
        System.out.println("c.put: free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
        
        // 清空ConcurrentHashMap中的数据
        c.clear();
        // c = null;
//        for(int i = 0; i <= 100000; i++) {
//            c.remove(String.valueOf(i));
//        }
//        for(int i = 0; i <= 90000; i++) {
//            c.put(String.valueOf(i), new User(i, String.valueOf(i), i/2==0, i,  String.valueOf(i)));
//        }
//        System.out.println("c.put: free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
//        
        System.out.println("c.clear(): free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
        
        
        
//        // 触发垃圾回收
          System.gc();
          System.out.println("System.gc(): free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
    }
}

class User {
    private int id;
    private String name;
    private boolean age;
    private long phone;
    private String address;
    
    public User(){}
    
    public User(int id, String name, boolean age, long phone, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isAge() {
        return age;
    }
    public long getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(boolean age) {
        this.age = age;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
/**
配置jvm参数: -Xmx128m -Xms128m

Max memory is 123M
Total memory is 123M
free memory is 121M
--------------------------------------------
Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
    at java.util.concurrent.ConcurrentHashMap.putVal(ConcurrentHashMap.java:1043)
    at java.util.concurrent.ConcurrentHashMap.put(ConcurrentHashMap.java:1006)
    at com.ilucky.test.jdk.util.map.ConcurrentHashMapTest.main(ConcurrentHashMapTest.java:33)

*/
