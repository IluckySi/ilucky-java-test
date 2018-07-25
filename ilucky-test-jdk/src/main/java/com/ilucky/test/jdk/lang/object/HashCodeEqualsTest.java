package com.ilucky.test.jdk.lang.object;

import java.util.HashSet;
import java.util.Set;

public class HashCodeEqualsTest {

    public static void main(String[] args) {
        User u1= new User("IluckySi", 1);
        User u2= new User("IluckySi", 1);
        System.out.println(u1 == u2); // false
        System.out.println(u1.hashCode() == u2.hashCode()); // true
        System.out.println(u1.equals(u2)); // true
        
        Set s = new HashSet();
        s.add(u1);
        s.add(u2);
        System.out.println(s);
        // u1.setName("IluckySi2");
        s.remove(u1);
        System.out.println(s);
        
        System.out.println("===========String===========");
        String s1 = "IluckySi";
        String s2 = "IluckySi";
        System.out.println(s1 == s2); // true
        System.out.println(s1.equals(s2)); // true
        
        String s3 = new String("IluckySi");
        String s4 = new String("IluckySi");
        System.out.println(s3 == s4); // false
        System.out.println(s3.equals(s4)); // true
    }
}
