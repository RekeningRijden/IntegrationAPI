package jms;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSProducer {

    private Channel channel;
    private String exchangeName;
    private HashMap<String, String> routingKeys;

    public JMSProducer(HashMap<String, String> routingKeys, String exchangeName, String exchangeType, String queueSuffix) throws IOException, TimeoutException {

        this.routingKeys = routingKeys;
        this.exchangeName =  exchangeName;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.99.100");
        factory.setPort(5672);
        factory.setUsername("test");
        factory.setPassword("test");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, exchangeType);

        //Setup routing for RabbitMQ
        for(HashMap.Entry<String, String> entry : routingKeys.entrySet()) {
            String queue = entry.getKey() + "_" + queueSuffix;
            channel.queueDeclare(queue , false, false, false, null);
            channel.queueBind(queue, exchangeName, entry.getValue());
        }
    }

    public void sendMessage(String messagebody) throws Exception {
        if (channel != null) {
            String message = "This is a message for testing routing";
            channel.basicPublish(exchangeName, messagebody, null, message.getBytes());
            System.out.println("Sent message: " + message + ". With routing key: " + messagebody);
        } else {
            throw new Exception("channel is null");
        }
    }
}
