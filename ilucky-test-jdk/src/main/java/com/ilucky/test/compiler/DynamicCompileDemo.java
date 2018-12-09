package com.ilucky.test.compiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * 参考: https://www.jianshu.com/p/44395ef6406f
 * @author IluckySi
 *
 */
public class DynamicCompileDemo {

    static void eval(String source) {
        // 构建一个类的源代码
        StringBuffer sourceCode = new StringBuffer();
        sourceCode.append("public class Temp {").append("\r\n")
                .append("public static String call(String args) {").append("\r\n")
                .append("System.out.println(\""+source+"\");").append("\r\n")
                .append("return \"Hello, " + source + "\";").append("\r\n")
                .append("}").append("\r\n")
                .append("}");
        try {
            // 生成Java源文件
            String javaFileName = "Temp.java";
            File sourceDir = new File("D:\\core\\git2018\\ilucky-java-test\\ilucky-test-jdk\\src\\main\\java");
            if (!sourceDir.exists()) {
                sourceDir.mkdirs();
            }
            File javaFile = new File(sourceDir, javaFileName);
            PrintWriter writer = new PrintWriter(new FileWriter(javaFile));
            writer.write(sourceCode.toString());
            writer.flush();
            writer.close();

            // 重点: 将生成的class文件存拷贝到class.path目录下, class.path必须是正确的
            System.out.println(System.getProperties());
            // File distDir = new File("D:\\core\\git2018\\ilucky-java-test\\ilucky-test-jdk\\target\\classes");
            File distDir = new File(System.getProperty("java.class.path").split(";")[0]);// Windows系统用;分隔
            // File distDir = new File(System.getProperty("user.dir")); // java.lang.ClassNotFoundException: Temp
            if (distDir.exists()) {
                distDir.delete();
            }
            distDir.mkdirs();
            // 获取JavaCompiler
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            
            // JavaCompiler最核心的方法是run, 通过这个方法编译java源文件, 前3个参数代表的意思是: 
            // 使用标准输入/输出/错误流 来处理输入和编译输出. 使用编译参数-d指定字节码输出目录.
            int compileResult = javac.run(null, null, null, "-d", distDir.getAbsolutePath(), javaFile.getAbsolutePath());
            
            // run方法的返回值: 0-表示编译成功, 否则表示编译失败
            if(compileResult != 0) {
                System.err.println("编译失败!!");
                return;
            }

            // 动态执行 (反射执行)
            Class klass = Class.forName("Temp");
            Method evalMethod = klass.getMethod("call", String.class);
            String result = (String)evalMethod.invoke(klass.newInstance(), source);
            System.out.println("eval(" + source + ") = " + result);
            System.out.println(System.getProperties());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        eval("ILucky Si");
    }
}

