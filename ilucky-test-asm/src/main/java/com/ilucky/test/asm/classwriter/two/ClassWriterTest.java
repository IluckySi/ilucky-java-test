package com.ilucky.test.asm.classwriter.two;

import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * 方法注解
 * 
 * @author IluckySi
 */
public class ClassWriterTest {

	public static void main(String[] args) {
		try {
		    ClassWriter cw = new ClassWriter(0);
			ClassPrinterVisitor cpv = new ClassPrinterVisitor(Opcodes.ASM5, cw);
			
			// 从ClassLoader里面获取资源
			InputStream is = ClassWriterTest.class.getClassLoader().getResourceAsStream("com.ilucky.test.asm.classreader.two.Test".replace(".", "/") + ".class");
			ClassReader cr = new ClassReader(is);
			
			// Makes the given visitor visit the Java class of this ClassReader. 
			// 这个类是构造函数中指定的类
			// This class is the one specified in the constructor (see ClassReader).
			cr.accept(cpv, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
