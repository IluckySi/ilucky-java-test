package com.ilucky.test.asm.classvisitor.three;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * 为存在的类添加静态变量和实例方法
 * 
 * 输出结果:
 * ---password=ilucky-password
 * ---getPassword=ilucky-password
 * @author IluckySi
 *
 */
public class MainTest3  extends ClassLoader  implements Opcodes {

	public static void main(String[] args) {
		new MainTest3().test();
	}
	
	public void test() {
		try {
			ClassReader cr = new ClassReader("com.ilucky.test.asm.classvisitor.three.User");
			ClassWriter cw = new ClassWriter(0);
			AddFieldAndMethodClassVisitor2 cv = new AddFieldAndMethodClassVisitor2(ASM5,cw,"password","Ljava/lang/String;","getPassword","()Ljava/lang/String;");
			cr.accept(cv, 0);
			byte[] b = cw.toByteArray();
			
			// 测试变量和方法是否添加
			Class<?> c = defineClass("com.ilucky.test.asm.classvisitor.three.User", b);
			if(c!=null) {
				Field f =c.getDeclaredField("password");
				if(f != null) {
					f.setAccessible(true);
					Object o = c.newInstance();
					Object r = f.get(o);
					System.out.println("---password="+r);
				}
				Method m =c.getDeclaredMethod("getPassword");
                if(m != null) {
                    m.setAccessible(true);
                    Object o = c.newInstance();
                    Object r = m.invoke(o);
                    System.out.println("---getPassword="+r);
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
