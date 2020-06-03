package com.lys.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * MQ
 */
public class ActiveMqMain {

    // ActiveMq得点对点生产者
    @Test
    public void queueProducer() throws JMSException {
        // 1、创建一个连接工厂对象ConnectionFactory对象。需要指定mq服务得ip以及端口号61616。
        String brokerURL = "tcp://127.0.0.1:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        // 2、使用ConnectionFactory创建一个连接Connection对象。
        Connection connection = connectionFactory.createConnection();
        // 3、开启连接。调用Connection对象得start方法。
        connection.start();
        // 4、使用Connection对象创建一个Session对象。
        // 参数一是否开启事务，一般不开启事务，保证数据得最终一致性，可以使用消息队列实现数据最终一致性。如果第一个参数为true，第二个参数自动忽略
        // 参数二是消息得应答模式。两种模式，自动应答和手动应答。一般使用自动应答。
        boolean transacted = false;// 不开启事务
        int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;// 1
        Session session = connection.createSession(transacted, acknowledgeMode);
        // 5、使用Session对象创建一个Destination对象。两种形式queue、topic。现在应该使用queue。
        String queueName = "queue1";// 当前消息队列得名称
        Queue queue = session.createQueue(queueName);
        // 6、使用Session对象创建一个Producer对象。
        // interface Queue extends Destination。destination是一个接口。
        MessageProducer producer = session.createProducer(queue);
        // 7、创建一个TextMessage对象。
        // 创建TextMessage方式一
        // TextMessage textMessage = new ActiveMQTextMessage();
        // textMessage.setText("hello activeMq......");
        // 方式二
        TextMessage textMessage = session.createTextMessage("hello activeMq......");
        // 8、发送消息。
        producer.send(textMessage);
        // 9、关闭资源。
        producer.close();// 关闭producer
        session.close();// 关闭session
        connection.close();// 关闭connection
    }

    // activeMq的点对点消费者
    @Test
    public void queueConsumer() throws JMSException {
        // 1、创建一个连接工厂ConnectionFactory 对象
        String brokerURL = "tcp://192.168.110.142:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        // 2、使用连接工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        // 3、开启连接
        connection.start();
        // 4、使用连接对象创建一个Session对象
        boolean transacted = false;// 关闭事务
        int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;// 自动响应
        Session session = connection.createSession(transacted, acknowledgeMode);
        // 5、使用Session创建一个Destination，Destination应该和消息的发送端一致的。
        String queueName = "queue1";
        Queue queue = session.createQueue(queueName);
        // 6、使用Session创建一个Consumer对象。
        MessageConsumer consumer = session.createConsumer(queue);
        // 7、向Consumer对象中设置一个MessageListener对象，用来接受消息。
        // 匿名内部类，new 接口，后面加上{}，相当于实现了这个接口的实现类。然后创建这个实现类的对象listener。
        MessageListener listener = new MessageListener() {

            @Override
            public void onMessage(Message message) {
                // 接受事件的。当消息到达就可以在这里接受到消息了的。
                // 8、取出消息的内容。
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    // 9、打印消息内容。
                    try {
                        String text = textMessage.getText();
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        consumer.setMessageListener(listener);

        // 关闭资源以前，系统等待，等待接受消息。
                 /*while (true) {
110             try {
111                 Thread.sleep(100);
112             } catch (InterruptedException e) {
113                 e.printStackTrace();
114             }
115         }*/

        // 等待键盘输入。才回接着向下执行的。
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 10、关闭资源。
        consumer.close();// 关闭consumer
        session.close();// 关闭session
        connection.close();// 关闭connection
    }

}
