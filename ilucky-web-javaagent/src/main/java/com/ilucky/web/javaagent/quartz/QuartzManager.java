package com.ilucky.web.javaagent.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager{  
	
    private static final SimpleTrigger CronTrigger = null;  
  
    public static void main(String[] args){  
    	simpleDemo();
    }  
      
    public  static void simpleDemo(){  
          //通过SchedulerFactory来获取一个调度器  
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
        Scheduler scheduler;  
        try {  
            scheduler = schedulerFactory.getScheduler();  

        //引进作业程序  
        JobDetail jobDetail =  new JobDetail("jobDetail-s1", "jobDetailGroup-s1", QuartzDemo.class);  
  
         //new一个触发器  
        SimpleTrigger simpleTrigger =  new SimpleTrigger("simpleTrigger", "triggerGroup-s1");  
  
        //设置作业启动时间  
        long ctime = System.currentTimeMillis();   
        simpleTrigger.setStartTime(new Date(ctime));  
  
  
        //设置作业执行间隔   
        simpleTrigger.setRepeatInterval(1000);  
  
        //设置作业执行次数  
        simpleTrigger.setRepeatCount(10);  
  
        //设置作业执行优先级默认为5  
        //simpleTrigger.setPriority(10);  
  
        //作业和触发器设置到调度器中  
        scheduler.scheduleJob(jobDetail, simpleTrigger);  
          
        //启动调度器  
        scheduler.start();  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
}