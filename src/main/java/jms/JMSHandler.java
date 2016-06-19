package jms;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSHandler {

    public JMSHandler() {
    }

    /**
     * Transforms and routes the messages if needed
     *
     * @param message       The received message via the consumer
     * @param fromQueueName The name of the queue where the message came from
     * @param producer      The producer that sends the message to the right queue
     */
    public void handleMessage(String message, String fromQueueName, JMSProducer producer) {
        System.out.println("Message: " + message + ", on queue: " + fromQueueName);

        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(message);
            String country = "";

            if (fromQueueName.equals("portugal_car_exit")) {
                country = json.get("country").toString();

            } else if (fromQueueName.equals("portugal_car_stolen_movement")) {
                country = json.get("country").toString();

            } else if (fromQueueName.equals("portugal_car_stolen")) {

            } else if (fromQueueName.equals("portugal_foreign_invoice")) {
                JSONObject driverAddress = (JSONObject) json.get("driverAddress");
                country = driverAddress.get("country").toString();
            }

            producer.sendMessage(message, country);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}