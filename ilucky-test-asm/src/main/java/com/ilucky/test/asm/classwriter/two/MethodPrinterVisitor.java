package com.ilucky.test.asm.classwriter.two;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodPrinterVisitor extends MethodVisitor implements Opcodes {

    public MethodPrinterVisitor(int api) {
        super(api);
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
       System.out.println("Annotation:" + desc + ":" + visible);
        return null;
    }
}
