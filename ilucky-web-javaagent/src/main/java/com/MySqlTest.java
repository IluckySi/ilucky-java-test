//package com;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Arrays;
//
//import com.cloudwise.agent.core.util.MD5Util;
//
//
//public class MySqlTest {
//
//    public static void main(String[] args) {
//    	
//    	
//    	
////    	String agentRootPath = "/opt/tomcat/test";
////    			// System.getProperty("agentRootPath");
////		if (agentRootPath.endsWith("/")){
////			agentRootPath = agentRootPath.substring(0,agentRootPath.length() - 1);
////		}
////
////		//主机IP + 进程路径组成agentId
////		agentId = MD5Util.encode2hex(localIp + agentRootPath);
//		
//		
//		
////        String[] s = new String[2];
////        s[0]="2";
////        System.out.println(Arrays.asList(s));
////        String connStr = "jdbc:mysql://" + "10.0.3.42" + ":" 
////                + "3306" + "/information_schema?&useUnicode=true&characterEncoding=UTF-8";
//////        
//////        String connStr = "jdbc:mysql://" + "10.0.6.104" + ":" 
//////                + "3306" + "/information_schema?&useUnicode=true&characterEncoding=UTF-8";
////        try {
////            connStr = "jdbc:mysql://10.0.3.42:3306/information_schema?&useUnicode=true&characterEncoding=UTF-8";
////            System.out.println(connStr);
////             Connection conn = DriverManager.getConnection(connStr, "kevin", "000000");
////            // Connection conn = DriverManager.getConnection(connStr, "root", "yunzhihui123");
////            System.out.println(conn);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }
//    
//}
