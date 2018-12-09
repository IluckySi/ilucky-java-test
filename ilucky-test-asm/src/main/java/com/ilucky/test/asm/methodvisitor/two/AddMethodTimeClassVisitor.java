package com.ilucky.test.asm.methodvisitor.two;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 统计方法执行时间
 * 
 * @author IluckySi
 *
 */
public class AddMethodTimeClassVisitor extends ClassVisitor implements Opcodes {

    private boolean isInterface;
    private String owner;

    public AddMethodTimeClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        this.isInterface = (access & ACC_INTERFACE) != 0;
        this.owner = name;
    }

// 注意: 针对Monitor类,不会过这里,因为Monitor类没有Field, 只有有Field, 才会visit   
//    public FieldVisitor visitField(int access, String name, String desc,
//            String signature, Object value) {
//        System.out.println("=========FieldVisitor============");
//        FieldVisitor fv = null;
//        if (cv != null) {
//            fv = cv.visitField(ACC_PUBLIC + ACC_STATIC, "timer", "J", null, null);
//            fv.visitEnd();
//        }
//        return fv;
//    }
    
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("=========MethodVisitor============");
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        // 排除接口和非接口的构造方法
        if (!isInterface && mv != null && !name.equals("<init>")) {
            mv = new AddMethodTimeMethodVisitor(ASM5, mv, owner);
        }
        return mv;
    }

    public void visitEnd() {
        // 添加字段
        if (!isInterface) {
            FieldVisitor fv = cv.visitField(ACC_PUBLIC + ACC_STATIC, "timer", "J", null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
