package com.ilucky.test.asm.classreader.one;

import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

/**
 * 事件生成器
 * 
 * @author IluckySi
 * 
 * javap是jdk自带的反汇编器
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
			
			// 第二种方法: 从ClassLoader里面获取资源
			InputStream is = ClassReaderTest.class.getClassLoader().getResourceAsStream("java.lang.Runnable".replace(".", "/") + ".class");
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
/**
输出结果:
ClassReader cr = new ClassReader("java.lang.Runnable");
java/lang/Runnable extends java/lang/Object {
 ()V run
}

ClassReader cr = new ClassReader("java.lang.Object");
java/lang/Object extends null {
 ()V <init>
 ()V registerNatives
 ()Ljava/lang/Class; getClass
 ()I hashCode
 (Ljava/lang/Object;)Z equals
 ()Ljava/lang/Object; clone
 ()Ljava/lang/String; toString
 ()V notify
 ()V notifyAll
 (J)V wait
 (JI)V wait
 ()V wait
 ()V finalize
 ()V <clinit>
}
*/
