//package com.ilucky.web.javaagent.activemq;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.DeliveryMode;
//import javax.jms.Destination;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.jms.Topic;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//public class TopicProducer {
//
//	public void init() {
//        // ConnectionFactory ：连接工厂，JMS 用它创建连接
//        ConnectionFactory connectionFactory; // Connection ：JMS 客户端到JMS
//        // Provider 的连接
//        Connection connection = null;
//        // Session： 一个发送或接收消息的线程
//        Session session = null;
//        // Destination ：消息的目的地;消息发送给谁.
//        Destination destination;
//        // MessageProducer：消息发送者
//        MessageProducer producer = null;
//        // TextMessage message;
//        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
//        connectionFactory = new ActiveMQConnectionFactory(
//                /*ActiveMQConnection.DEFAULT_USER*/"admin",
//                /*ActiveMQConnection.DEFAULT_PASSWORD*/"admin", "tcp://10.0.3.153:61616");
//
//
//        try { // 构造从工厂得到连接对象
//        	connection = connectionFactory.createConnection();
//            // 启动
//            connection.start();
//            // 获取操作连接
//            session = connection.createSession(Boolean.TRUE,
//                    Session.AUTO_ACKNOWLEDGE);
//
//            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
//            Topic topic = session.createTopic("FirstTopic");
//            // 得到消息生成者【发送者】
//            producer = session.createProducer(topic);//destination);
//            // 设置不持久化，此处学习，实际根据项目决定
//            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//            // 构造消息，此处写死，项目就是参数，或者方法获取
//            System.out.println("producer : " + producer);
//            System.out.println("session : " + session);
//
//            //7、使用会话对象创建一个消息对象
//            TextMessage textMessage = session.createTextMessage("hello!test-topic");
//            //8、发送消息
//            System.out.println("producer message : hello!test-topic.");
//            producer.send(textMessage);
//            //9、关闭资源
//            producer.close();
//            session.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//    }
//}
