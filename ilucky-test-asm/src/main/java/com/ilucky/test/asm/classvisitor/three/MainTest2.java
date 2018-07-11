package com.ilucky.test.asm.classvisitor.three;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 为存在的类添加字段和方法
 * 
 * 获取的结果如下:
 * ---password=null
 * ---getPassword=null
 * 添加字段时，初始值为什么没有生效呢?
 * 
 *  FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC, fname, fdesc, null, "ilucky-password");
 *  value the field's initial value. This parameter, which may be null if the field does not have an initial value,
 *  must be an Integer, a Float, a Long, a Double or a String (for int, float, long or String fields respectively). 
 *  This parameter is only used for static fields. Its value is ignored for non static fields, 
 *  which must be initialized through bytecode instructions in constructors or methods
 * 
 * @author IluckySi
 *
 */
public class MainTest2  extends ClassLoader  implements Opcodes {

	public static void main(String[] args) {
		new MainTest2().test();
	}
	
	public void test() {
		try {
			ClassReader cr = new ClassReader("com.ilucky.test.asm.classvisitor.three.User");
			ClassWriter cw = new ClassWriter(0);
			AddFieldAndMethodClassVisitor cv = new AddFieldAndMethodClassVisitor(ASM5,cw,"password","Ljava/lang/String;","getPassword","()Ljava/lang/String;");
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
