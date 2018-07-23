package com.ilucky.test.asm.methodvisitor;

public class MethodVisitorTest2 {

    /**
     * Exception
     * 
   public throwsException()V throws java/lang/Exception 
   L0
    LINENUMBER 7 L0
    NEW java/lang/Exception   // 创建异常对象并保存到操作数栈
    DUP                       // 为操作数栈中的异常对象生成副本
    LDC "MyException"         // 向操作数栈中写入
    INVOKESPECIAL java/lang/Exception.<init> (Ljava/lang/String;)V
    ATHROW
   L1
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest2; L0 L1 0
    MAXSTACK = 3
    MAXLOCALS = 1
     * @throws Exception
     */
    public void throwsException() throws Exception {
       throw new Exception("MyException");
    }
    
    /**
     public tryCatchTest()V
    TRYCATCHBLOCK L0 L1 L2 java/lang/Exception
   L0
    LINENUMBER 29 L0
    LCONST_1
    INVOKESTATIC java/lang/Thread.sleep (J)V
   L1
    LINENUMBER 30 L1
    GOTO L3
   L2
    ASTORE 1
   L4
    LINENUMBER 31 L4
    ALOAD 1
    INVOKEVIRTUAL java/lang/Exception.printStackTrace ()V
   L3
    LINENUMBER 33 L3
    RETURN
   L5
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest2; L0 L5 0
    LOCALVARIABLE e Ljava/lang/Exception; L4 L3 1
    MAXSTACK = 2
    MAXLOCALS = 2
     */
    public void tryCatchTest() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * try catch
     * 
   public tryCatch()V
    TRYCATCHBLOCK L0 L1 L2 java/lang/Exception
   L3
    LINENUMBER 33 L3
    ICONST_0
    ISTORE 1
   L0
    LINENUMBER 35 L0
    BIPUSH 10
    ILOAD 1
    IDIV
    ISTORE 2
   L4
    LINENUMBER 36 L4
    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    ILOAD 2
    INVOKEVIRTUAL java/io/PrintStream.println (I)V
   L1
    LINENUMBER 37 L1
    GOTO L5
   L2
    ASTORE 2
   L6
    LINENUMBER 38 L6
    ALOAD 2
    INVOKEVIRTUAL java/lang/Exception.printStackTrace ()V
   L5
    LINENUMBER 40 L5
    RETURN
   L7
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest2; L3 L7 0
    LOCALVARIABLE i I L0 L7 1
    LOCALVARIABLE result I L4 L1 2
    LOCALVARIABLE e Ljava/lang/Exception; L6 L5 2
    MAXSTACK = 2
    MAXLOCALS = 3
     */
    public void tryCatch() {
        int i = 0;
        try {
            int result = 10/i;
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
