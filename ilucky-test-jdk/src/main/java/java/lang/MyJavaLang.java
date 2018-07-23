package java.lang;

public class MyJavaLang {

    public static void main(String[] args) {
        System.out.println("===============");
    }
}
/**
Exception in thread "main" java.lang.SecurityException: Prohibited package name: java.lang
at java.lang.ClassLoader.preDefineClass(ClassLoader.java:658)
at java.lang.ClassLoader.defineClass(ClassLoader.java:794)
at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
at java.net.URLClassLoader.defineClass(URLClassLoader.java:449)
at java.net.URLClassLoader.access$100(URLClassLoader.java:71)
at java.net.URLClassLoader$1.run(URLClassLoader.java:361)
at java.net.URLClassLoader$1.run(URLClassLoader.java:355)
at java.security.AccessController.doPrivileged(Native Method)
at java.net.URLClassLoader.findClass(URLClassLoader.java:354)
at java.lang.ClassLoader.loadClass(ClassLoader.java:425)
at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308)
at java.lang.ClassLoader.loadClass(ClassLoader.java:358)
at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:482)
*/