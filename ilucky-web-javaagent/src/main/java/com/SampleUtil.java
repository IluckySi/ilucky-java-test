package com;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author IluckySi
 *
 */
public class SampleUtil {

    private static int sample = 1;  // 采集n%的数据
    private static AtomicInteger flag = new AtomicInteger(0);
    
    public static boolean isSample() {
        if(flag.intValue() == 10)flag = new AtomicInteger(0);
        if (flag.incrementAndGet() <= sample) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        int count = 0;
        for(int i=0; i<1000000; i++) {
            if(isSample()) {
                count++; // 采集的数据
            }
        }
        System.out.println(count);
    }
}
