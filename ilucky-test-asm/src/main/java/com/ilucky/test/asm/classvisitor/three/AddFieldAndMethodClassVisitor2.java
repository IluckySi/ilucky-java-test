package com.ilucky.test.asm.classvisitor.three;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 添加类静态成员变量和实例方法
 * 
 * @author IluckySi
 *
 */
public class AddFieldAndMethodClassVisitor2 extends ClassVisitor implements Opcodes {

	private boolean isIncludeField;
	private boolean isIncludeMethod;
	private String fname;
	private String fdesc;
	private String mname;
    private String mdesc;
	
	public AddFieldAndMethodClassVisitor2(int api, ClassVisitor cv, String fname, String fdesc,
	        String mname, String mdesc) {
		super(api, cv);
		this.fname=fname;
		this.fdesc=fdesc;
		this.mname=mname;
		this.mdesc=mdesc;
	}
	
   public FieldVisitor visitField(int access, String name, String desc,  String signature, Object value) {
	   // 判断是否包含类成员变量
       if(name.equals(fname) && desc.equals(fdesc)) {
		   isIncludeField = true;
		   // 注意: 如果此属性存在，需要删除此属性, 只需要返回null即可
           // return null;
	   }
	   return cv.visitField(access, name, desc, signature, value);
   }
   
   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
       // 判断是否包含类成员方法
       if(name.equals(mname) && desc.equals(mdesc)) {
           isIncludeMethod = true;
           // 注意: 如果此方法存在，需要删除此方法, 只需要返回null即可
           // return null;
       }
       return cv.visitMethod(access, name, desc, signature, exceptions);
   }
   
   public void visitEnd() {
       // 如果不包含，则添加
	   if(!isIncludeField) {
		   FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, fname, fdesc, null, "ilucky-password");
		   if (fv != null) {
		       fv.visitEnd();
		   }
	   }
	   if(!isIncludeMethod) {
	       MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, mname, mdesc, null, null);;
	       if (mv != null) {
	           mv.visitVarInsn(ALOAD, 0);
	           mv.visitFieldInsn(GETSTATIC, "com/ilucky/test/asm/classvisitor/three/User", fname, fdesc);
	           mv.visitInsn(ARETURN);
	           mv.visitMaxs(2, 1);
	           mv.visitEnd();
	       }
	   }
       cv.visitEnd();
   }
}
