package com.ilucky.test.asm.methodvisitor.three;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author IluckySi
 *
 */
public class MyClassVisitor extends ClassVisitor implements Opcodes {

    private String owner;

    public MyClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(V1_7, access, name, signature, superName, interfaces);
        this.owner = name;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        // 过滤器
        if (name.startsWith("monitor")) {
            System.out.println("Begin modify monitor method......name="+ name);
            mv = new MyMethodVisitor(ASM5, mv, owner, name, desc);
        }
        return mv;
    }

    public void visitEnd() {
        cv.visitEnd();
    }
}
