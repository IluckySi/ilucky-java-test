package com.ilucky.test.asm.classvisitor.three;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 添加类成员
 * @author IluckySi
 *
 */
public class AddFieldClassVisitor extends ClassVisitor {

	private boolean isIncludeField;
	private String fname;
	private String fdesc;
	
	public AddFieldClassVisitor(int api, ClassVisitor cv, String fname, String fdesc) {
		super(api, cv);
		this.fname=fname;
		this.fdesc=fdesc;
	}
	
   public FieldVisitor visitField(int access, String name, String desc,  String signature, Object value) {
	   // 判断是否包含类成员变量
       if(name.equals(fname)) {
		   isIncludeField = true;
		   // 注意: 如果此属性存在，需要删除此属性, 只需要返回null即可
		   // return null;
	   }
	   return cv.visitField(access, name, desc, signature, value);
   }
   
   public void visitEnd() {
       // 如果不包含，则添加
	   if(!isIncludeField) {
		   FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC, fname, fdesc, null, null);
		   if(fv != null) {
		       fv.visitEnd();
		   }
	   }
       cv.visitEnd();
   }
}
