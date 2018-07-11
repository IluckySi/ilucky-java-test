package com.ilucky.test.asm.classreader.one;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author IluckySi
 *
 */
public class ClassPrinterVisitor extends ClassVisitor{

	public ClassPrinterVisitor(int api) {
		super(api);
	}

	/**
	 * 访问类信息
	 * Parameters:
		version the class version.
		access the class's access flags (see Opcodes). This parameter also indicates if the class is deprecated.
		name the internal name of the class (see getInternalName).
		signature the signature of this class. May be null if the class is not a generic one, and does not extend or implement generic classes or interfaces.
		superName the internal of name of the super class (see getInternalName). For interfaces, the super class is Object. May be null, but only for the Object class.
		interfaces the internal names of the class's interfaces (see getInternalName). May be null.
	 */
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		System.out.println(name+" extends " + superName + " {");
	}
	
	/**
	 * 访问字段信息
	 * 注意: 针对value字段只有静态类型才有用
	 * Parameters:
		access the field's access flags (see Opcodes). This parameter also indicates if the field is synthetic and/or deprecated.
		name the field's name.
		desc the field's descriptor (see Type).
		singnature the field's signature. May be null if the field's type does not use generic types.
		value the field's initial value. This parameter, which may be null if the field does not have an initial value, 
		    must be an Integer, a Float, a Long, a Double or a String (for int, float, long or String fields respectively). 
		    This parameter is only used for static fields. Its value is ignored for non static fields, 
		    which must be initialized through bytecode instructions in constructors or methods.
		Returns:
		a visitor to visit field annotations and attributes, or null if this class visitor is not interested in visiting these annotations and attributes.
	 */
	public FieldVisitor visitField(int access, String name, String desc, String singnature, Object value) {
		System.out.println(" " + desc + " " + name);
		return null;
	}
	
	/**
	 * 访问方法信息
	 * Parameters:
		access the method's access flags (see Opcodes). This parameter also indicates if the method is synthetic and/or deprecated.
		name the method's name.
		desc the method's descriptor (see Type).
		singature the method's signature. May be null if the method parameters, return type and exceptions do not use generic types.
		exceptions the internal names of the method's exception classes (see getInternalName). May be null.
		Returns:
		an object to visit the byte code of the method, or null if this class visitor is not interested in visiting the code of this method.
	 */
	public MethodVisitor visitMethod(int access, String name, String desc, String singature, String[] exceptions) {
	    System.out.println(" " + desc + " " + name);
	    // System.out.println(" " + desc + " " + singature + " " + name);
		return null;
	}
	
	/**
	 * 访问结束标志
	 */
	public void visitEnd() {
		System.out.println("}");
	}
}
