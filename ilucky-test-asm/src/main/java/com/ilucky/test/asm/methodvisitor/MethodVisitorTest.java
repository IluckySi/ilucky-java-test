package com.ilucky.test.asm.methodvisitor;

/**
 * 在编译器内部，方法的代码存储为一系列的字节码指令。
 * 为生成和转换类，最根本的就是要了解这些指令，并理解他们是如何工作的。
 * 
 * java虚拟机执行模型:
 * java代码是线程内部执行的，每个线程都有自己的执行栈，栈由帧组成，每个帧表示一个方法调用。
 * 每次调用一个方法时，会将一个新帧压入当前线程的执行栈; 当方法返回时，或者是因为异常返回，
 * 会将这个帧从栈中弹出。
 * 每个帧包含两部分:一个局部变量表和一个操作数栈部分。
 * 局部变量表包含可根据索引以随机顺序访问的变量。
 * 操作数栈按照后入先出的顺序访问，包含了字节码指令操作数的值。
 * 
 * --------------------------------字节码指令-----------------------------------------------------------
 * 字节码指令可以分为两类: 
 * a: 一小组指令用于在局部变量和操作数栈之间传递值;
 * b: 一大组指令用于操作数栈: 他们从栈中弹出一些值，根据这些值计算一个结果，并将他压回栈中
 * 
 * a指令:
 * ILOAD,LLOAD,FLOAD,DLOAD和ALOAD(用于加载任意非基本元素数据类型)指令作用是读取局部变量，并将它们的值压入到操作数栈中。与之对应,
 * ISTORE,LSTORE,FSTORE,DSTORE和ASTORE(用于存储任意非基本元素数据类型)指令作用是从操作数栈中弹出一个值，并将它存储在由其索引i指定的局部变量中
 * 参考如下demo
 * 
 * b指令:
 * 栈: 这些指令用于处理操作数栈上的值,POP弹出操作数栈顶部的值,DUP压入操作数栈顶部值的一个副本,SWAP弹出两个值,并按逆序压入他们。
 * 参考如下demo
 * 
 * 常量: 这些指令用于在操作数栈压入一个常量值,ACONST_NULL压入null,ICONST_0压入int值0,DCONST_0压入double值0d,
 * BIPUSH b压入字节值b,SIPUSH s压入short值s,LDC cst压入任意int,float,long,double,String或class常量cst等
 * 参考如下demo
 * 
 * 算数逻辑: 这些指令用于从操作数栈中弹出值,合并它们,并将结果压入栈中,他们没有任何参数
 * xADD，xSUB，xMUL，xDIV和xREM分别对应"+ - * \/ %"运算，其中x为I，F，或D等。类似的还有其他>>,<<,|,&等运算。
 * 参考如下demo
 * 
 * 类型转换: 这些指令用于从操作数栈中弹出一个值,将其转换为另一种类型,并将结果压入操作数栈中。他们对应于java中的类型转换表达式,
 * I2F，F2D，L2D等将数值由一种数值类型转换为另一种类型。
 * 参考如下demo
 * 
 * 对象: 这些指令用于创建对象,锁定他们,检测他们的类型等等,例如:NEW type指令将一个type类型的对象压入操作数栈中。
 * 参考如下demo
 * 
 * 字段: 这些指令用于读或写一个字段的值,GETFIELD ower name desc弹出一个对象引用,并将其name字段中的值压入操作数栈,
 * PUTFIELD ower name desc弹出一个对象引用,并将操作数栈中值存储在他的name字段中。
 * GETSTATIC和PUTSTATIC是类似指令, 但用于静态字段。
 * 参考如下demo
 * 
 * 方法:这些指令调用一个方法或构造器，INVOKEVIRTUAL owner name desc调用在owner类的name方法，方法描述符为desc。
 * INVOKESTATIC用于静态方法，INVOKESPECIAL用于私有方法和构造器，INVOKEINTERFACE方法用于接口中定义的方法，
 * 另外，在java7中的类，INVOKEDYNAMIC用于新动态方法调用机制。
 * 参考如下demo
 * 
 * 数组:这些指令用于读写数组中的值，xALOAD指令弹出一个索引和一个数组，并压入此索引数组元素的值，
 * xASTORE指令弹出一个值，一个索引和一个数组，并将这个值存储在该数组的这一索引处，这里的x可以是I，L，F，D，或A等。
 * 参考如下demo
 * 
 * 跳转:这些指令无条件的或者在某一条件为真时跳转到一条任意指令，他们用于编译if，for，do，while，break和continue等
 * 例如: IFEQ label, 从栈中弹出一个int值，这个值如果为0，这跳转到由这个label指定的指令处（否则，正常执行下一条指令），
 * 还有许多其他跳转指令: 比如IFNE或IFGE，最后，TABLESWITCH和LOOKUPSWITCH对应swich指令。
 * 参考如下demo
 * 
 * 返回: xRETURN和RETURN指令用于终止一个方法的执行,并将其结果返回给调用者.RETURN用于返回void方法,xRETURN用于其他方法。
 * 参考如下demo
 * 
 * 【通过Bytecode插件对上面的指令进行分析】
 * 注意: 在分析字节码时，时刻注意: 栈是一个动态的先进后出的数据结构
 * 
 * @author IluckySi
 *
 */
