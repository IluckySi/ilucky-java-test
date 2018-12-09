package com.ilucky.test.asm.methodvisitor.two;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * visitCode和visitMaxs方法可用于检测该方法的字节码在一个事件序列中的开始与结束，和类的情况一样，
 * visitEnd方法也必须在最后调用，用于检测一个方法在一个序列事件中结束
 * 
 * 为一个方法计算栈映射帧并不是非常容易: 必须计算所有帧，找出与跳转目标相对应的帧，最后压缩剩余帧。
 * 与此类似，为一个方法计算局部变量与操作数栈的大小要容易些，但依然算不上非常容易，所以可以使用asm提供的自动计算
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
            ClassReader cr = new ClassReader("com.ilucky.test.asm.methodvisitor.two.Monitor");
            ClassWriter cw = new ClassWriter(0);
            
            // 测试
            AddMethodTimeClassVisitor cv = new AddMethodTimeClassVisitor(ASM5, cw);
            
            cr.accept(cv, 0);
            byte[] b = cw.toByteArray();
            
            // 测试: 将其写入class文件
            new FileOutputStream(new File("E:/Monitor.class")).write(b, 0, b.length);
            
            // 进行测试
            Class<?> c = defineClass("com.ilucky.test.asm.methodvisitor.two.Monitor", b);
            Object o = c.newInstance();
            if(c!=null) {
                Method m = c.getMethod("monitor");
                if(m != null) {
                    m.setAccessible(true);
                    m.invoke(o);
                }
                Field f =c.getDeclaredField("timer");
                if(f != null) {
                    f.setAccessible(true);
                    Object r = f.get(o);
                    System.out.println("---timer="+r);
                }
            }
        } catch (Exception e) {
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
/**
运行结果:
=========MethodVisitor============
=========MethodVisitor============
---begin
---end
---timer=2000


生成的class文件如下:
package com.ilucky.test.asm.methodvisitor.two;

import java.io.PrintStream;

public class Monitor
{
  public static long timer;

  public void monitor()
  {
    timer -= System.currentTimeMillis(); 
    System.out.println("---begin");
    int i = 1;
    int j = 2;
    int r = i + j;
    try {
      Thread.sleep(2000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("---end");
    timer += System.currentTimeMillis();
  }
}
*/
