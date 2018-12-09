package com.ilucky.test.asm.classwriter.two;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

/**
 * @author IluckySi
 *
 */
public class ClassPrinterVisitor extends ClassVisitor  implements Opcodes {

	public ClassPrinterVisitor(int api,  ClassWriter cw) {
		super(api, cw);
	}

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		System.out.println(name+" extends " + superName + " {");
	}
	
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
       System.out.println(" (Annotation:" + desc + ":" + visible +")");
       return null;
    }
	   
   public AnnotationVisitor visitTypeAnnotation(int typeRef,
           TypePath typePath, String desc, boolean visible) {
       System.out.println(" (Type Annotation:" + typePath + ":" + desc + ":" + visible +")");
       return null;
   }
   
	public FieldVisitor visitField(int access, String name, String desc, String singnature, Object value) {
		System.out.println(" " + desc + " " + name);
		return null;
	}
	
   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + desc + " " + name);
        // System.out.println(" " + desc + " " + singature + " " + name);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        // 排除接口和非接口的构造方法
        if (!name.equals("<init>")) {
            mv = new MethodPrinterVisitor(ASM5);
        }
        return mv;
    }
	   
	public void visitEnd() {
		System.out.println("}");
	}
}
