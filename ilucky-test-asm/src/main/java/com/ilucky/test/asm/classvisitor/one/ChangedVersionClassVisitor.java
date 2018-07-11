package com.ilucky.test.asm.classvisitor.one;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ChangedVersionClassVisitor extends ClassVisitor implements Opcodes {

    public ChangedVersionClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        if (cv != null) {
            cv.visit(V1_6, access, name, signature, superName, interfaces);
        }
    }
}
