package com.ilucky.web.javaagent.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.OtherPackageTest;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ilucky.web.javaagent.controller.session.User;
import com.ilucky.web.javaagent.custom.Test;
import com.ilucky.web.javaagent.custom.Test1;
import com.ilucky.web.javaagent.custom.Test11;
import com.ilucky.web.javaagent.custom.Test12;
import com.ilucky.web.javaagent.custom.Test13;
import com.ilucky.web.javaagent.custom.Test2;
import com.ilucky.web.javaagent.custom.Test3;
import com.ilucky.web.javaagent.custom.TestAbs;
import com.ilucky.web.javaagent.custom.TestInter;
import com.ilucky.web.javaagent.exception.MyException;
import com.ilucky.web.javaagent.exception.TestExe;
import com.ilucky.web.javaagent.mysql.MysqlMain;
import com.ilucky.web.javaagent.quartz.QuartzManager;
import com.ilucky.web.javaagent.sandbox.BrokenClock;
import com.ilucky.web.javaagent.sandbox.Clock;
import com.ilucky.web.javaagent.service.MyInterface;
import com.ilucky.web.javaagent.service.MyInterfaceImpl;
import com.ilucky.web.javaagent.util.HttpUtil;
import com.ilucky.web.javaagent.util.MemcacheUtil;
import com.urlconnection.UrlConnectionTest;

/**
 * @author IluckySi
 * @since 20151014
 */
@Controller("test")
@Scope("prototype")
@RequestMapping("/test")
public class TestController {

	private static Logger logger = Logger.getLogger(TestController.class);
	
//	public static void main(String[] args) {
//	    MyProjectTest.main(null);
//    }
	
