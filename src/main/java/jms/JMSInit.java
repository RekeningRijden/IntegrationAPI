package jms;

import domain.ApiKey;
import service.ApiKeyService;

import javax.annotation.PostConstruct;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 17-06-16.
 */
//@ApplicationScoped
@Singleton
@Startup
public class JMSInit {

    @Inject
    private ApiKeyService apiKeyService;
    private final String EXCHANGE_TYPE = "direct";

    public void createConsumersAndProducers() {
        HashMap<String, String> keys = apiKeyService.getCountriesWithAbbrs();

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

                JMSProducer producer = new JMSProducer(keys, exchangeName, EXCHANGE_TYPE, queueNameSuffix);
                JMSConsumer consumer = new JMSConsumer(queueName, producer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}
