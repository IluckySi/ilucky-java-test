package com.ilucky.test.policy;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author IluckySi
 *
 * 创建policy文件, 添加如下内容:
grant codeBase "file:D:/*" {
    permission java.io.FilePermission "d:/ilucky.txt", "read";
};
第一行的意思是: 给某个目录下的code定义权限
第二行的意思是: 给ilucky.txt文件分配只读权限
意思是: codeBase目录下的代码对于d:/ilucky.txt文件只有只读权限

打包(mvn clean package), 将ilucky-test-jdk.jar文件拷贝到D盘目录下, 执行如下指令:
D:\>java -Djava.security.manager -Djava.security.policy=D:\core\git2018\ilucky-java-test\ilucky-test-jdk\target\classes\policy -jar ilucky-test-jdk.jar
java.security.AccessControlException: access denied ("java.io.FilePermission" "D:\ilucky.txt" "write")
        at java.security.AccessControlContext.checkPermission(AccessControlContext.java:472)
        at java.security.AccessController.checkPermission(AccessController.java:884)
        at java.lang.SecurityManager.checkPermission(SecurityManager.java:549)
        at java.lang.SecurityManager.checkWrite(SecurityManager.java:979)
        at java.io.FileOutputStream.<init>(FileOutputStream.java:200)
        at java.io.FileOutputStream.<init>(FileOutputStream.java:101)
        at java.io.FileWriter.<init>(FileWriter.java:63)
        at com.ilucky.test.policy.PolicyTest.main(PolicyTest.java:40)

D:\>
-Djava.security.manager 意思是启动默认的安全管理器
-Djava.security.policy  意思是设置安全策略文件

重新调整policy文件, 如下:
grant codeBase "file:D:/*" {
    permission java.io.FilePermission "d:/ilucky.txt", "read,write";
};
再次执行, 不报异常了
D:\>java -Djava.security.manager -Djava.security.policy=D:\core\git2018\ilucky-java-test\ilucky-test-jdk\target\classes\policy -jar ilucky-test-jdk.jar
Success~

D:\>

重新调整policy文件, 如下两种情况也都没有报异常
grant {
    permission java.io.FilePermission "D:/ilucky.txt", "read,write";
};
grant {
    permission java.security.AllPermission;
};
 */
public class PolicyTest {

    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("D:\\ilucky.txt");
            fw.write("Policy");
            System.out.println("Success~");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/**
如果添加一个不存在的权限, 如下:
grant codeBase "file:D:/*" {
    permission java.io.FilePermission "d:/ilucky.txt", "read,write,test";
};

会报如下异常:
D:\>java -Djava.security.manager -Djava.security.policy=D:\core\git2018\ilucky-java-test\ilucky-test-jdk\target\classes\policy -jar ilucky-test-jdk.jar
java.security.policy: 添加权限java.io.FilePermission时出错:
        java.lang.IllegalArgumentException: invalid permission: read,write,test
java.security.AccessControlException: access denied ("java.io.FilePermission" "D:\ilucky.txt" "write")
        at java.security.AccessControlContext.checkPermission(AccessControlContext.java:472)
        at java.security.AccessController.checkPermission(AccessController.java:884)
        at java.lang.SecurityManager.checkPermission(SecurityManager.java:549)
        at java.lang.SecurityManager.checkWrite(SecurityManager.java:979)
        at java.io.FileOutputStream.<init>(FileOutputStream.java:200)
        at java.io.FileOutputStream.<init>(FileOutputStream.java:101)
        at java.io.FileWriter.<init>(FileWriter.java:63)
        at com.ilucky.test.policy.PolicyTest.main(PolicyTest.java:40)

D:\>

*/
