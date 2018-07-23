package com.ilucky.test.jdk.nio.two.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author IluckySi
 * @date 20140618
 */
public class IoServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9911);
			System.out.println("Io Server启动成功!");
			while (true) {
				Socket socket = null;
				PrintWriter writer = null;
				BufferedReader reader = null;
				try {
					// 如果没有消息阻塞在这里.
					socket = serverSocket.accept();
					writer = new PrintWriter(socket.getOutputStream());
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println("接收到来自客户端"+ socket.getRemoteSocketAddress() + "的消息: "+ reader.readLine());
					writer.println("success!");
					writer.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(reader != null) {
						reader.close();
						reader = null;
					}
					if(writer != null) {
						writer.close();
						writer = null;
					}
					if(socket != null) {
						socket.close();
						socket = null;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
