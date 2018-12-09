package com.ilucky.web.javaagent.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.log4j.Logger;

/**
 * @author IluckySi
 * @date 2016-12-18 上午11:58:10
 * @version 1.0
 */
public class MemcacheUtil {

	/**
	 * @param args
	 */
	private static Logger logger = Logger.getLogger(MemcacheUtil.class);
	private static String ip = "10.0.1.224";
	private static int port = 11211;
	private static String ipPort = "10.0.1.224:11211";
	private static String MEMCACHE_ADDR = "memcache.addr";
	
	public static void main(String[] args) {
		// test1();
		// test2();
		// test3();
		//testOther();
	    
	    set("hello", "Hello,xmemcached222");
	    System.out.println(get("hello"));
	}
	
	public static void init() {
		try {
			Properties p = new Properties();
			InputStream is = MemcacheUtil.class.getClassLoader().getResourceAsStream("core.properties");
			p.load(is);
			ipPort = p.getProperty(MEMCACHE_ADDR);
			logger.info("IP PORT = " + ipPort);
		} catch (Exception e) {
			logger.error("INIT OCCUR EXCEPTION :" + e.toString());
		}
	}

	public static void testOther() {
		String str = "10.0.1.224:11211(weight=1)";
		String[] ipPortWight = str.split(":");
		System.out.println("======" + ipPortWight[0]);
		System.out.println("======" + ipPortWight[1]);
		if(ipPortWight[1].contains("(")) {
			String[]  portWight = ipPortWight[1].split("\\(");
			System.out.println(portWight[0]);
		}
	}
	
	public static void test1() {
		try {
		    System.out.println("----------");
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("10.0.1.81:11211"));
			MemcachedClient memcachedClient = builder.build();
			memcachedClient.set("hello", 0, "Hello,xmemcached");
			memcachedClient.flushAll();
//			System.out.println("hello=" + memcachedClient.get("hello"));
//			
//			memcachedClient.prepend("hello", "dddd");
//			System.out.println("hello=" + memcachedClient.get("hello"));
//
//			memcachedClient.delete("hello");
//			String value = memcachedClient.get("hello");
//			System.out.println("hello=" + value);
//
//			
//			memcachedClient.shutdown();
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception");
			e.printStackTrace();
		}
	}
	
	public static void test3() {
		try {
			XMemcachedClient memcachedClient = new XMemcachedClient("10.0.1.224", 11211);
			memcachedClient.set("hello", 0, "Hello,xmemcached");
			memcachedClient.flushAll();
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception");
			e.printStackTrace();
		}
	}

	public static void test2() {
		try {
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient client = builder.build();
			client.flushAll();
			if (!client.set("hello", 0, "world")) {
				System.err.println("set error");
			}
			if (client.add("hello", 0, "dennis")) {
				System.err.println("Add error,key is existed");
			}
			if (!client.replace("hello", 0, "dennis")) {
				System.err.println("replace error");
			}
			client.append("hello", " good");
			client.prepend("hello", "hello ");
			String name = client.get("hello", new StringTranscoder());
			System.out.println(name);
			client.deleteWithNoReply("hello");
			
			System.out.println(client.get("hello", new StringTranscoder()));
			client.shutdown();
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception");
			e.printStackTrace();
		}
	}
	
	public static boolean set(String key, String value) {
		try {
			init();
//			System.out.println("========= XMemcachedClient memcachedClient = new XMemcachedClient(\"10.0.1.224\", 11211);==============");
			 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			 MemcachedClient memcachedClient = builder.build();
//			 XMemcachedClient memcachedClient = new XMemcachedClient("10.0.1.224", 11211);
			return memcachedClient.set(key, 0, value);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static String get(String key) {
		try {
			init();
//			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
//			MemcachedClient memcachedClient = builder.build();
	//		System.out.println("========= XMemcachedClient memcachedClient = new XMemcachedClient(\"10.0.1.224\", 11211);==============");
			 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			 MemcachedClient memcachedClient = builder.build();
//			 XMemcachedClient memcachedClient = new XMemcachedClient("10.0.1.224", 11211);
			return memcachedClient.get(key);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static boolean delete(String key) {
		try {
			init();
//			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
//			MemcachedClient memcachedClient = builder.build();
			System.out.println("========= XMemcachedClient memcachedClient = new XMemcachedClient(\"10.0.1.224\", 11211);==============");
//			 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
//			 MemcachedClient memcachedClient = builder.build();
			 XMemcachedClient memcachedClient = new XMemcachedClient("10.0.1.224", 11211);
			return memcachedClient.delete(key);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	////////////////////////////
	public static boolean add(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.add(key, 50, value);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static long incr(String key, long value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.incr(key, value);
		} catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}
	}
	
	public static long decr(String key, long value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.decr(key, value);
		} catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}
	}
	
	public static boolean cas(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			memcachedClient.cas(key, 1, value, 1);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static boolean append(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.append(key, value);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static boolean prepend(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.prepend(key, value);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	

	public static void flushAll() {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			memcachedClient.flushAll();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static long gets(String key) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.gets(key).getCas();
		} catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}
	}
	
	public static boolean version(String key) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.getVersions().isEmpty();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static void getStats(String key) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			memcachedClient.getStats();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static boolean replace(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.replace(key,10, value);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	/////////////////////////////////////////////////--------------新增
	public static boolean touch(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.touch(key, 1);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static boolean getAndTouch(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			return memcachedClient.getAndTouch(key, 1);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static boolean getStats(String key, String value) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			memcachedClient.getStats();
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
//	public static boolean getStats(String key, String value) {
//		try {
//			init();
//			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
//			MemcachedClient memcachedClient = builder.build();
//			return true;
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			return false;
//		}
//	}

	public static void getAuthInfoMap(String key) {
		try {
			init();
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ipPort));
			MemcachedClient memcachedClient = builder.build();
			memcachedClient.getAuthInfoMap();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
