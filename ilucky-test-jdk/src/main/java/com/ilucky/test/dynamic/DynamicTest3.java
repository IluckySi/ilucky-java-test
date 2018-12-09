package com.ilucky.test.dynamic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.ilucky.test.dynamic.test3.BodyParser;

/**
 * 
   Map<String, Object> json = JSON.parseObject(body);
   String result = json.get("UserName");
   return result;
            
 * @author IluckySi
 *
 */
public class DynamicTest3 {

    public static void main(String[] args) {
        try {
            // 模拟body
            String body = "{\"UserName\":\"IluckySi\"}";
            
            
            Map<String,Object> map=new HashMap<String,Object>();  
            map.put("bodyParser", new BodyParser());  
            map.put("body",body);  
            String expression="bodyParser.parse(body)";  
            Object code = convertToCode(expression, map);  
            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object convertToCode(String jexlExp, Map<String, Object> map) {
        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(jexlExp);
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        if (null == e.evaluate(jc)) {
            return "";
        }
        return e.evaluate(jc);
    }
}
