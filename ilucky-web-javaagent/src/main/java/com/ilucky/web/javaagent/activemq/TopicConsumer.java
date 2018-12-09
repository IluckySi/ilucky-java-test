//package com.ilucky.web.javaagent.activemq;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.MessageListener;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.jms.Topic;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//public class TopicConsumer {
//
//	public void init() {
//        // ConnectionFactory ：连接工厂，JMS 用它创建连接
//        ConnectionFactory connectionFactory;
//        // Connection ：JMS 客户端到JMS Provider 的连接
//        Connection connection = null;
//        // Session： 一个发送或接收消息的线程
//        Session session;
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
//            Topic topic = session.createTopic("FirstTopic");
//            consumer = session.createConsumer(topic);//destination);
//            System.out.println("consumer : " + consumer);
//            System.out.println("session : " + session);
//            //7、向consumer对象中设置一个messageListener对象，用来接收消息
//            consumer.setMessageListener(new MessageListener() {
//
//                public void onMessage(Message message) {
//                    // TODO Auto-generated method stub
//                	System.out.println("onMessage : " + message);
//                    if(message instanceof TextMessage){
//                        TextMessage textMessage = (TextMessage)message;
//                        try {
//                            System.out.println("consumer message : " + textMessage.getText());
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
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//    }
//
//	public void init1(){
//		try{
//			//1、创建工厂连接对象，需要制定ip和端口号
//	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin", "tcp://10.0.3.153:61616");
//	        //2、使用连接工厂创建一个连接对象
//	        Connection connection = connectionFactory.createConnection();
//	        //3、开启连接
//	        connection.start();
//	        //4、使用连接对象创建会话（session）对象
//	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//	        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
//	        Topic topic = session.createTopic("FirstTopic");
//	        //6、使用会话对象创建生产者对象
//	        MessageConsumer consumer = session.createConsumer(topic);
//	        System.out.println("consumer : " + consumer);
//            System.out.println("session : " + session);
//	        //7、向consumer对象中设置一个messageListener对象，用来接收消息
//	        consumer.setMessageListener(new MessageListener() {
//
//	            public void onMessage(Message message) {
//	                // TODO Auto-generated method stub
//	            	System.out.println("onMessage : " + message);
//	                if(message instanceof TextMessage){
//	                    TextMessage textMessage = (TextMessage)message;
//	                    try {
//	                        System.out.println(textMessage.getText());
//	                    } catch (JMSException e) {
//	                        // TODO Auto-generated catch block
//	                        e.printStackTrace();
//	                    }
//	                }
//	            }
//	        });
//	        //8、程序等待接收用户消息
//	        System.in.read();
//	        //9、关闭资源
//	        consumer.close();
//	        session.close();
//	        connection.close();
//		}catch(Exception ex){
//
//		}
//	}
//}
