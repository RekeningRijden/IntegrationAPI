package jms;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import util.RoutingKeyFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSProducer {

    private Channel channel;
    private Connection connection;
    private String exchangeName;
    private String exchangeType;
    private String queue;

    public JMSProducer(String exchangeType, String queue) throws IOException, TimeoutException {
        this.exchangeType = exchangeType;
        this.queue = queue;
        this.exchangeName = queue;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5672);

        //Lokaal in docker
//        factory.setHost("192.168.99.100");
//        factory.setUsername("test");
//        factory.setPassword("test");
        //Productie
        factory.setHost("rabbitmq.seclab.marijn.ws");
        factory.setUsername("portugal");
        factory.setPassword("s63a");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, exchangeType);
        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchangeName, "PT");
    }

    /**
     * Creates a connection and declares the queue(s) and exchange and binds
     * them
     *
     * @param routingKeys The routingKeys for the routing
     * @param exchangeName The name of the exchange to send the message to
     * @param exchangeType The exchange type (e.g. direct, fanout, topic, etc.)
     * @param queueSuffix The suffix of the name of the queue
     * @throws IOException
     * @throws TimeoutException
     */
    public JMSProducer(HashMap<String, String> routingKeys, String exchangeName, String exchangeType, String queueSuffix) throws IOException, TimeoutException {
        this.exchangeType = exchangeType;
        this.exchangeName = exchangeName;
        this.queue = queueSuffix;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5672);

        //Lokaal in docker
//        factory.setHost("192.168.99.100");
//        factory.setUsername("test");
//        factory.setPassword("test");
        //Productie
        factory.setHost("rabbitmq.seclab.marijn.ws");
        factory.setUsername("portugal");
        factory.setPassword("s63a");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, exchangeType);

        //Setup routing for RabbitMQ
        for (HashMap.Entry<String, String> entry : routingKeys.entrySet()) {
            String queue = entry.getKey() + "_" + queueSuffix;
            channel.queueDeclare(queue, true, false, false, null);
            channel.queueBind(queue, exchangeName, entry.getValue());
        }
    }

    /**
     * Closes the connections
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    /**
     * Gets the routing key based on the param country and sends it to the
     * exchange with the routing key
     *
     * @param messagebody The body of the message to be sent
     * @param country The country for the message to be to send to
     */
    public void sendMessage(String messagebody, String country) {
        try {
            String routingKey = "no_key";
            if (exchangeType.equals("direct")) {
                RoutingKeyFilter routingKeyFilter = new RoutingKeyFilter();
                routingKey = routingKeyFilter.getRoutingKeyForCountry(country);
            }

            channel.basicPublish(exchangeName, routingKey, null, messagebody.getBytes());
            System.out.println("Sent message: " + messagebody + ", to exchange: " + exchangeName + ", with routing key: " + routingKey + " ( " + exchangeType + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getQueue() {
        return queue;
    }
}
