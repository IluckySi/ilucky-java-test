//package com.inner;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
//
//public class InnerClassTest {
//
//    public static void main(String[] args) {
//        Student s = new Student();
//        s.setUser("Student");
//        s.setPassword("123");
//        Student.People p = s.new People();
//        p.setName("IluckySi");
//        
//        System.out.println(s.getUser()+"-"+s.getPassword()+"-"+p.getName()+"-"+p);
//        
//        // 
//        Object o = s;
//        Class[] ca = o.getClass().getDeclaredClasses();
//        for(Class c : ca) {
//            System.out.println(c);
//            if(c.toString().contains("People")) {
//                Method getNameMethod;
//                try {
//                    getNameMethod = c.getDeclaredMethod("getName", null);
//                    System.out.println(getNameMethod);
//                   
//                    // ------------------------------------------------
//                    Constructor con2 = c.getDeclaredConstructor(o.getClass());
//                    con2.setAccessible(true);
//                    Object obj2 = con2.newInstance(o);
//                    System.out.println(obj2);
//                    
//                    Object getName = getNameMethod.invoke(obj2, null);
//                    System.out.println(getName);
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//}
