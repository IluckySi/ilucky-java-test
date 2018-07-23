/*package com.ilucky.test.jdk8.util;

import java.util.ArrayList;
import java.util.List;

public class ForEachTest {  

public static void main(String[] args) {  
        List<String> list=new ArrayList<String>();  
        list.add("a");  
        list.add("b");  
        list.add("c");  
        list.add("d");  
        
        //传统方式进行外部迭代  
        for (String s : list) {  
            System.out.print(s);  
        }  
       
//        //java8内部迭代，用lambda处理  
        list.forEach(s ->System.out.print(s));  
        
        //java8进行并行流处理后迭代  
        list.parallelStream().forEach(s ->System.out.prinst(s));  
       
        //结果为abcdabcdcdba  
        //最后是并行处理，所以不是abcd了  
    }  
}  */