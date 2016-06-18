package jms;

import javax.inject.Inject;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSHandler {

    public JMSHandler(){}

    public void handleMessage(String message, String queueName, JMSProducer producer) {
        System.out.println("Message: " + message + ", on queue: " + queueName);
    }
}
