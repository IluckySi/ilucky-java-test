package com.ilucky.test.yunzhihui.test2;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 数据进一步处理并发送
 */
public class DataSender implements Runnable {

    static AtomicLong c = new AtomicLong(0);
    long s;
    long e;
    HashMap<String, long[]> cache = new HashMap<String,long[]>();
    
    public DataSender(long s, long e, HashMap<String, long[]> cache)  {
        this.s=s;
        this.e=e;
        this.cache = cache;
    }
    
    public void run() {
        System.out.println("cache time="+(e-s)+",size="+cache.size());
        for(Entry<String, long[]> entry : cache.entrySet()) {
            long [] l = entry.getValue();
            // System.out.println(entry.getKey()+"----"+l[0]+"---"+l[1]+"---"+l[2]+"---"+l[3]);
            c.addAndGet(l[3]);
        }
    }
}
