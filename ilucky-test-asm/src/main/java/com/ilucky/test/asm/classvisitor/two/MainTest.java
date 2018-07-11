package com.ilucky.test.asm.classvisitor.two;

import java.lang.reflect.Method;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * 移除类成员
 * @author IluckySi
 *
 */
public class MainTest extends ClassLoader  implements Opcodes {

	public static void main(String[] args) {
		new MainTest().test();
	}
	
	public void test() {
		try {
			ClassReader cr = new ClassReader("com.ilucky.test.asm.classvisitor.two.User");
			ClassWriter cw = new ClassWriter(0);
			
			// 测试
			// ClassVisitor cv = new ClassVisitor(ASM5, cw){};
			RemovedClassVisitor cv = new RemovedClassVisitor(ASM5, cw);
			
			cr.accept(cv, 0);
			byte[] b = cw.toByteArray();
			
			// 测试方法是否被去除
			Class<?> c = defineClass("com.ilucky.test.asm.classvisitor.two.User", b);
			if(c!=null) {
				Method m =c.getDeclaredMethod("getUsername");
				if(m != null) {
					m.setAccessible(true);
					Object o = c.newInstance();
					Object r = m.invoke(o);
					System.out.println("---usernmae="+r);
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