public class MethodVisitorTest {
   
   // private I i
   private int i;
   // private static I j
   private static int j;
   // private static I sum
   private static int sum;
   // private final static Ljava/lang/String; PASSWORD = "123"
   private static final String PASSWORD = "123";
   
   /**
    * 实例方法
    * 
   public getI()I
   L0          
    LINENUMBER 99 L0 // 代表source文件中第99行
    ALOAD 0    // 加载0局部变量(this)到操作数栈
    GETFIELD com/ilucky/test/asm/methodvisitor/MethodVisitorTest.i : I // GETFIELD ower name desc: 将栈顶的对象this的i变量压入到操作数栈中
    IRETURN    // 返回整数: 返回操作数栈顶的值 
   L1        
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest; L0 L1 0
    MAXSTACK = 1   // 代表了操作数栈深度的最大值
    MAXLOCALS = 1  // 代表了局部变量表所需最大空间
    * @return
    */
   public int getI() {
       return i;
   }
   
   /**
    * 实例方法
    * 
   public setI(I)V
   L0      
    LINENUMBER 122 L0 // 代表source文件中的第122行
    ALOAD 0 // 加载0局部变量(this)到操作数栈
    ILOAD 1 // 加载1局部变量(i)到操作数栈
    PUTFIELD com/ilucky/test/asm/methodvisitor/MethodVisitorTest.i : I // PUTFIELD ower name desc: 弹出操作数占中i对应的值, 再弹出操作数栈中的this, 将i对应的值赋给this.i
   L1
    LINENUMBER 123 L1
    RETURN // 结束
   L2
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest; L0 L2 0
    LOCALVARIABLE i I L0 L2 1
    MAXSTACK = 2    // 代表了操作数栈深度的最大值
    MAXLOCALS = 2   // 代表了局部变量表所需最大空间
    * @param i
    */
   public void setI(int i) {
       this.i = i;
   }
   
   /**
    * 静态方法
    * 
   public static setJ(I)V
   L0
    LINENUMBER 99 L0
    ILOAD 0    // 加载0局部变量(j, 注意:静态方法中没有this)到操作数栈
    PUTSTATIC com/ilucky/test/asm/methodvisitor/MethodVisitorTest.j : I // PUTSTATIC ower name desc: 弹出操作数栈中j的值, 再弹出操作数栈中的this, 将j对应的值赋给this.j
   L1
    LINENUMBER 100 L1
    RETURN    // 结束
   L2
    LOCALVARIABLE j I L0 L2 0
    MAXSTACK = 1   // 代表了操作数栈深度的最大值
    MAXLOCALS = 1  // 代表了局部变量表所需最大空间
    * @param j
    */
   public static void setJ(int j) {
       MethodVisitorTest.j = j;
   }
   
   /**
    * 静态方法就算两个数的和
    * 
    public static setSum(II)I
   L0
    LINENUMBER 150 L0
    ILOAD 0 // 加载0局部变量(i)到操作数栈
    ILOAD 1 // 加载1局部变量(j)到操作数栈
    IADD    // 对操作数栈中的元素做加法运算,并将结果压入操作数栈中
    PUTSTATIC com/ilucky/test/asm/methodvisitor/MethodVisitorTest.sum : I // PUTSTATIC ower name desc
   L1
    LINENUMBER 151 L1
    GETSTATIC com/ilucky/test/asm/methodvisitor/MethodVisitorTest.sum : I // GETSTATIC ower name desc
    IRETURN                                                               // 返回操作数栈中的值
   L2
    LOCALVARIABLE i I L0 L2 0
    LOCALVARIABLE j I L0 L2 1
    MAXSTACK = 2    // 代表了操作数栈深度的最大值
    MAXLOCALS = 2   // 代表了局部变量表所需最大空间
    * @param i
    * @param j
    */
   public static int setSum(int i, int j) {
       MethodVisitorTest.sum = i + j;
       return MethodVisitorTest.sum;
   }
   
