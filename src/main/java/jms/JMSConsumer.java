package jms;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSConsumer {

    private JMSHandler handler;

    public JMSConsumer(final String queueName, final JMSProducer producer) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //Lokaal in docker
//        factory.setHost("192.168.99.100");
//        factory.setUsername("test");
//        factory.setPassword("test");

        //Productie
        factory.setHost("rabbitmq.seclab.marijn.ws");
        factory.setUsername("portugal");
        factory.setPassword("s63a");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, true, false, false, null);

        System.out.println("Waiting for messages on queue: " + queueName);
        handler = new JMSHandler();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received message: " + message + " on queue " + queueName + " with routing key: " + envelope.getRoutingKey());

                handler.handleMessage(message, queueName, producer);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
