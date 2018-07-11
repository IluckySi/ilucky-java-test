package com.ilucky.test.asm.classwriter.one;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 生成类
 * 注意: 除了long和double类型(变量表和操作数栈)占用两个槽位，其他所有基本数据类型都占用一个槽位
 * 注意: 变量表和操作数栈的差异性
 * @author IluckySi
 */
public class CLassWriterTest extends ClassLoader implements Opcodes {

	public static void main(String[] args) {
		CLassWriterTest cwt = new CLassWriterTest();
		cwt.generateClass();
	}
	
	public void generateClass() {
		ClassWriter cw =new ClassWriter(ClassWriter.COMPUTE_FRAMES|ClassWriter.COMPUTE_MAXS);
		
		// Visits the header of the class.
		cw.visit(V1_7, ACC_PUBLIC, "com/ilucky/test/asm/classwriter/one/MyTest", null, "java/lang/Object", null);
		
		// Visits a field of the class.
		// 添加public static final int类型字段test, 初始值是20, 注意:只有静态字段才可以初始化值
		cw.visitField(ACC_PUBLIC+ACC_STATIC+ACC_FINAL, "test", "I",  null, new Integer(20));
		
		// Visits a method of the class. This method must return a new MethodVisitor instance (or null) each time it is called, i.e., 
		// it should not return a previously returned visitor.
		// 添加构造方法
	    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);  
	    // Visits a local variable instruction. A local variable instruction is an instruction that loads or stores the value of a local variable.
	    mv.visitVarInsn(ALOAD, 0); 
	    // Visits a method instruction. A method instruction is an instruction that invokes a method.
	    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);  
	    // Visits a zero operand instruction.
	    mv.visitInsn(RETURN);  
	    // Visits the maximum stack size and the maximum number of local variables of the method.
	    mv.visitMaxs(1, 1); 
	    // Visits the end of the method. This method, which is the last one to be called,
	    // is used to inform the visitor that all the annotations and attributes of the method have been visited.
	    mv.visitEnd(); 
		
	    // Visits a method of the class. This method must return a new MethodVisitor instance (or null) each time it is called, i.e., 
		// it should not return a previously returned visitor.
	    // 添加实例方法: 返回两个数相加的和
	    mv = cw.visitMethod(ACC_PUBLIC, "getTest", "(II)I", null, null);  
	    // Visits a local variable instruction. A local variable instruction is an instruction that loads or stores the value of a local variable.
	    mv.visitVarInsn(ILOAD, 1);  
	    // Visits a local variable instruction. A local variable instruction is an instruction that loads or stores the value of a local variable.
	    mv.visitVarInsn(ILOAD, 2);  
	    // Visits a zero operand instruction.
	    mv.visitInsn(IADD);   
	    mv.visitInsn(IRETURN); 
	    // Visits the maximum stack size and the maximum number of local variables of the method.
	    mv.visitMaxs(2, 3);  
	    // Visits the end of the method. This method, which is the last one to be called,
	    // is used to inform the visitor that all the annotations and attributes of the method have been visited.
	    mv.visitEnd();  
	    
	    // Visits the end of the class. This method, which is the last one to be called, 
	    // is used to inform the visitor that all the fields and methods of the class have been visited.
	    cw.visitEnd();
	    
	    // Returns the bytecode of the class that was build with this class writer.
	    // 生成新的字节数组
		byte[] b = cw.toByteArray();
		
		// 测试生成的类
		try {
			Class<?> c = defineClass("com.ilucky.test.asm.classwriter.one.MyTest", b);
			// 通过反射获取变量value和调用方法
			if(c!=null) {
				Field f =c.getDeclaredField("test");
				if(f != null) {
					f.setAccessible(true);
					// 获取实例
					Object o = c.newInstance();
					Object test = f.get(o);
					System.out.println("---test="+test);
				}
				Method m =c.getDeclaredMethod("getTest", new Class[]{int.class, int.class});
				if(m != null) {
					m.setAccessible(true);
					// 获取实例
					Object o = c.newInstance();
					Object r = m.invoke(o, new Object[]{10, 20});
					System.out.println("---r="+r);
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
