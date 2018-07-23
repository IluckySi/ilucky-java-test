package com.ilucky.test.jdk.nio.two.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author IluckySi
 * @date 20140618
 */
public class IoClientOne {

	public static void main(String[] args) {
		// 模拟业务: 隔5秒钟向服务器发送一条消息.
		for (int i = 0; i < 5; i++) {
			Socket socket = null;
			PrintWriter pw = null;
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				socket = new Socket("127.0.0.1", 9911);
				pw = new PrintWriter(socket.getOutputStream());
				is = socket.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				pw.println("Hello! Io Server, I am IoClientOne!");
				pw.flush();
				System.out.println("接收到来自服务器" + socket.getRemoteSocketAddress() + "的消息: " + br.readLine());
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(br != null) {
						br.close();
						br = null;
					}
					if(isr != null) {
						isr.close();
						isr = null;
					}
					if(is != null) {
						is.close();
						is = null;
					}
					if(pw != null) {
						pw.close();
						pw = null;
					}
					if(socket != null) {
						socket.close();
						socket = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
}
