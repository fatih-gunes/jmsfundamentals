package com.gunessoftware.jms.basics;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstTopic {

    public static void main(String[] args) throws Exception {
        InitialContext initialContext = null;
        Connection connection = null;

        initialContext = new InitialContext();
        Topic topic = (Topic) initialContext.lookup("topic/myTopic");
        ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
        connection = cf.createConnection();

        Session session = connection.createSession();
        MessageProducer producer = session.createProducer(topic);

        MessageConsumer consumer1 = session.createConsumer(topic);
        MessageConsumer consumer2 = session.createConsumer(topic);
        MessageConsumer consumer3  = session.createConsumer(topic);

        TextMessage message = session.createTextMessage("All the power is with in me. I can do anything and everything.");
        System.out.println("Producer message: " + message.getText());

        producer.send(message);

        connection.start();

        TextMessage message1 = (TextMessage) consumer1.receive();
        System.out.println("Consumer 1 message received: " + message1.getText());
        TextMessage message2 = (TextMessage) consumer2.receive();
        System.out.println("Consumer 2 message received: " + message2.getText());
        TextMessage message3 = (TextMessage) consumer3.receive();
        System.out.println("Consumer 3 message received: " + message3.getText());


        connection.close();
        initialContext.close();


    }
}
