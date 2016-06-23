package jms;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import wrappers.InvoiceWrapper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSHandler {

    private SimpleDateFormat sdf;
    private Gson gson;

    public JMSHandler() {
        sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
        gson = new Gson();
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

                country = json.get("country").toString();
                JSONObject invoice = (JSONObject) json.get("invoice");
                JSONObject ownership = (JSONObject) invoice.get("ownership");
                JSONObject car = (JSONObject) ownership.get("car");

                String carIdentifier = car.get("cartrackerId").toString();
                String licencePlate = car.get("licencePlate").toString();
                BigDecimal totalAmount = BigDecimal.valueOf((Double) invoice.get("totalAmount")).setScale(2, BigDecimal.ROUND_HALF_UP);
                String dateString = invoice.get("period").toString();

                InvoiceWrapper invoiceWrapper = new InvoiceWrapper(carIdentifier, licencePlate, totalAmount.doubleValue(), getFormatedDate(dateString));
                message = gson.toJson(invoiceWrapper);
            }

            producer.sendMessage(message, country);

        } catch (ParseException ex) {
            Logger.getLogger(JMSHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(JMSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFormatedDate(String dateString) throws java.text.ParseException {
        Date date = sdf.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if(month.length() == 1) {
            month = "0" + month;
        }

        return cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
    }
}