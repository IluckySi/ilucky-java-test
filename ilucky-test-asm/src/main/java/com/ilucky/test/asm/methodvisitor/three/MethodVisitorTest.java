//package com.ilucky.test.asm.methodvisitor.three;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//import org.objectweb.asm.ClassReader;
//import org.objectweb.asm.ClassWriter;
//import org.objectweb.asm.Opcodes;
//
//import sun.misc.IOUtils;
//
///**
// * 将方法执行时间，返回结果，抛出的异常通过静态方法发出去
// * 
// * @author IluckySi
// *
// */
//public class MethodVisitorTest extends ClassLoader  implements Opcodes {
//
//    public static void main(String[] args) {
//        new MethodVisitorTest().test();
//    }
//    
//    public void test() {
//        try {
//            ClassReader cr = new ClassReader("com.ilucky.test.asm.methodvisitor.two.Monitor");
//            ClassWriter cw = new ClassWriter(0);
//            
//            // 测试
//            AddMethodTimeClassVisitor cv = new AddMethodTimeClassVisitor(ASM5, cw);
//            
//            cr.accept(cv, 0);
//            byte[] b = cw.toByteArray();
//            
//            // 测试: 将其写入class文件
//            new FileOutputStream(new File("E:/Monitor.class")).write(b, 0, b.length);
//            
//            // 进行测试
//            Class<?> c = defineClass("com.ilucky.test.asm.methodvisitor.two.Monitor", b);
//            Object o = c.newInstance();
//            if(c!=null) {
//                Method m = c.getMethod("monitor");
//                if(m != null) {
//                    m.setAccessible(true);
//                    m.invoke(o);
//                }
//                Field f =c.getDeclaredField("timer");
//                if(f != null) {
//                    f.setAccessible(true);
//                    Object r = f.get(o);
//                    System.out.println("---timer="+r);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    /**
//     * 重新定义class
//     * @param name
//     * @param b
//     * @return
//     */
//    public Class<?>  defineClass(String name, byte[] b) {
//        return defineClass(name, b, 0, b.length);
//    }
//}
