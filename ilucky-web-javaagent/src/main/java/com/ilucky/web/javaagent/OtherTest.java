package com.ilucky.web.javaagent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OtherTest {

	public static void main(String[] args) {
	    String sessionExpression = "getUsernmae()";
	    String[] sessionExpressions = sessionExpression.split("\\.");
	    System.out.println(Arrays.asList(sessionExpressions));
        for (String sessionExp : sessionExpressions){
            System.out.println(sessionExp.substring(0, sessionExp.length() -2));
        }
//		String s = "destTermID=-1\r\n" +
//				 "mac=00000000\r\n" +
//				 "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
//				 "<xmlMobile>" +
//				 "<tpName>seki.merIds</tpName>";
//		
//		String[] strArray = s.split("<")[0].split("\r\n");
//		//System.out.println(strArray.length);
//		StringBuilder sb = new StringBuilder();
//		for(String str : strArray) {
//			System.out.println(str);
//			//System.out.println("========"+sb);
//			sb.append(str+"&");
//		}
//		System.out.println(sb.toString());
		
//		String[] str = new String[]{"a", "b", "c", null, null, null, null, null, null, null};
//		final String[] str2 = str;
//		System.out.println(str[3]);
//		str[3] = "s";
//		System.out.println(str2[3]);
//	    try {
//            String s = IOUtils.toString(new FileInputStream(new File("D:\\yunzhihui\\javaagent\\性能优化\\data.txt")));
//            // System.out.println(s);
//            
//            // Object o = new JSONReader().read(s);
//            // System.out.println(o);
//            
////            List<Map<String,Object>> l  = (List<Map<String,Object>>)new JSONReader().read(s);
////            System.out.println(l.size());
//            
//            
//            
//            //-----------------
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode dataNode = objectMapper.readTree(s);
////            System.out.println(dataNode);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//	    
	    
	}

}