   /**
    * 实例方法: 创建User类型对象
    * 
   public initUser()Lcom/ilucky/test/asm/methodvisitor/User;
   L0
    LINENUMBER 190 L0
    NEW com/ilucky/test/asm/methodvisitor/User  // 创建对象
    DUP                                         // 将对象的一个副本压入操作数栈(注意: NEW和DUP是组合使用的),这句话的意思不是操作数栈中有两个这个对象,只是有一个
    LDC "IluckySi"                              // 压入操作数栈一个变量
    LDC "123"                                   // 压入操作数栈一个变量
    INVOKESPECIAL com/ilucky/test/asm/methodvisitor/User.<init> (Ljava/lang/String;Ljava/lang/String;)V // 调用构造器,将返回结果放到操作数栈
    ASTORE 1                                    // 将生成的对象保存到局部变量表中第二个局部变量，因为第一个局部变量是this
   L1
    LINENUMBER 191 L1
    ALOAD 1                                     // 加载局部变量表中的第二个局部变量到操作数栈
    ASTORE 2                                    // 将操作数栈中的值保存到第三个局部变量
   L2
    LINENUMBER 192 L2
    ALOAD 2                                     // 加载局部变量表中的第三个局部变量到操作数栈
    ARETURN                                     // 返回操作数栈中的值
   L3
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest; L0 L3 0  // 局部变量
    LOCALVARIABLE u Lcom/ilucky/test/asm/methodvisitor/User; L1 L3 1                  // 局部变量
    LOCALVARIABLE copy Lcom/ilucky/test/asm/methodvisitor/User; L2 L3 2               // 局部变量
    MAXSTACK = 4  // 代表了操作数栈深度的最大值
    MAXLOCALS = 3 // 代表了局部变量表所需最大空间
    * @return
    */
   public User initUser() {
       User u = new User("IluckySi", PASSWORD);
       User copy = u;
       return copy;
   }
   
   /**
    * 测试跳转
    * 注意: LLOAD ISTORE等指令后面跟的是局部变量表的位置,并不代表变量,代表开始槽位
    * 即,第n个位置并不代表第n个变量，因为有的变量(long和double)占用两个位置。
    * 
    * 
   public testGoto(J)Z
   L0
    LINENUMBER 200 L0
    LLOAD 1    // 加载局部变量表中的第二个局部变量到操作数栈,因为第一个是this
    L2I        // 将操作数栈中的值由long类型转换为int类型, 并压入到操作数栈中
    ISTORE 3   // 弹出操作数栈中的值,保存到局部变量表第四个位置,注意: 第一个位置是this,第二个第三个位置是j(因为是long类型,所以占用两个槽位)
   L1
    LINENUMBER 201 L1
    ILOAD 3    // 加载局部变量表第四个位置的变量到操作数栈
    ICONST_2   // 向操作数栈压入常量2
    IF_ICMPLE L2 // 弹出操作数栈中的值,如果 ${ILOAD 3} <= ${ICONST_2},跳到L2, IF_ICMPLE指令的使用，注意顺序
   L3
    LINENUMBER 202 L3
    ICONST_1 // 向操作数栈压入常量1
    IRETURN  // 返回操作数栈中的值
   L2
    LINENUMBER 204 L2
    ICONST_0 // 向操作数栈压入常量0
    IRETURN  // 返回操作数栈中的值
   L4
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest; L0 L4 0
    LOCALVARIABLE l J L0 L4 1
    LOCALVARIABLE i I L1 L4 3
    MAXSTACK = 2  // 代表了操作数栈深度的最大值
    MAXLOCALS = 4 // 代表了局部变量表所需最大空间
    * @param l
    * @return
    */
   public boolean testGoto(long l) {
       int i = (int)l;
       if(i > 2) {
           return true;
       }
       return false;
   }
   
   /**
    * 数组
    * 
    public testArray()V
   L0
    LINENUMBER 249 L0
    ICONST_2          // 向操作数栈中压入常量2
    NEWARRAY T_INT    // 创建一个整型数组, 并压入到操作数栈
    ASTORE 1          // 将整型数组保存到第2个局部变量,因为第一个局部变量是this
   L1
    LINENUMBER 250 L1
    ALOAD 1           // 加载2局部变量
    ICONST_0          // 向操作数栈中压入int类型常量0       
    BIPUSH 10         // 向操作数栈中压入byte类型常量10     
    IASTORE           // 将操作数栈中的值(先弹出value,再弹出index)添加到数组
   L2
    LINENUMBER 251 L2
    ALOAD 1           // 加载2局部变量
    ICONST_1          // 向操作数栈中压入int类型常量1
    BIPUSH 100        // 向操作数栈中压入byte类型常量100
    IASTORE           // 将操作数栈中的值(先弹出value,再弹出index)添加到数组
   L3
    LINENUMBER 252 L3
    ALOAD 0           // 加载0局部变量this
    ALOAD 1           // 加载1局部变量数组
    ICONST_0          // 向操作数栈中压入int类型常量0    
    IALOAD            // 加载数组的第一个元素
    INVOKEVIRTUAL com/ilucky/test/asm/methodvisitor/MethodVisitorTest.setI (I)V // 调用对象的实例方法
   L4
    LINENUMBER 253 L4
    RETURN
   L5
    LOCALVARIABLE this Lcom/ilucky/test/asm/methodvisitor/MethodVisitorTest; L0 L5 0
    LOCALVARIABLE iArray [I L1 L5 1
    MAXSTACK = 3  // 代表了操作数栈深度的最大值
    MAXLOCALS = 2 // 代表了局部变量表所需最大空间
    */
   public void testArray() {
       int[] iArray = new int[2];
       iArray[0] = 10;
       iArray[1] = 100;
       setI(iArray[0]);
   }
}
