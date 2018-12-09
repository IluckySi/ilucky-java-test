package com.ilucky.web.javaagent.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
	      
		Object[] ts = new Object[]{"1","2"};
		Set<String> tidSet = new HashSet<String>();
		tidSet.add("1");
		tidSet.add("3");
		
          if(tidSet.size() > 0) {
          	if(ts != null && ts.length > 0) {
          		for(Object t : ts) {
          			if(t!=null)tidSet.add(t.toString());
          		}
          	}
          }
          System.out.println(tidSet);
	}
}
