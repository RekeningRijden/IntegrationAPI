package jms;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSConsumer {

    private JMSHandler handler;

    private Channel channel;

    private Connection connection;

    /**
     * Creates a connection and declares the queue and listens for message on that queue
     *
     * @param queueName The name of the queue
     * @param producer  The producer to forward the message to
     * @throws IOException
     * @throws TimeoutException
     */
    public JMSConsumer(final String queueName, final JMSProducer producer) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //Lokaal in docker
        //factory.setHost("192.168.99.100");
        //factory.setUsername("test");
        //factory.setPassword("test");

        //Productie
        factory.setHost("rabbitmq.seclab.marijn.ws");
        factory.setUsername("portugal");
        factory.setPassword("s63a");

        connection = factory.newConnection();
        channel = connection.createChannel();

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

    /**
     * Closes the open connections
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
