package com.ilucky.test.asm.classvisitor.one;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * 优化
 * @author IluckySi
 *
 */
public class ClassVisitorTest  implements Opcodes {

	public static void main(String[] args) {
//	    test();
//		test1();
		System.out.println("------------");
//		test2();
		test3();
	}

	/**
	 * 如下程序本身没有任何意义,只是简单的复制一个字节数组
	 * ClassReader将产生的事件交给事件使用器ClassWriter
	 * 结果是:事件使用器ClassWriter重新构建了事件生成器ClassReader分析的类
	 */
	private static void test() {
        try {
            long start = System.currentTimeMillis();
            for(int i=0; i<100000; i++) {
                ClassReader cr = new ClassReader("java.lang.Object");
                ClassWriter cw = new ClassWriter(0);
                cr.accept(cw, 0);
                byte[] b2 = cw.toByteArray();
            }
            System.out.println("test--->"+(System.currentTimeMillis()-start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 如下程序本身没有任何意义,只是简单的复制一个字节数组
	 * 注意：引入ClassVisitor后,事件产生器ClassReader产生事件后,将事件交给事件筛选器ClassVisitor，
	 * ClassVisitor又将事件转发给事件使用器ClassWriter，
	 * 但是由于ClassVisitor事件筛选器没有筛选任何东西，所以结果和test没有任何变化
	 */
	private static void test1() {
		try {
			long start = System.currentTimeMillis();
			for(int i=0; i<100000; i++) {
				ClassReader cr = new ClassReader("java.lang.Object");
				ClassWriter cw = new ClassWriter(0);
				ClassVisitor cv = new ClassVisitor(ASM5, cw){};
				cr.accept(cv, 0);
				byte[] b2 = cw.toByteArray();
			}
			System.out.println("test1--->"+(System.currentTimeMillis()-start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用ChangedVersionClassVisitor修改类的版本,这个类仅重写了ClassVisitor类的一个方法。
	 * 尽管只是修改类的版本，即修改原类的四个字节，但是使用如下的代码，整个类均被分析，字节从头进行了构建，所以，这种做法的效率非常低。
	 * 如果将被分析的字节码中不被转换的部分直接复制到b2中，不对其分析，也不产生相应的事件，其效率就会高很多。参考test3
	 */
	private static void test2() {
		try {
			long start = System.currentTimeMillis();
			for(int i=0; i<100000; i++) {
				ClassReader cr = new ClassReader("java.lang.Object");
				ClassWriter cw = new ClassWriter(0);
				ChangedVersionClassVisitor cv = new ChangedVersionClassVisitor(ASM5, cw){};
				cr.accept(cv, 0);
				byte[] b2 = cw.toByteArray();
			}
			System.out.println("test2--->"+(System.currentTimeMillis()-start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 如果ClassReader和ClassWriter拥有对方的引用，则他们会进行如上的优化
	 * 重点: 为什么不会产生相应的事件?
	 * 在ClassReader组件的accept方法参数中传送了ClassVisitor，如果ClassReader检测到这个ClassVisitor返回
	 * 的MethodVisitor来自一个ClassWriter，意味着这个方法的内容将不会被转换，即不会产生相应的事件，只是复制
	 * ClassWriter中表示这个方法的字节数组。
	 */
	private static void test3() {
        try {
            long start = System.currentTimeMillis();
            for(int i=0; i<100000; i++) {
                ClassReader cr = new ClassReader("java.lang.Object");
                ClassWriter cw = new ClassWriter(cr, 0);
                ChangedVersionClassVisitor cv = new ChangedVersionClassVisitor(ASM5, cw){};
                cr.accept(cv, 0);
                byte[] b2 = cw.toByteArray();
            }
            System.out.println("test3--->"+(System.currentTimeMillis()-start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
