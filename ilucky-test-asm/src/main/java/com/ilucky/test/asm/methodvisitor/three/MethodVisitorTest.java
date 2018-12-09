package com.ilucky.test.asm.methodvisitor.three;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;


/**
 * 将方法执行时间，返回结果，抛出的异常通过静态方法发出去
 * 
 * 拦截定义表包含: 类(包含包名)+方法(参数是可选项)
 * 1. 首先对类进行过滤,可以结合-javaagent(transform)
 * 2. 然后按照方法进行过滤(除了visitMethod, 还可以使用注解的方式)
 * 3. 在类的方法级别, 修改字节码
 * 
 * @author IluckySi
 *
 */
public class MethodVisitorTest extends ClassLoader  implements Opcodes {

    public static void main(String[] args) {
        new MethodVisitorTest().test();
    }
    
    public void test() {
        try {
            ClassReader cr = new ClassReader("com.ilucky.test.asm.methodvisitor.three.Monitor");
            ClassWriter cw = new ClassWriter(0);
            
            // 测试
            MyClassVisitor cv = new MyClassVisitor(ASM5, cw);
            
            cr.accept(cv, 0);
            byte[] b = cw.toByteArray();
            
            // 测试: 将其写入class文件
            new FileOutputStream(new File("E:/Monitor3.class")).write(b, 0, b.length);
            
            // 进行测试
            Class<?> c = defineClass("com.ilucky.test.asm.methodvisitor.three.Monitor", b);
//            Method[] ms = c.getDeclaredMethods();
//            for(Method m : ms) {
//                System.out.println(m.getName());
//            }
            Object o = c.newInstance();
            if(c!=null) {
                System.out.println("=====================monitor==========================");
                Method m = c.getDeclaredMethod("monitor");
                if(m != null) {
                    m.setAccessible(true);
                    m.invoke(o);
                }
                sleep();
                System.out.println("=====================monitor2==========================");
                Method m2 = c.getDeclaredMethod("monitor2"); 
                if(m2 != null) {
                    m2.setAccessible(true);
                    m2.invoke(o);
                }
                sleep();
                System.out.println("=====================monitor3==========================");
                Method m3 = c.getDeclaredMethod("monitor3", new Class[]{String.class, long.class, long.class, int.class, long.class});
                if(m3 != null) {
                    m3.setAccessible(true);
                    m3.invoke(o, new Object[]{"IluckySi", 14l, 10l, 2, 3l});
                }
                sleep();
                System.out.println("=====================monitor4==========================");
                Method m4 = c.getDeclaredMethod("monitor4");
                if(m4 != null) {
                    m4.setAccessible(true);
                    m4.invoke(o);
                }
                sleep();
                System.out.println("=====================monitor5==========================");
                Method m5 = c.getDeclaredMethod("monitor5");
                if(m5 != null) {
                    m5.setAccessible(true);
                    m5.invoke(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void sleep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新定义class
     * @param name
     * @param b
     * @return
     */
    public Class<?>  defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
