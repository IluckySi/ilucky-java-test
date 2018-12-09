//package com.ilucky.web.javaagent.activemq;
//
//import java.util.Enumeration;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.MessageListener;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.jms.Topic;
//
//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//public class Comsumer {
//
//	public void init(){
//		new Thread(new Runnable(){
//
//			public void run() {
//				// TODO Auto-generated method stub
//				init1("Client1Queue");
//			}
//
//		}).start();
//		new Thread(new Runnable(){
//
//			public void run() {
//				// TODO Auto-generated method stub
//				init1("Client2Queue");
//			}
//
//		}).start();
//		new Thread(new Runnable(){
//
//			public void run() {
//				// TODO Auto-generated method stub
//				init1("Client3Queue");
//			}
//
//		}).start();
//
//		new Thread(new Runnable(){
//
//			public void run() {
//				// TODO Auto-generated method stub
//				init1("Client4Queue");
//			}
//
//		}).start();
//	}
//
//	public void init1(final String queueName) {
//        // ConnectionFactory ：连接工厂，JMS 用它创建连接
//        ConnectionFactory connectionFactory;
//        // Connection ：JMS 客户端到JMS Provider 的连接
//        Connection connection = null;
//        // Session： 一个发送或接收消息的线程
//        Session session;
//        // Destination ：消息的目的地;消息发送给谁.
//        Destination destination;
//        // 消费者，消息接收者
//        MessageConsumer consumer;
//        connectionFactory = new ActiveMQConnectionFactory(
//                /*ActiveMQConnection.DEFAULT_USER*/"admin",
//                /*ActiveMQConnection.DEFAULT_PASSWORD*/"admin", "tcp://10.0.3.153:61616");
//        try {
//            // 构造从工厂得到连接对象
//            connection = connectionFactory.createConnection();
//            // 启动
//            connection.start();
//            // 获取操作连接
//            session = connection.createSession(Boolean.FALSE,
//                    Session.AUTO_ACKNOWLEDGE);
//            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
//            destination = session.createQueue(queueName);
//            //Topic topic = session.createTopic("FirstTopic");
//            consumer = session.createConsumer(destination);
//            System.out.println("consumer : " + consumer);
//            System.out.println(queueName + " Receive Message Start.");
//            /*while (true) {
//                // 设置接收者接收消息的时间，为了便于测试，这里谁定为100s
//                TextMessage message = (TextMessage) consumer.receive(1000000);
//                if (null != message) {
//                	Enumeration e = message.getPropertyNames();
//                    while(e.hasMoreElements()){
//                    	String s = e.nextElement().toString();
//                    	System.out.println(s + " : " + message.getStringProperty(s));
//                    }
//
//                    System.out.println("Receive Message : " + message.getText());
//                } else {
//                	System.out.println("null.");
//                    break;
//                }
//            }*/
//
//            consumer.setMessageListener(new MessageListener() {
//
//                public void onMessage(Message message) {
//                    // TODO Auto-generated method stub
//                    if(message instanceof TextMessage){
//                        TextMessage textMessage = (TextMessage)message;
//                        try {
//                            System.out.println(queueName + " Receive Message : " + textMessage.getText());
//                        } catch (JMSException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//            //8、程序等待接收用户消息
//            System.in.read();
//            //9、关闭资源
//            consumer.close();
//            session.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != connection)
//                    connection.close();
//            } catch (Throwable ignore) {
//            }
//        }
//    }
//}
