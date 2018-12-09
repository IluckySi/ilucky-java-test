package com.ilucky.test.asm.methodvisitor.four;

public class Monitor {

    
    public void monitor5() throws Exception {
        throw new Exception("xxx");
    }
    
    // 拦截实例方法参数
    public User monitor5(User user) {
        System.out.println("---begin");
        user.addFamily("I", "IluckySi");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---end");
        return user;
    }
    
    // 拦截静态方法参数
    public static User monitor6(User user) {
        System.out.println("---begin");
        user.addFamily("I", "IluckySi");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---end");
        return user;
    }
}
