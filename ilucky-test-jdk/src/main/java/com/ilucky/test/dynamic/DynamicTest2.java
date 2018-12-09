package com.ilucky.test.dynamic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.ilucky.test.dynamic.test2.Person;
import com.ilucky.test.dynamic.test2.TestService;

public class DynamicTest2 {

    public static void main(String[] args) {
        try {
            TestService testService = new TestService();
            Person person = new Person();
            person.setName("IluckySi");
            person.setAge(30);
            person.setSex(true);
            
            Map<String,Object> map=new HashMap<String,Object>();  
            map.put("testService", testService);  
            map.put("person",person);  
            String expression="testService.save(person)";  
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
