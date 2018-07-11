package com.ilucky.test.asm.classreader.one;

import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

/**
 * 事件生成器
 * 
 * @author IluckySi
 * 
E:\work\yunzhihui>javap "java/lang/Runnable"
Compiled from "Runnable.java"
public interface java.lang.Runnable {
  public abstract void run();
}
 */
public class ClassReaderTest {

	public static void main(String[] args) {
		try {
			ClassPrinterVisitor cpv = new ClassPrinterVisitor(Opcodes.ASM5);
			// 第一种方法:
			// ClassReader cr = new ClassReader("java.lang.Runnable");
			ClassReader cr = new ClassReader("java.lang.Object");
			
			// 第二种方法:
			// InputStream is = MainTest.class.getClassLoader().getResourceAsStream("java.lang.Runnable".replace(".", "/") + ".class");
			// ClassReader cr = new ClassReader(is);
			
			// Makes the given visitor visit the Java class of this ClassReader. 
			// This class is the one specified in the constructor (see ClassReader).
			cr.accept(cpv, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/**
输出结果:
java/lang/Runnable extends java/lang/Object {
 ()V run
}
*/
