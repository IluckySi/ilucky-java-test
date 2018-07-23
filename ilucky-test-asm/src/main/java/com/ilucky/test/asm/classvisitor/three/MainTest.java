package com.ilucky.test.asm.classvisitor.three;

import java.io.PrintWriter;
import java.lang.reflect.Field;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

public class MainTest  extends ClassLoader  implements Opcodes {

	public static void main(String[] args) {
		new MainTest().test();
	}
	
	public void test() {
		try {
			ClassReader cr = new ClassReader("com.ilucky.test.asm.classvisitor.three.User");
			ClassWriter cw = new ClassWriter(0);
			
			// 测试
			// ClassVisitor cv = new ClassVisitor(ASM5, cw){}; 
			AddFieldClassVisitor cv = new AddFieldClassVisitor(ASM5, cw, "password", "Ljava/lang/String;");
			
			cr.accept(cv, 0);
			byte[] b = cw.toByteArray();
			
			// 测试变量是否添加
			Class<?> c = defineClass("com.ilucky.test.asm.classvisitor.three.User", b);
			if(c!=null) {
				Field f =c.getDeclaredField("password");
				if(f != null) {
					f.setAccessible(true);
					Object o = c.newInstance();
					Object r = f.get(o);
					System.out.println("---password="+r);
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
