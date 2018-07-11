package com.ilucky.test.asm.util;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.objectweb.asm.Type;

import com.ilucky.test.asm.classvisitor.three.User;

/**
 * 完全限定名和类型描述符
 * @author IluckySi
 *
 */
public class TypeTest {

	public static void main(String[] args) {
	    basic();
	    nonBasic();
	    other();
	}
	
	private static void basic() {
	    // 获取分类
	    System.out.println(Type.VOID);
	    System.out.println(Type.BOOLEAN);
	    System.out.println(Type.CHAR);
	    System.out.println(Type.BYTE);
	    System.out.println(Type.SHORT);
	    System.out.println(Type.INT);
	    System.out.println(Type.FLOAT);
        System.out.println(Type.LONG);
        System.out.println(Type.DOUBLE);
        System.out.println(Type.ARRAY);
        System.out.println(Type.OBJECT);
        System.out.println(Type.METHOD);
        System.out.println("--------------------");
        
        // 获取类型
        System.out.println(Type.VOID_TYPE);
        System.out.println(Type.BOOLEAN_TYPE);
        System.out.println(Type.CHAR_TYPE);
        System.out.println(Type.BYTE_TYPE);
        System.out.println(Type.SHORT_TYPE);
	    System.out.println(Type.INT_TYPE);
	    System.out.println(Type.FLOAT_TYPE);
	    System.out.println(Type.LONG_TYPE);
        System.out.println(Type.DOUBLE_TYPE);
        System.out.println("--------------------");
        
        // 获取类型描述符
        System.out.println(Type.VOID_TYPE.getDescriptor());
        System.out.println(Type.BOOLEAN_TYPE.getDescriptor());
        System.out.println(Type.CHAR_TYPE.getDescriptor());
        System.out.println(Type.BYTE_TYPE.getDescriptor());
        System.out.println(Type.SHORT_TYPE.getDescriptor());
        System.out.println(Type.INT_TYPE.getDescriptor());
        System.out.println(Type.FLOAT_TYPE.getDescriptor());
        System.out.println(Type.LONG_TYPE.getDescriptor());
        System.out.println(Type.DOUBLE_TYPE.getDescriptor());
        System.out.println("--------------------");
        
        // 获取类型大小(在变量表和操作数栈中的大小)
        System.out.println(Type.VOID_TYPE.getSize());
        System.out.println(Type.BOOLEAN_TYPE.getSize());
        System.out.println(Type.CHAR_TYPE.getSize());
        System.out.println(Type.BYTE_TYPE.getSize());
        System.out.println(Type.SHORT_TYPE.getSize());
        System.out.println(Type.INT_TYPE.getSize());
        System.out.println(Type.FLOAT_TYPE.getSize());
        System.out.println(Type.LONG_TYPE.getSize());
        System.out.println(Type.DOUBLE_TYPE.getSize());
        System.out.println("--------------------");
        
        // 获取类型名称
        System.out.println(Type.VOID_TYPE.getClassName());
        System.out.println(Type.BOOLEAN_TYPE.getClassName());
        System.out.println(Type.CHAR_TYPE.getClassName());
        System.out.println(Type.BYTE_TYPE.getClassName());
        System.out.println(Type.SHORT_TYPE.getClassName());
        System.out.println(Type.INT_TYPE.getClassName());
        System.out.println(Type.FLOAT_TYPE.getClassName());
        System.out.println(Type.LONG_TYPE.getClassName());
        System.out.println(Type.DOUBLE_TYPE.getClassName());
        System.out.println("--------------------");
	}
	
    private static void nonBasic() {
        // 获取类型描述符
        System.out.println(Type.getType(Integer.class).getDescriptor());
        System.out.println(Type.getType(String.class).getDescriptor());
        System.out.println(Type.getType(Object.class).getDescriptor());
        System.out.println(Type.getType(User.class).getDescriptor());
        System.out.println("--------------------");
        
        // 获取类型大小(在变量表和操作数栈中的大小)
        System.out.println(Type.getType(Integer.class).getSize());
        System.out.println(Type.getType(String.class).getSize());
        System.out.println(Type.getType(Object.class).getSize());
        System.out.println(Type.getType(User.class).getSize());
        System.out.println("--------------------");
        
        // 获取类型名称
        System.out.println(Type.getType(Integer.class).getClassName());
        System.out.println(Type.getType(String.class).getClassName());
        System.out.println(Type.getType(Object.class).getClassName());
        System.out.println(Type.getType(User.class).getClassName());
        System.out.println("--------------------");
        
        // 获取类型完全限定名
        System.out.println(Type.getType(Integer.class).getInternalName());
        System.out.println(Type.getType(String.class).getInternalName());
        System.out.println(Type.getType(Object.class).getInternalName());
        System.out.println(Type.getType(User.class).getInternalName());
        System.out.println("--------------------");
    }
    
    public static void other() {
        try {
            Method waitMethod = Object.class.getDeclaredMethod("wait", new Class[]{long.class, int.class});
            System.out.println(Type.getType(waitMethod));
            System.out.println(Type.getMethodDescriptor(waitMethod));
            System.out.println(Type.getReturnType(waitMethod));
            System.out.println(Arrays.asList(Type.getArgumentTypes(waitMethod)));
            
            System.out.println(Type.getReturnType("(IJ)V"));
            System.out.println(Arrays.asList(Type.getArgumentTypes("(IJ)V")));
            System.out.println(Type.getArgumentsAndReturnSizes("(IJ)V"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
	}
}