	@RequestMapping(value="/connect")
    public void connect(HttpServletRequest request, HttpServletResponse response) {
		UrlConnectionTest.main(null);
	}
	
	
	@RequestMapping(value="/mysql")
    public void mysql(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "key", required = false) int key) {
	        MysqlMain.test(key);
	}
	
	  @RequestMapping(value="/time")
	   public void time(HttpServletRequest request, HttpServletResponse response) {
		  QuartzManager.main(null);
		  CommonService.commonService(response, "quartz");
	  	}
	  
	  @RequestMapping(value="/http")
	   public void http(HttpServletRequest request, HttpServletResponse response) {
		  HttpUtil.httpPost("http://www.baidu.com", "ss");
		  CommonService.commonService(response, "time");
	  	}
	
	// 普通class
    @RequestMapping(value="/tn")
    public void tn(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到参数:===tn====");
        logger.info("收到参数:===tn====id="+Thread.currentThread().getId());
        logger.info("收到参数:===tn====name="+Thread.currentThread().getName());
        long sum = 0;
        for(int i=0; i<500000; i++) {
             sum+=i;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<500000; i++) {
            sum+=i;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==============>"+sum);
        logger.info("收到参数:===tn2====id="+Thread.currentThread().getId());
        logger.info("收到参数:===tn2====name="+Thread.currentThread().getName());
        CommonService.commonService(response, "base");
    }
    
	// 普通class
	@RequestMapping(value="/base")
    public void base(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到参数:===base====");
        
        Test test = new Test();
        test.test();
        test.test("test");
        test.test(1, 3);
        test.test("test", 4);
        test.test("Test", true);
        test.test("test", new User());
        test.test2();
        test.test3();
        
        CommonService.commonService(response, "base");
	}
	
	// 普通class
    @RequestMapping(value="/abs")
    public void abs(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到参数:===abs====");
        
        TestAbs test1 = new Test1();
        test1.test();
        test1.test("test");
        test1.test(1, 2);
        test1.test3();
        
        TestAbs test2 = new Test2();
        test2.test();
        test2.test("test");
        test2.test(1, 2);
        test2.test3();
        
        TestAbs test3 = new Test3();
        test3.test();
        test3.test("test");
        test3.test(1, 2);
        test3.test3();
        CommonService.commonService(response, "abs");
    }
    
 // 普通class
    @RequestMapping(value="/inter")
    public void inter(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到参数:===inter====");
        
        TestInter test1 = new Test11();
        test1.test();
        test1.test("test");
        test1.test(1, 2);
        test1.test3();
        
        TestInter test2 = new Test12();
        test2.test();
        test2.test("test");
        test2.test(1, 2);
        test2.test3();
        
        TestInter test3 = new Test13();
        test3.test();
        test3.test("test");
        test3.test(1, 2);
        test3.test3();
        CommonService.commonService(response, "inter");
    }
	
	@RequestMapping(value="/head", method = RequestMethod.HEAD)
    public void head(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到参数:===head====");
        Map<String, String[]> map = request.getParameterMap();
        for(Entry<String, String[]> e : map.entrySet()) {
            System.out.println(e.getKey() + "-----"+ Arrays.asList(e.getValue()));
        }
        try {
            logger.info("user=====>"+request.getSession().getAttribute("user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=============head====================");
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("result", "head");
        CommonService.commonService(response, result);
//        try {
//            String xRealIp = request.getHeader("X-Real-IP");
//            String xForwareIp = request.getHeader("X-Forwarded-For");
//            String remoteIp = request.getRemoteAddr();
//            System.out.println("xRealIp=" + xRealIp + ",xForwareIp=" + xForwareIp + ",remoteIp=" + remoteIp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String serviceResult="{\"key\":\"head\"}";
//        if(serviceResult != null) {
//            response.setContentType(CommonService.CONTENT_TYPE_JSON);
//            PrintWriter pw = null;
//            try {
//                pw = response.getWriter();
//                pw.write(serviceResult);
//                pw.flush();
//            } catch (IOException e) {
//                logger.error("业务层处理发生异常:"+e);
//            } finally {
//                if(pw != null) {
//                    pw.close();
//                    pw = null;
//                }
//            }
//        }
    }
	
	@RequestMapping(value="/put", method = RequestMethod.PUT)
    public void put(HttpServletRequest request, HttpServletResponse response) {
	      logger.info("收到参数:===put====");
	      Map<String, String[]> map = request.getParameterMap();
	        for(Entry<String, String[]> e : map.entrySet()) {
	            System.out.println(e.getKey() + "-----"+ Arrays.asList(e.getValue()));
	        }
	        System.out.println("=============put====================");
	        try {
	            logger.info("user=====>"+request.getSession().getAttribute("user"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        Map<String,Object> result = new HashMap<String,Object>();
	        result.put("result", "put");
	        CommonService.commonService(response, result);
//        logger.info("收到参数:===put====");
//        try {
//            String xRealIp = request.getHeader("X-Real-IP");
//            String xForwareIp = request.getHeader("X-Forwarded-For");
//            String remoteIp = request.getRemoteAddr();
//            System.out.println("xRealIp=" + xRealIp + ",xForwareIp=" + xForwareIp + ",remoteIp=" + remoteIp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String serviceResult="{\"key\":\"put\"}";
//        if(serviceResult != null) {
//            response.setContentType(CommonService.CONTENT_TYPE_JSON);
//            PrintWriter pw = null;
//            try {
//                pw = response.getWriter();
//                pw.write(serviceResult);
//                pw.flush();
//            } catch (IOException e) {
//                logger.error("业务层处理发生异常:"+e);
//            } finally {
//                if(pw != null) {
//                    pw.close();
//                    pw = null;
//                }
//            }
//        }
    }
	
	@RequestMapping(value="/del", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request, HttpServletResponse response) {
//        logger.info("收到参数:===cip====");
//        try {
//            String xRealIp = request.getHeader("X-Real-IP");
//            String xForwareIp = request.getHeader("X-Forwarded-For");
//            String remoteIp = request.getRemoteAddr();
//            System.out.println("xRealIp=" + xRealIp + ",xForwareIp=" + xForwareIp + ",remoteIp=" + remoteIp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String serviceResult="{\"key\":\"delete\"}";
//        if(serviceResult != null) {
//            response.setContentType(CommonService.CONTENT_TYPE_JSON);
//            PrintWriter pw = null;
//            try {
//                pw = response.getWriter();
//                pw.write(serviceResult);
//                pw.flush();
//            } catch (IOException e) {
//                logger.error("业务层处理发生异常:"+e);
//            } finally {
//                if(pw != null) {
//                    pw.close();
//                    pw = null;
//                }
//            }
//        }
	    logger.info("收到参数:===del====");
        System.out.println("=============del====================");
        Map<String, String[]> map = request.getParameterMap();
        for(Entry<String, String[]> e : map.entrySet()) {
            System.out.println(e.getKey() + "-----"+ Arrays.asList(e.getValue()));
        }
        try {
            logger.info("user=====>"+request.getSession().getAttribute("user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("result", "del");
        CommonService.commonService(response, result);
    }
	 
//    @RequestMapping("/cip")
//    public void cip(HttpServletRequest request, HttpServletResponse response) {
//        logger.info("收到参数:===cip====");
//        try {
//            String xRealIp = request.getHeader("X-Real-IP");
//            String xForwareIp = request.getHeader("X-Forwarded-For");
//            String remoteIp = request.getRemoteAddr();
//            System.out.println("xRealIp=" + xRealIp + ",xForwareIp=" + xForwareIp + ",remoteIp=" + remoteIp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    //-------------------------------------Session start---------------------------
//	 @RequestMapping("/cookie1")
//	    public void cookie1(HttpServletRequest request, HttpServletResponse response,
//	            @RequestParam(value = "key", required = false) String key,
//	            @RequestParam(value = "pwd", required = false) String value) {
//	        logger.info("收到参数:===session====start");
//	        try {
//	            User user = new User();
//	            user.setUsername(key == null ? "test" : key);
//	            user.setPassword(value == null ? "value" : value);
//	            request.getSession().setAttribute("user", user);
//	            int lenght = request.set
//	            Cookie c = new Cookie();
//	            c.s
//	            // user.getUsername()
//	            logger.info("user=====>"+user);
//	            
//	            Map<String,Object> result = new HashMap<String,Object>();
//	            result.put("result", "session1");
//	            CommonService.commonService(response, result);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
    @RequestMapping("/session1")
    public void start(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "pwd", required = false) String value) {
        logger.info("收到参数:===session====start");
        try {
            User user = new User();
            user.setUsername(key == null ? "test" : key);
            user.setPassword(value == null ? "value" : value);
            request.getSession().setAttribute("user", user);
            // user.getUsername()
            logger.info("user=====>"+user);
            
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("result", "session1");
            CommonService.commonService(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/session2")
    public void end(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到参数:===session====end");
        try {
            logger.info("user=====>"+request.getSession().getAttribute("user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("result", "session2");
        CommonService.commonService(response, result);
    }
  //-------------------------------------Session end---------------------------
  //-------------------------------------body start---------------------------
    @RequestMapping("/body")
    public void body(HttpServletRequest request, HttpServletResponse response) {
        String body = "{\"name\":\"IluckySi\"}";
        try {
            logger.info("user=====>"+request.getSession().getAttribute("user"));
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String body = "{\"name\":\"IluckySi\"}";
        Map<String, Object> map = JSON.parseObject(body);
        System.out.println(map.get("name"));
    }
  //-------------------------------------body start---------------------------  
    
	/**
	 * AKIAJIFDXJYQQEGOUWIA
	 * cAxV/tSc3R1xDFGUggvLlliH34hYXUM6zAbMD/Z9
	 * 
	 * @param request
	 * @param response
	 */

	
	
	@RequestMapping("/sandbox")
    public void sandbox(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到参数:===sandbox====");
        Clock c = new BrokenClock();
        try {
            c.loopReport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // MyProjectTest.main(null);
        // com.test.MyProjectTest m = new com.test.MyProjectTest();
    }
	
   @RequestMapping("/batchsend")
    public void batchsend(HttpServletRequest request, HttpServletResponse response) {
       try {
           String rk = request.getHeader("RoutingKey");
           String ae = request.getHeader("ApiEnum");
           String dc = request.getHeader("DataCount");
           logger.info("收到参数:===0000000000===="+rk+"*"+ae+"*"+dc);
           
           StringBuffer sb = new StringBuffer();
           InputStream is = request.getInputStream();
           BufferedInputStream buf = new BufferedInputStream(is);
           byte[] buffer = new byte[1024];
           int iRead;
           while ((iRead = buf.read(buffer)) != -1) {
               sb.append(new String(buffer, 0, iRead, "utf-8"));
           }
           logger.info("收到数据"+sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	    
   
   //http://localhost:8080/ilucky-web-javaagent/test/normal.mvc
//   @RequestMapping("/nginx")
//   public void nginx(HttpServletRequest request, HttpServletResponse response) {
//       logger.info("收到参数:=======");
//   }
   
	//http://localhost:8080/ilucky-web-javaagent/test/normal.mvc
	@RequestMapping("/test")
	public void set(HttpServletRequest request, HttpServletResponse response) {
		logger.info("收到参数:=======");
	}
	
	@RequestMapping("/request")
    public void nginx(HttpServletRequest request, HttpServletResponse response) {
	    
	    Map<String, String[]> map = request.getParameterMap();
        for(Entry<String, String[]> e : map.entrySet()) {
            System.out.println(e.getKey() + "-----"+ Arrays.asList(e.getValue()));
        }
        System.out.println("=============request====================");
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("result", "request");
        CommonService.commonService(response, result);
//	    
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("request.getRequestURI()="+request.getRequestURI()+ "\r\n");
//	    sb.append("request.getRequestURL()="+request.getRequestURL()+ "\r\n");
//	    sb.append("request.getQueryString()="+request.getQueryString()+ "\r\n");
//	    sb.append("request.getScheme()="+request.getScheme()+ "\r\n");
//	    sb.append("request.getProtocol()="+request.getProtocol()+ "\r\n");
//	    sb.append("request.getServerName()="+request.getServerName()+ "\r\n");
//	    sb.append("request.getServerPort()="+request.getServerPort()+ "\r\n");
//	    sb.append("request.getServletPath()="+request.getServletPath()+ "\r\n");
//	    sb.append("request.getRemoteAddr()="+request.getRemoteAddr()+ "\r\n");
//	    sb.append("request.getRemoteHost()="+request.getRemoteHost()+ "\r\n");
//	    sb.append("request.getRemotePort()="+request.getRemotePort()+ "\r\n");
//        sb.append("request.getLocalAddr()="+request.getLocalAddr()+ "\r\n");
//        sb.append("request.getLocalPort()="+request.getLocalPort()+ "\r\n");
//        sb.append("request.getLocalName()="+request.getLocalName()+ "\r\n");
//        sb.append("request.getPathInfo()="+request.getPathInfo()+ "\r\n");
//        sb.append("request.getContextPath()="+request.getContextPath()+ "\r\n");
//        
//        sb.append("request.getCharacterEncoding()="+request.getCharacterEncoding()+ "\r\n");
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while(headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            sb.append("request.getHeaderNames()="+headerName+"#"+request.getHeader(headerName)+ "\r\n");
//        }
//	    String serviceResult = sb.toString();
	    
        
        
        
//	    if(serviceResult != null) {
//	        response.setContentType(CommonService.CONTENT_TYPE_JSON);
//	        PrintWriter pw = null;
//	        try {
//	            pw = response.getWriter();
//	            pw.write(serviceResult);
//	            pw.flush();
//	        } catch (IOException e) {
//	            logger.error("业务层处理发生异常:"+e);
//	        } finally {
//	            if(pw != null) {
//	                pw.close();
//	                pw = null;
//	            }
//	        }
//	    }
    }
	
	
	//-------------------------------start----------------------------------
	@RequestMapping("/test1") // 停顿3秒
    public void test1(HttpServletRequest request, HttpServletResponse response) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonService.commonService(response, "normal");
    }
	@RequestMapping("/test2") // 停顿5秒
    public void test2(HttpServletRequest request, HttpServletResponse response) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonService.commonService(response, "normal");
    }
   @RequestMapping("/test3") // 异常信息可以抓取到
    public void test3(HttpServletRequest request, HttpServletResponse response) {
        int i = 10;
        int j = 0;
        
        test322();
        
        System.out.println(i/j);
        CommonService.commonService(response, "normal");
    }
   
   public void test322() {
       try {
        Thread.sleep(4000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
   }
   
   @RequestMapping("/test32") // try catch:异常信息不能抓取到
   public void test32(HttpServletRequest request, HttpServletResponse response) {
       try {
           int i = 10;
           int j = 0;
           System.out.println(i/j);
           CommonService.commonService(response, "normal");
       } catch (Exception e){
           e.printStackTrace();
       }
   }
   /**
    * 注意:针对业务层面抛出的异常，必须配置pacakeg.include，否则trace不到异常。
    * @param request
    * @param response
    * @throws MyException
    */
   @RequestMapping("/test33") // 抛出自定义异常: 异常信息可以抓取到
   public void test33(HttpServletRequest request, HttpServletResponse response) throws MyException {
       try {
           int i = 10;
           int j = 0;
           System.out.println(i/j);
           CommonService.commonService(response, "normal");
       } catch (Exception e){
           throw new MyException("Hi girl!@#$% ^&*()~!Q@_+^&");
       }
   }
   @RequestMapping("/test34") // 抛出自定义异常: 异常信息可以抓取到
   public void test34(HttpServletRequest request, HttpServletResponse response) throws MyException {
       try {
           int i = 10;
           int j = 0;
           System.out.println(i/j);
           CommonService.commonService(response, "normal");
       } catch (Exception e){
           throw new MyException("Hi girl!", e);
       }
   }

    // 测试最大记录
    @RequestMapping("/test4")
    public void test4(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "c", required = true) int key,
            @RequestParam(value = "f", required = true) int f) {
        int i = 0;
        // 执行一个接口
        MyInterface m = new MyInterfaceImpl();
        m.sayHello();
        m.sayHello2();
        a();
        b();
        c();
        foreach(f);
        foreach2(f);
        digui(i, key);
        test322();
        test323();
        CommonService.commonService(response, "normal");
    }
    public void a(){}
    public void b(){} 
    public void c() {
        try {
         Thread.sleep(5000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
    }    
    public void test323() {
      try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }}
    public void foreach(int f) {
       for(int i=0; i<f; i++) {
           comeon2(i);
       }
    }
    public void foreach2(int f) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<f; i++) {
            comeon2(i);
        }
     }
    
    public void digui(int i, int c) {
        i++;
        if (c == i) {
            return;
        }
        digui(i, c);
    }
    
    @RequestMapping("/test5")
    public void test5(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "c", required = true) int key) {
        int i = 0;
        foreach(i, key);
        CommonService.commonService(response, "normal");
    }
    
    public void foreach(int i, int k) {
        for(int j=0;j<k;j++) {
            comeon();
            // comeon2();
        }
    }
    public void comeon() {
//        int i = 1;
//        int j = 0;
//        int k = i+j;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void comeon2(int i) {
        try {
            if(i == 30) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
	//-------------------------------end----------------------------------
	
	
	@RequestMapping("/normal")
	public void normal(HttpServletRequest request, HttpServletResponse response) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonService.commonService(response, "normal");
	}
	
	@RequestMapping("/exception")
	public void exception(HttpServletRequest request, HttpServletResponse response) {
		logger.info("===============exception========");
	/*	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int i = 0;
		System.out.println(10/i);
		CommonService.commonService(response, "exception");*/
		TestExe.testExec(response);
	}
	
	
	@RequestMapping("/throw")
	public void testThrows(HttpServletRequest request, HttpServletResponse response) throws MyException {
		logger.info("===============THROW========");
		TestExe.testThrows(response);
//		try {
//			logger.info("===============THROW========");
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		throw new MyException("IL");
		//CommonService.commonService(response, "exception");
	}
	
	@RequestMapping("/set")
	public void set2(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "value", required = true) String value) {
		logger.info("收到参数:key="+key+",value="+value);
		boolean set = MemcacheUtil.set(key, value);
		Object v = MemcacheUtil.get(key);
		testA(10L, 2, "ss"); //public-public
		tesPrivateA(10L, 2, "ss");// private-private
		testPublicA(10L, 2, "ss");// public-private
		testPrivateA2(10L, 2, "ss");// private-public
		/*new ChildPackageTest().testChild(10L, 2, "ss");
		new ParentPackageTest().testParent(10L, 2, "ss");*/
		new OtherPackageTest().testOther(10L, 2, "ss");
		CommonService.commonService(response, v);
		// return true;
	}
	
	public void testA(long a, int b, String c) {
	    try {
	        System.out.println("=============testA");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	    testB(a+b,c);
    }
	private void tesPrivateA(long a, int b, String c) {
        try {
            System.out.println("=============tesPrivateA");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testPrivateB(a+b,c);
    }
	
	private void testPrivateA2(long a, int b, String c) {
        try {
            System.out.println("=============testPrivateA2");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testB(a+b,c);
    }
	
	public void testPublicA(long a, int b, String c) {
        try {
            System.out.println("=============testApublicA");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testPrivateB2(a+b,c);
    }
	
	public void testB(long l, String c) {
        try {
            System.out.println("=============testB");
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.out.println(l+c);
    }
	private void testPrivateB(long l, String c) {
        try {
            System.out.println("=============testPrivateB");
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.out.println(l+c);
    }
	private void testPrivateB2(long l, String c) {
        try {
            System.out.println("=============testPrivateB2");
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.out.println(l+c);
    }

    @RequestMapping("/get")
	public void get(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		String value = MemcacheUtil.get(key);
		CommonService.commonService(response, value);
	}
	

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		boolean delete = MemcacheUtil.delete(key);
		CommonService.commonService(response, delete);
	}
	
	@RequestMapping("/append")
	public void append(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "value", required = true) String value) {
		logger.info("收到参数:key="+key);
		boolean delete = MemcacheUtil.append(key, value);
		CommonService.commonService(response, delete);
	}
	
	@RequestMapping("/prepend")
	public void prepend(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "value", required = true) String value) {
		logger.info("收到参数:key="+key);
		boolean delete = MemcacheUtil.prepend(key, value);
		CommonService.commonService(response, delete);
	}
	
	@RequestMapping("/replace")
	public void replace(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "value", required = true) String value) {
		logger.info("收到参数:key="+key);
		boolean delete = MemcacheUtil.replace(key, value);
		CommonService.commonService(response, delete);
	}
	
	@RequestMapping("/gets")
	public void gets(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
//		long value = MemcacheUtil.gets(key);MemcacheUtil.g
//		CommonService.commonService(response, value);
	}
	
	
	
//	@RequestMapping("/gets2")
//	public void gets2(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam(value = "key", required = true) String key) {
//		logger.info("收到参数:key="+key);
//		long value = MemcacheUtil.get
//		CommonService.commonService(response, value);
//	}
	
	@RequestMapping("/flushall")
	public void flushall(HttpServletRequest request, HttpServletResponse response) {
		MemcacheUtil.flushAll();
		CommonService.commonService(response, "true");
	}
	
	@RequestMapping("/version")
	public void version(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		boolean delete = MemcacheUtil.version(key);
		CommonService.commonService(response, delete);
	}
	
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "value", required = true) String value) {
		logger.info("收到参数:key="+key);
		boolean delete = MemcacheUtil.add(key, value);
		CommonService.commonService(response, delete);
	}
	
	@RequestMapping("/incr")
	public void incr(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		long incr = MemcacheUtil.incr(key, 10);
		CommonService.commonService(response, incr);
	}
	
	@RequestMapping("/decr")
	public void decr(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		long decr = MemcacheUtil.decr(key, 10);
		CommonService.commonService(response, decr);
	}
	
	//--------------------
	@RequestMapping("/touch")
	public void touch(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		boolean re = MemcacheUtil.touch(key, "10");
		CommonService.commonService(response, re);
	}
	
	@RequestMapping("/getAndTouch")
	public void getAndTouch(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		boolean re = MemcacheUtil.getAndTouch(key, "");
		CommonService.commonService(response, re);
	}
	
	@RequestMapping("/getStats")
	public void getStats(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key", required = true) String key) {
		logger.info("收到参数:key="+key);
		boolean e = MemcacheUtil.getStats(key, "");
		CommonService.commonService(response, e);
	}
}
