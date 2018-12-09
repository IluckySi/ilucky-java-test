package com.ilucky.test.asm.methodvisitor.three;

public class Submitter {

    volatile public static boolean agent = true;
    
    // 也可以从ThreadLocal中获取, 进而采样
    public static boolean isAgent() {
        return agent;
    }
    
    public static void start(String className, String methodName) {
        long startTime = System.currentTimeMillis();
        System.out.println(className+"."+methodName+"---"+"startTime="+startTime);
    }
    
    public static void end(String className, String methodName, Object returnResult) {
        long endTime = System.currentTimeMillis();
        System.out.println(className+"."+methodName+"---"+"endTime="+endTime);
        System.out.println(className+"."+methodName+"---"+"returnResult="+returnResult);
    }
    
    public static void exception(String className, String methodName, Object exception) {
        long endTime = System.currentTimeMillis();
        System.out.println(className+"."+methodName+"---"+"endTime="+endTime);
        System.out.println(className+"."+methodName+"---"+"exception="+exception+"   $$$");
    }
}
