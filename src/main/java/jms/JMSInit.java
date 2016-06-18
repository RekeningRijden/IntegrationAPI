package jms;

import domain.ApiKey;
import service.ApiKeyService;
import util.RoutingKeyFilter;

import javax.annotation.PostConstruct;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Eric on 17-06-16.
 */

/**
 * Class for setting up everything needed for this webapp
 */
@Singleton
@Startup
public class JMSInit {

    @Inject
    private ApiKeyService apiKeyService;

    private List<JMSConsumer> consumers;
    private List<JMSProducer> producers;

    /**
     * Initiates the needed components for JMS, this method is called at startup
     */
    @PostConstruct
    public void init() {

        if(apiKeyService.getAll().isEmpty()){
            apiKeyService.create(new ApiKey("Belgie", "118c7fe4b4fa71b9d4a37f39666411f2", "BE"));
            apiKeyService.create(new ApiKey("Nederland", "4846339f0926558a03ae223e9635a5e6", "NL"));
            apiKeyService.create(new ApiKey("Luxemburg", "860bbaefa1c880cfbf8f33d5799a718a", "LU"));
            apiKeyService.create(new ApiKey("Duitsland", "40878cf16cf4dfb681ce4e15665c75b7", "DE"));
            apiKeyService.create(new ApiKey("Portugal", "s63a", "PT"));
        }
        createConsumersAndProducers();
    }

    /**
     * Creates the producers and consumers for this app
     */
    public void createConsumersAndProducers() {
        String EXCHANGE_TYPE_DIRECT = "direct";
        String EXCHANGE_TYPE_FANOUT = "fanout";
        consumers = new ArrayList<>();
        producers = new ArrayList<>();
        HashMap<String, String> keys = apiKeyService.getCountriesWithAbbrs();

        //Hashmap for the mapping between consumed queuename and the name of the exchange
        HashMap<String, String> queueProducerMapping = new HashMap<>();
        queueProducerMapping.put("portugal_car_exit", "car_movement");
        queueProducerMapping.put("portugal_car_stolen_movement", "car_stolen_movement");
        queueProducerMapping.put("portugal_car_stolen", "car_stolen");
        queueProducerMapping.put("portugal_foreign_invoice", "invoice");

        try {
            for(Map.Entry<String, String> entry : queueProducerMapping.entrySet()) {
                String queueNameSuffix = entry.getValue();
                String exchangeName = entry.getValue() + "_exchange";
                String queueName = entry.getKey();

                String EXCHANGE_TYPE = EXCHANGE_TYPE_DIRECT;

                //Only car_stolen_exchange is of type fanout
                if(exchangeName.equals("car_stolen_exchange")) {
                    EXCHANGE_TYPE = EXCHANGE_TYPE_FANOUT;
                }

                JMSProducer producer = new JMSProducer(keys, exchangeName, EXCHANGE_TYPE, queueNameSuffix);
                JMSConsumer consumer = new JMSConsumer(queueName, producer);

                consumers.add(consumer);
                producers.add(producer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes all open connections when the server shuts down or the app gets undeployed
     */
    @PreDestroy
    public void destroy() {
        for(JMSConsumer consumer : consumers) {
            try {
                consumer.closeConnection();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(JMSProducer producer : producers) {
            try {
                producer.closeConnection();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
