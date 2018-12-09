package com.ilucky.web.javaagent.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

/**
 * @author IluckySi
 * @since 20151014
 */
public class CommonService {

	private static Logger logger = Logger.getLogger(CommonService.class);
	public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
	
	public static void main(String[] args) {
//	    String classResourcePath = CommonService.class.getName().replaceAll("\\.", "/") + ".class";
//        URL resource = CommonService.class.getClassLoader().getSystemClassLoader().getResource(classResourcePath);
//        System.out.println(resource);
	    

	    // Object o = "1801250089.pool2-site.make.yun300.cn";
	    Object o = "1706160095.pool1-site.yun300.cn";
	    String sn = o.toString();
	    sn = sn.substring(sn.indexOf(".")+1);
	    System.out.println("------------"+sn);
	    
	    
	    String i = "12";
	    long count = Long.parseLong(i);
       AtomicLong n = new AtomicLong(2);
       n.addAndGet(count);
       System.out.println(n);
	}
	
	
	public static void commonService(HttpServletResponse response, Object result) {
		String serviceResult = JSON.toJSONString(result);
		
//		StringBuilder sb = new StringBuilder();
//		Class c = CommonService.class;
//		 ClassLoader xc = null;
//		if(c != null) {
//		    ClassLoader cl = c.getClassLoader();
//		    if(cl != null) {
//		        sb.append("【CURRENT】").append(cl).append("---").append(cl.hashCode());
//		        ClassLoader clp = cl.getParent();
//		        if(clp != null) {
//		            sb.append("\r\n");
//		            sb.append("---parent---").append(clp).append("---").append(clp.hashCode());
//		            ClassLoader clpp = clp.getParent();
//		            xc = clpp;
//		            if(clpp != null) {
//		                sb.append("\r\n");
//		                sb.append("------parent---").append(clpp).append("---").append(clpp.hashCode());
//		                ClassLoader clppp = clpp.getParent();
//		                sb.append("\r\n");
//		                if(clppp != null) {
//		                    sb.append("---------parent---").append(clppp).append("---").append(clppp.hashCode());
//		                }
//		            }
//		        }
//		    }
//		}
//		URL cc = xc.getResource("javax.servlet.http.HttpServletRequest".replace(".", "/"));
//		serviceResult = "\r\n" + xc + cc + "\r\n";
//		serviceResult = "\r\n" + serviceResult + cc + "\r\n";
//		serviceResult += sb.toString();
//		String cpath = System.getProperty("java.class.path");
//		serviceResult=serviceResult+"\r\n"+"-------"+"classpath="+cpath;
//		String udir = System.getProperty("user.dir");    
//		serviceResult=serviceResult+"\r\n"+"-------"+"udir="+udir;
//		String rPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//		serviceResult=serviceResult+"\r\n"+"-------"+"rPath="+rPath;
//		String rPath2 = CommonService.class.getClassLoader().getResource("/").getPath();
//	    serviceResult=serviceResult+"\r\n"+"-------"+"rPath2="+rPath2;
//	    String rPath3 = CommonService.class.getClassLoader().getResource("").getPath();
//        serviceResult=serviceResult+"\r\n"+"-------"+"rPath3="+rPath3;
//		
//        serviceResult=serviceResult+"\r\n";
//        serviceResult=serviceResult+"\r\n";
//        serviceResult=serviceResult+"\r\n";
        
		logger.info("业务层处理结果:" + serviceResult);
		if(serviceResult != null) {
			response.setContentType(CONTENT_TYPE_JSON);
			PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write(serviceResult);
				pw.flush();
			} catch (IOException e) {
				logger.error("业务层处理发生异常:"+e);
			} finally {
				if(pw != null) {
					pw.close();
					pw = null;
				}
			}
		}
	}
	
//	public static void main(String[] args) {
////	    System.out.println("javax.servlet.http.HttpServletRequest".replace(".", "/"));
////	    String epath = System.getProperty("java.ext.dirs");      
////        System.out.println(epath);
//        String cpath = System.getProperty("java.class.path");
//        String lpath = System.getProperty("java.library.path");
//        System.out.println(cpath);
//        System.out.println(lpath);
//    }
}
