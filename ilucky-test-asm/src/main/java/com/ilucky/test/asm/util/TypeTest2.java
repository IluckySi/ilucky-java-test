package com.ilucky.test.asm.util;

import org.objectweb.asm.Type;

public class TypeTest2 {

    public static void main(String[] args) {
       
        String s1 = "(III)V";
        String s2 = "(IIJ)V";
        String s3 = "(JJJ)V";
        test(s1);
        test(s2);
        test(s3);
    }

    private static void test(String desc) {
        Type[] types = Type.getArgumentTypes(desc);
        System.out.println(types.length);
        int size = 0;
        for(Type t : types) {
            size += t.getSize();
        }
        System.out.println("==="+size);
    }
}
/**
asm进行load的时候,后面的index是位置,在真正进行计算的时候,内部已经考虑到了size
3
===3
3
===4
3
===6
*/