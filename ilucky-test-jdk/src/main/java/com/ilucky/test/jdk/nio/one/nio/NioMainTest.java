package com.ilucky.test.jdk.nio.one.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author IluckySi
 * @date 20140618
 */
public class NioMainTest {

	public static void main(String[] args) {
	    File fileRead = new File("D:/fileRead.txt");  
	    File fileWrite = new File("D:/fileIOWrite.txt");  
	    FileInputStream fis = null;
	    FileChannel fic = null;
	    FileOutputStream fos = null;
	    FileChannel foc = null;
	    try {  
	    	fis = new FileInputStream(fileRead);
	    	fic = fis.getChannel();
	        fos = new FileOutputStream(fileWrite);  
	    	foc = fos.getChannel();
	        ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
	        long start =  System.currentTimeMillis();
	        //把通道的数据读入缓冲区.
	        while(fic.read(byteBuffer) != -1) {
            	byteBuffer.flip();  
            	//将缓冲区的数据写入另一个通道.
            	foc.write(byteBuffer);  
                byteBuffer.clear();  
            }
	        long end = System.currentTimeMillis();
	        System.out.println("本次IO操作耗时: " + (end - start) + "毫秒!");
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  finally {
    		try {
    			if(foc != null) {
    				foc.close();
    				foc =null;
    			}
    			if(fos != null) {
    				fos.close();
    				fos = null;
    			}
    			if(fic != null) {
    				fic.close();
    				fic = null;
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
本次IO操作耗时: 910毫秒!
本次IO操作耗时: 1163毫秒!
本次IO操作耗时: 937毫秒!
本次IO操作耗时: 997毫秒!
本次IO操作耗时: 900毫秒!
 */
/**
输出结果(注意文件大小是1.02G, 每次读取10KB, 共输出3次结果):
本次IO操作耗时: 37308毫秒!
本次IO操作耗时: 31083毫秒!
本次IO操作耗时: 35427毫秒!
*/

