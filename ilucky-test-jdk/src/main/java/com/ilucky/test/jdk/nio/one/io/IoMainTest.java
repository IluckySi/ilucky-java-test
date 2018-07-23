package com.ilucky.test.jdk.nio.one.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author IluckySi
 * @date 20140618
 */
public class IoMainTest {

	public static void main(String[] args) {
	    File fileRead = new File("D:/fileRead.txt");  
	    File fileWrite = new File("D:/fileIOWrite.txt");  
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    try {  
	    	fis = new FileInputStream(fileRead);
	    	bis = new BufferedInputStream(fis);
	        fos = new FileOutputStream(fileWrite);  
	        bos = new BufferedOutputStream(fos);
	        byte[] buffer=new byte[10240];  
	        int length = -1;
	        long start =  System.currentTimeMillis();
	        //length = bis.read(buffer)意思是读取10KB字节放入缓存并返回读到字节的长度.
	        while((length = bis.read(buffer))!=-1){  
	            bos.write(buffer, 0, length);
	            bos.flush();
	        }  
	        long end = System.currentTimeMillis();
	        System.out.println("本次IO操作耗时: " + (end - start) + "毫秒!");
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  finally {
    		try {
    			if(bos != null) {
					bos.close();
					bos =null;
    			}
    			if(fos != null) {
    				fos.close();
    				fos = null;
    			}
    			if(bis != null) {
    				bis.close();
    				bis = null;
    			}
    			if(fis != null) {
    				fis.close();
    				fis = null;
    			}
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
}
/**
输出结果(注意文件大小是291MB, 每次读取10KB, 共输出5次结果):
本次IO操作耗时: 940毫秒!
本次IO操作耗时: 861毫秒!
本次IO操作耗时: 925毫秒!
本次IO操作耗时: 904毫秒!
本次IO操作耗时: 962毫秒!
 */
/**
输出结果(注意文件大小是1.02G, 每次读取10KB, 共输出3次结果):
本次IO操作耗时: 34880毫秒!
本次IO操作耗时: 29267毫秒!
本次IO操作耗时: 31435毫秒!
 */
/**
 io分析:
1.首先,正确理解byte[] buffer=new byte[10240]; 这相当于一个缓冲区, 即每次读取10KB放到缓存中,
   因为java中输入输出流类都是按单字节的读写方式进行IO操作的,也就是说每次读写一个字节的数据,
   这样很大程度上影响了系统的性能,因此,java又专门提供了高IO效率的缓冲类(这里是通过装饰者模式添加的),
   每次读写一个缓冲区的数据,这样很大程度上提高了系统的性能.
2.其次,write(byte[] b, int offset, int length)中的offset是指字节数组的偏移量.
   在网上的一些资料中经常看到这样的写法write(buffer),这样写是有问题的,
   经过多次测试,这么写每次write后的文件都比源文件大一些,究其原因,是因为读到最后一次时如果不够10KB,
   他也会按10KB写到目的文件中.
3.然后是flush方法,flush是把缓冲区中的内容全部写到文件上,如果没有他,可能有很多内容还存在于缓存中,即内容还有丢失的可能.
4.最后是close方法,close是文件完成真正的标志,通过调用close方法,来表示目的文件是一个完整的文件.
 */
