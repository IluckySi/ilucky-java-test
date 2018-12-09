package com.ilucky.test.asm.methodvisitor.four;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * @author IluckySi
 *
 */
public class MyMethodVisitor extends MethodVisitor implements Opcodes {

    private String owner;
    private String name;
    private Type[] argTypes;
    private Type returnType;
    
    private String SUMBITTER = "com/ilucky/test/asm/methodvisitor/three/Submitter";
    private String START = "start";
    private String END = "end";
    
    public MyMethodVisitor(int api, MethodVisitor mv, String owner, String name, String desc) {
        super(api, mv);
        this.owner = owner;
        this.name = name;
        this.argTypes = Type.getArgumentTypes(desc);
        this.returnType = Type.getReturnType(desc);
    }

    public void visitCode() {
        mv.visitLdcInsn(owner);
        mv.visitLdcInsn(name);
        mv.visitMethodInsn(INVOKESTATIC, SUMBITTER, START, "(Ljava/lang/String;Ljava/lang/String;)V", false);
    }

    public void visitInsn(int opcode) {
        // 注意: opcode满足如下条件时,操作数栈顶的值是返回值或者异常
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            if (Type.VOID == returnType.getSort()) {
                
                mv.visitLdcInsn(owner);
                mv.visitLdcInsn(name);
                mv.visitInsn(ACONST_NULL);
            } else {
            
                mv.visitInsn(DUP);
                // 注意: 因为end方法里面的参数是Object,所以如果返回结果是int,必须转换为Integer,否则无法接收
                emitAutoboxing();
                // 注意: 这里可能会影响局部变量表,因为局部变量表中的argTypes.length + 1这个地方可能有值
                mv.visitVarInsn(ASTORE, argTypes.length + 1);
            
                mv.visitLdcInsn(owner);
                mv.visitLdcInsn(name);
                mv.visitVarInsn(ALOAD, argTypes.length + 1);
            }
            mv.visitMethodInsn(INVOKESTATIC, SUMBITTER, END, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", false);
        }
        mv.visitInsn(opcode);
    }

    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack + 4, maxLocals);
        // mv.visitMaxs(maxStack + 4, maxLocals + 4);
    }
    
    public void emitAutoboxing() {
        switch (returnType.getSort()) {
        case Type.INT:
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            break;
        case Type.LONG:
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            break;
        case Type.FLOAT:
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            break;
        case Type.DOUBLE:
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            break;
        case Type.SHORT:
            mv.visitInsn(I2S);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            break;
        case Type.BYTE:
            mv.visitInsn(I2B);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            break;
        case Type.BOOLEAN:
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf",  "(Z)Ljava/lang/Boolean;");
            break;
        case Type.CHAR:
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            break;
        }
    }
}

/**
如果没有如下逻辑,会报如下错误:
if (Type.VOID == returnType.getSort()) {
原因是: 此时操作数栈顶没有值,所有执行dub指令的时候,会报错(Attempt to pop empty stack)

Exception in thread "main" java.lang.VerifyError: Operand stack underflow
Exception Details:
  Location:
    com/ilucky/test/asm/methodvisitor/three/Monitor.monitor()V @70: dup
  Reason:
    Attempt to pop empty stack.
  Current Frame:
    bci: @70
    flags: { }
    locals: { 'com/ilucky/test/asm/methodvisitor/three/Monitor', integer, integer, integer }
    stack: { }
  Bytecode:
    0000000: 120d 120e b800 14b2 001c 121e b600 2410
    0000010: 0c3c 053d 1b1c 603e 1400 25b8 002c b200
    0000020: 1cbb 002e 5912 30b7 0032 1db6 0036 b600
    0000030: 3ab6 0024 a700 0a3a 0419 04b6 003d b200
    0000040: 1c12 3fb6 0024 594c 120d 120e 2bb8 0043
    0000050: b1                                     
  Exception Handler Table:
    bci [24, 52] => handler: 55
  Stackmap Table:
    full_frame(@55,{Object[#2],Integer,Integer,Integer},{Object[#22]})
    same_frame(@62)

    at java.lang.Class.getDeclaredMethods0(Native Method)
    at java.lang.Class.privateGetDeclaredMethods(Class.java:2625)
    at java.lang.Class.getDeclaredMethods(Class.java:1868)
    at com.ilucky.test.asm.methodvisitor.three.MethodVisitorTest.test(MethodVisitorTest.java:41)
    at com.ilucky.test.asm.methodvisitor.three.MethodVisitorTest.main(MethodVisitorTest.java:22)
    
    

如果没有如下逻辑,会报如下错误(Bad type on operand stack):
emitAutoboxing();
原因是Submitter方法中用于接收返回值的参数是Object类型,但是返回值是int类型,不匹配
Exception in thread "main" java.lang.VerifyError: Bad type on operand stack
Exception Details:
  Location:
    com/ilucky/test/asm/methodvisitor/three/Monitor.monitor2()I @50: astore_1
  Reason:
    Type integer (current frame, stack[1]) is not assignable to reference type
  Current Frame:
    bci: @50
    flags: { }
    locals: { 'com/ilucky/test/asm/methodvisitor/three/Monitor', integer, integer, integer }
    stack: { integer, integer }
  Bytecode:
    0000000: 120d 124c b800 14b2 001c 121e b600 2410
    0000010: 0c3c 053d 1b1c 603e 1400 25b8 002c a700
    0000020: 0a3a 0419 04b6 003d b200 1c12 3fb6 0024
    0000030: 1d59 4c12 0d12 4c2b b800 43ac          
  Exception Handler Table:
    bci [24, 30] => handler: 33
  Stackmap Table:
    full_frame(@33,{Object[#2],Integer,Integer,Integer},{Object[#22]})
    same_frame(@40)

    at java.lang.Class.getDeclaredMethods0(Native Method)
    at java.lang.Class.privateGetDeclaredMethods(Class.java:2625)
    at java.lang.Class.getDeclaredMethods(Class.java:1868)
    at com.ilucky.test.asm.methodvisitor.three.MethodVisitorTest.test(MethodVisitorTest.java:41)
    at com.ilucky.test.asm.methodvisitor.three.MethodVisitorTest.main(MethodVisitorTest.java:22)


*/
