package com.ilucky.test.asm.methodvisitor.three;

public class Submitter {

    public static void start(String className, String methodName, long startTime) {
        System.out.println(className+"."+methodName+"---"+"startTime="+startTime);
    }
    
    public static void end(String className, String methodName, long endTime, Object returnResult, Object exception) {
        System.out.println(className+"."+methodName+"---"+"endTime="+endTime);
        System.out.println(className+"."+methodName+"---"+"returnResult="+returnResult);
        System.out.println(className+"."+methodName+"---"+"exception="+exception);
    }
}
