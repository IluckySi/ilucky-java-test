package com.ilucky.test.jdk.nio.two.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author IluckySi
 * @date 20140618
 */
public class NioClientOne {

	private Selector selector;

	private SocketChannel socketChannel;

	public static void main(String[] args) throws IOException {
		//模拟业务: 隔5秒钟向服务器发送一条消息.
		for (int i = 0; i < 5; i++) {
			NioClientOne client = new NioClientOne(9911);
			client.sendMessage("Hello! Nio Server, I am NioClientOne!");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public NioClientOne(int port) throws IOException {
		//创建选择器.
		selector = Selector.open();
		//创建通道.
		socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getLocalHost(), port));
		//设置通道为非阻塞模式.
		socketChannel.configureBlocking(false);
		//为通道注册选择器,并设置其支持READ操作
		socketChannel.register(selector, SelectionKey.OP_READ);
		//启动读取线程.
		new Thread(new NioClientReadThread()).start();
	}

	public void sendMessage(String message) throws IOException {
		//将字符串转化为为UTF-16编码的ByteBuffer.
		ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-16"));
		//向服务器写消息.
		socketChannel.write(writeBuffer);
	}

	class NioClientReadThread implements Runnable {
		public void run() {
			try {
				while (true) {
					//当通道上有数据到来时继续执行,否则会一直阻塞.
					selector.select();
					//遍历所有注册过选择器的通道.
					Set<SelectionKey> selectionKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = selectionKeys.iterator();
					while (iterator.hasNext()) {
						SelectionKey selectionKey = (SelectionKey)iterator.next();
						//删除SelectionKey,防止重复处理.
						selector.selectedKeys().remove(selectionKey);
						//分发SelectionKey,进行业务处理.
						dispatch(selectionKey);
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		public void dispatch(SelectionKey selectionKey) {
			//判断SelectionKey对应的通道是否支持READ操作.
			if (selectionKey.isReadable()) {
				try {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					socketChannel.read(buffer);
					buffer.flip();
					//将ByteBuffer转化为为UTF-16编码的字符串.
					String message = Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
					System.out.println("接收到来自服务器" + socketChannel.socket().getRemoteSocketAddress() + "的消息: "+ message);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					//操作结束后取消操作.
					selectionKey.cancel();
					if (selectionKey.channel() != null) {
						try {
							selectionKey.channel().close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}
}