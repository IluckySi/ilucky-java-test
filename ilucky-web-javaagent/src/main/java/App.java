

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Hello world!
 * -Dt=2
 */
public class App implements Runnable {
    
    private static String k8sNamespace;
    
    // 占用cpu资源
    public static void main( String[] args ) {
        
        
//        long s = System.currentTimeMillis();
//        for(int i=0; i<100000;i++) {
//            JSONObject jsonObject = new JSONObject();
//            for(int j=0; j<10; j++) {
//                jsonObject.put("k1"+j, "v1");
//                jsonObject.put("k2"+j, "v2");
//                jsonObject.put("k3"+j, "v3");
//            }
//            if(i==0) {
//                System.out.println(jsonObject.toJSONString());
//            }
//            jsonObject.toJSONString();
//            
//            // System.out.println();
//        }
//        System.out.println(System.currentTimeMillis() - s);
//        
//        
//        long s2 = System.currentTimeMillis();
//        for(int i=0; i<100000;i++) {
//            Map<String, Object> m = new HashMap<String, Object>();
//            for(int j=0; j<10; j++) {
//                m.put("k1"+j, "v1");
//                m.put("k2"+j, "v2");
//                m.put("k3"+j, "v3");
//            }
//            if(i==0) {
//                System.out.println(m);
//            }
//            JSON.toJSONString(m);
//            // System.out.println();
//        }
//        System.out.println(System.currentTimeMillis() - s2);
//        
        // String urlPattern = "/ilucky-web-javaagent/.*/request.mvc";
        // String urlPattern = "/ilucky-web-javaagent/*/*.mvc"; // true java.util.regex.Matcher[pattern=/ilucky-web-javaagent/.*/*.mvc region=0,38 lastmatch=]
        String urlPattern = "/ilucky-web-javaagent/*/request.mvc"; // false java.util.regex.Matcher[pattern=/ilucky-web-javaagent/*/request.mvc region=0,38 lastmatch=]
        // String urlPattern = "/ilucky-web-javaagent/*/";  // true java.util.regex.Matcher[pattern=/ilucky-web-javaagent/*/ region=0,38 lastmatch=]
        // String urlPattern = "/ilucky-web-javaagent/*";      // true java.util.regex.Matcher[pattern=/ilucky-web-javaagent/* region=0,38 lastmatch=]
        
        // equals
        String url = "/ilucky-web-javaagent/*/request.mvc";
        
        Pattern p=Pattern.compile(urlPattern);
        System.out.println(p);
        Matcher m=p.matcher(url);
        System.out.println(m);
        System.out.println(m.find());
        // System.out.println(urlPattern.equals(url));
        
//        System.setProperty("k8sNamespace", k8sNamespace);
//        System.out.println("k8sNamespace is :  " + k8sNamespace);
//        for(int i=0; i<1; i++) {
//            String cp = System.getProperty("java.class.path");
//            String cp2 = System.getProperty("java.class.path");
//            System.out.println(cp);
//            if(!cp.equals(cp2))System.out.println();
//            
//            
//            
//            
//        }
//        
        
//        Properties props = System.getProperties();
//        Iterator iter = props.keySet().iterator();
//        while (iter.hasNext()) {
//            String key = (String) iter.next();
//            System.out.println(key + "=="+  props.get(key));
//        }
        
        
//        int n = 10;
//        String t = System.getProperty("t");
//        try {
//            n = Integer.parseInt(t);
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        System.out.println( "Thread = " + n);
//        for(int i=0; i<=n; i++) {
//            if(i/2==0) {
//                new Thread(new App(-1)).start();
//            } else {
//                new Thread(new App(0)).start();
//            }
//        }
    }

    int i = 0;
    App (int i) {
        this.i = i;
    }
    
    public void run() {
        while(true) {
           // System.out.println("----------");
//            try {
//                // 序列化
//                // for(int i=0; i<10; i++) {
////                    User u = new User();
////                    String us = JSON.toJSONString(u);
////                    System.out.println(us);
//                    // Object uo = JSON.parse(us);
//                // }
//                if(i==0) {
//                    Thread.sleep(0);  
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
