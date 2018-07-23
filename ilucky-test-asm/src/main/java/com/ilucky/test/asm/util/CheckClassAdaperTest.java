package com.ilucky.test.asm.util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

/**
 * CheckClassAdapter用途:
 * 核实方法的调用是否恰当和参数是否有效
 * @author IluckySi
 *
 */
public class CheckClassAdaperTest {
    
    public static void main(String[] args) throws UnsupportedEncodingException {  
        ClassWriter cw = new ClassWriter(0);  
        // 前后顺序并不影响，并且TraceClassVisitor和CheckClassAdapter一样，可以在一个生成连或转换链的任意位置使用
        CheckClassAdapter ccv = new CheckClassAdapter(cw);
        TraceClassVisitor cv = new TraceClassVisitor(ccv, new PrintWriter(System.out));  
        
        cv.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE, "test/asm/examples/Comparable", null, "java/lang/Object",   new String[] { "test/asm/examples/Mesurable" });  
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();  
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();  
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();  
        cv.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();  
        cv.visitEnd();  
}
}
