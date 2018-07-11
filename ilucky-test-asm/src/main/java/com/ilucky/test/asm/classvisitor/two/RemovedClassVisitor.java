package com.ilucky.test.asm.classvisitor.two;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * 移除类成员
 * 可以通过覆盖访问内部类和外部类的方法，移除有关外部类和内部类的信息。
 * 但是对于字段和方法是无效的，因为visitField和visitMethod方法必须返回一个结果，
 * 要移除字段或方法，需要不得转发方法调用，并向调用者返回null
 * @author IluckySi
 *
 */
public class RemovedClassVisitor extends ClassVisitor {

	public RemovedClassVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}

	 public void visitOuterClass(String owner, String name, String desc) {
     }
	 
	 public void visitInnerClass(String name, String outerName, String innerName, int access) {
	 }
	 
	/**
	 * 清除所有类的getUsername方法
	 */
	 public MethodVisitor visitMethod(int access, String name, String desc,  String signature, String[] exceptions) {
	     // if(name.equals(mName) && desc.equals(mDesc)) {
	     if(name.equals("getUsername")) {
		     // 不要委托至下一个访问器，即移除该方法
			 return null;
		 }
		 return cv.visitMethod(access, name, desc, signature, exceptions);
	 }
}
