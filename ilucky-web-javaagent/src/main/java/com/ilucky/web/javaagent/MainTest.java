package com.ilucky.web.javaagent;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ilucky.web.javaagent.controller.TestController;


public class MainTest {
    
    
    private static ThreadLocal<Boolean> sampleThreadLocal = new ThreadLocal<Boolean>() {
        public Boolean initialValue() {
            return false;
        }
    };
    private static Logger logger = Logger.getLogger(TestController.class);
    public static void main(String[] args) {
        System.out.println(args);
        
        sampleThreadLocal.set(null);
        
       // Properties p = new Properties();
       Map<String, Object> p = new HashMap<String, Object>();
        Object value = null;
        p.put("key", value);
        
//        
//        String uri = "/aaa/te*st/adddd.mvc";
//        String urlPattern = "/aaa/.*/adddd.mvc";
//        Pattern p=Pattern.compile(urlPattern);
//        Matcher m=p.matcher(uri.toString());
//        if (m.find()){
//            System.out.println(true);
//        } else {
//            System.out.println(false);
//        }
//        System.out.println(Thread.currentThread().getStackTrace()[1].getLineNumber());
//        int i =0;
//        System.out.println(Thread.currentThread().getStackTrace()[1].getLineNumber());
//        System.out.println(Thread.currentThread().getStackTrace()[1].getLineNumber());
//        String s = "aaa";
//        System.out.println(s.length());
//        System.out.println(s.getBytes().length);
//        String b = "我是中";
//        System.out.println(b.length());
//        System.out.println(b.getBytes().length);
//        
//        
//        try {
//            String appConfigContent = null;
//            if(appConfigContent == null || appConfigContent.trim().equals("")) {
//                File f = new File("E:\\work\\yunzhihui\\work\\javaagent\\javaagent2\\conf\\app2.conf");
//                if(f.exists()) {
//                    FileInputStream inputStream = new FileInputStream(f);
//                    appConfigContent = IOUtils.toString(inputStream);
//                    // log.info(CloudwiseLogger.CAG_CONFIG, "Appcnfig content="+appConfigContent);
//                    System.out.println(appConfigContent);
//                }
//            }
//            System.out.println("------------");
//        }catch(Exception e) {
//            System.out.println(e);
//        }
        
//        logger.info("收到参数:===sandbox====");
//        
//        List<User> l = new ArrayList<User>();
//        l.add(new User("a"));
//        l.add(new User("b"));
//        l.add(new User("c"));
//        l.add(new User("d"));
//        
//        List<User> l2 = new ArrayList<User>();
//        l2.add(new User("a"));
//        l2.add(new User("a"));
//        
//        l.removeAll(l2);
//        System.out.println(l.size());
        
//        System.out.println("Before allocate map, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
//        ConcurrentHashMap<String, Object> c = new ConcurrentHashMap<String, Object>();
//        for(int i = 0; i <= 1000000; i++) {
//            c.put(i+"", i+"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//        }
//        System.out.println("free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
//        System.out.println(c.size());
//        c.clear();
//        System.out.println("free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
//        System.out.println(c.size());
    }
}

class User {
    
    private String username;
    
    User(String username) {
        this.username = username;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
    
    
}
