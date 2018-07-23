package com.ilucky.test.asm.methodvisitor.two;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 在方法前后插入时间统计代码
 * 
 * @author IluckySi
 *
 */
public class AddMethodTimeMethodVisitor extends MethodVisitor implements Opcodes {

    private String owner;

    public AddMethodTimeMethodVisitor(int api, MethodVisitor mv, String owner) {
        super(api, mv);
        this.owner = owner;
    }

    public void visitCode() {
        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitInsn(Opcodes.LSUB); // -运算
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitInsn(LADD);
            mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
        }
        mv.visitInsn(opcode);
    }

    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack + 4, maxLocals);
    }
}
