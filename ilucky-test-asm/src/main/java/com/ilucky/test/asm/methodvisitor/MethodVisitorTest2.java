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
    LINENUMBER 53 L0
    LCONST_1
    INVOKESTATIC java/lang/Thread.sleep (J)V
   L1
    LINENUMBER 54 L1
    GOTO L3
   L2
    ASTORE 1                      // 将Exception保存到局部变量表
   L4
    LINENUMBER 55 L4
    ALOAD 1                       
    INVOKEVIRTUAL java/lang/Exception.printStackTrace ()V
   L3
    LINENUMBER 57 L3
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
    
    /**
     * 证明LOAD后面不是index,而是开始槽位
     * 
     * @param i1
     * @param i2
     * @param i3
     * @param i4
     * @return
public manyInt(IIII)I
   L0
    LINENUMBER 112 L0
    ILOAD 1
    ILOAD 2
    IADD
    ILOAD 3
    IADD
    ILOAD 4
    IADD
    ISTORE 5
   L1
    LINENUMBER 113 L1
    ILOAD 5
    IRETURN
   L2
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest2; L0 L2 0
    LOCALVARIABLE i1 I L0 L2 1
    LOCALVARIABLE i2 I L0 L2 2
    LOCALVARIABLE i3 I L0 L2 3
    LOCALVARIABLE i4 I L0 L2 4
    LOCALVARIABLE i I L1 L2 5
    MAXSTACK = 2
    MAXLOCALS = 6

     */
    public int manyInt(int i1, int i2, int i3, int i4) {
        int i = i1 + i2 + i3 + i4;
        return i;
    }
    
    /**
     * 证明LOAD后面不是index,而是开始槽位
     * 
     * @param l1
     * @param l2
     * @return
     * 
 public manyLong(JJJJ)J
   L0
    LINENUMBER 137 L0
    LLOAD 1
    LLOAD 3
    LADD
    LLOAD 5
    LADD
    LLOAD 7
    LADD
    LSTORE 9
   L1
    LINENUMBER 138 L1
    LLOAD 9
    LRETURN
   L2
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest2; L0 L2 0
    LOCALVARIABLE l1 J L0 L2 1
    LOCALVARIABLE l2 J L0 L2 3
    LOCALVARIABLE l3 J L0 L2 5
    LOCALVARIABLE l4 J L0 L2 7
    LOCALVARIABLE l J L1 L2 9
    MAXSTACK = 4
    MAXLOCALS = 11
     */
    public long manyLong(long l1, long l2, long l3, long l4) {
        long l = l1 + l2 + l3 + l4;
        return l;
    }
}
