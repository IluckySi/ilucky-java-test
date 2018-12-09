package com.ilucky.web.javaagent.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ilucky.web.javaagent.custom.Test;

public class QuartzDemo implements Job{  
	  
    public void execute(JobExecutionContext arg0) throws JobExecutionException {  
        System.out.println("Quartz执行.......");  
        
        
        Test test =  new Test();
        test.test();
        test.test("sss");
        test.test(1, 2);
    }  
  
}  
