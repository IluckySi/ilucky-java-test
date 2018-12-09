package com.ilucky.test.yunzhihui2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InterfaceTest {

    public static void main(String[] args) {
        List<String> classInterfaces = new ArrayList<String>();
        Class clazz = ServiceImpl.class;
        System.out.println(clazz.getName());
       
        for (Class<?> ifc : clazz.getInterfaces()) {
            String ifname = ifc.getName();
            System.out.println(ifname);
            classInterfaces.add(ifname);
        }
        System.out.println(classInterfaces.toString());
        
        Pattern pattern = Pattern.compile(clazz.getName());
        System.out.println(match(pattern, classInterfaces));
    }
    
    private static boolean match(Pattern pattern, List<String> candidates) {
        for (String candidate : candidates) {
            if (pattern.matcher(candidate).matches()) {
                return true;
            }
        }
        return false;
    }
}
