package com.ilucky.test.asm.util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

/**
 * TraceClassVisitor用途:
 * 由于ClassWriter返回的字节数组不可读，那么如何确认生产或转换后的类符合你的预期呢？
 * 这正是TraceClassVisitor类提供的东西，从名字可以看出，这个类扩展了ClassVisitor类，
 * 通过他，可以获取实际生成内容的一个可读轨迹。
 * @author IluckySi
 *
 */
public class TraceClassVisitorTest {

    public static void main(String[] args) throws UnsupportedEncodingException {  
        ClassWriter cw = new ClassWriter(0);  
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));  
        cv.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE, "test/asm/examples/Comparable", null, "java/lang/Object",   new String[] { "test/asm/examples/Mesurable" });  
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();  
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();  
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();  
        cv.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();  
        cv.visitEnd();  
    }
}
