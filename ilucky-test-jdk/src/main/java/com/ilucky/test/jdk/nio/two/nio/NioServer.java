package com.ilucky.test.jdk.nio.two.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author IluckySi
 * @date 20140618
 */
public class NioServer {
	
	private Selector selector;
	
	private ServerSocketChannel serverSocketChannel;

	public static void main(String[] args) throws IOException {
		//创建服务器.
		NioServer server = new NioServer(9911);
		//启动服务器.
		server.start();
	}
	
	public NioServer(int port) throws IOException {
		//创建选择器.
		selector = Selector.open();
		//创建通道.
		serverSocketChannel = ServerSocketChannel.open();
		//创建服务器.
		ServerSocket serverSocket = serverSocketChannel.socket();
		//为服务器绑定监听端口.
		serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
		//设置通道为非阻塞模式.
		serverSocketChannel.configureBlocking(false);
		//为通道注册选择器,并设置其支持Accept操作.
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void start() {
		System.out.println("Nio Server启动成功!");
		try {
			while (true) {
				//当通道上有数据到来时继续执行,否则会一直阻塞.
				selector.select();
				//遍历所有注册过选择器的通道.
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = (SelectionKey) iterator.next();
					//删除SelectionKey,防止重复处理.
					iterator.remove();
					//分发SelectionKey,进行业务处理.
					dispatch(selectionKey);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dispatch(SelectionKey selectionKey) {
		//判断SelectionKey对应的通道是否支持ACCEPT操作.
		if (selectionKey.isAcceptable()) {
			try {
				//获取客户端连接的通道.
				ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
				SocketChannel socketChannel = (SocketChannel) serverSocketChannel.accept();
				//设置通道为非阻塞模式.
				socketChannel.configureBlocking(false);
				//在客户端连接成功后,为了可以接收到客户端发送过来的消息,需要为通道注册读权限.
				socketChannel.register(selector, SelectionKey.OP_READ);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		//判断SelectionKey对应的通道是否支持READ操作.
		if (selectionKey.isReadable()) {
			try {
				SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int bytesRead = socketChannel.read(buffer);
				if (bytesRead > 0) {
					buffer.flip();
					//将ByteBuffer转化为为UTF-16编码的字符串.
					String message = Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
					System.out.println("接收到来自客户端"+ socketChannel.socket().getRemoteSocketAddress() + "的消息: "+ message);
					//将字符串转化为为UTF-16编码的ByteBuffer.
					buffer = ByteBuffer.wrap("success".getBytes("UTF-16"));
					//向客户端写回消息.
					socketChannel.write(buffer);
				}
			} catch (IOException e) {
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
