/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send;

import com.google.gson.Gson;
import domain.Car;
import jms.JMSProducer;

/**
 *
 * @author maikel
 */
public class SendMovements {

    public SendMovements() {
    }
    
    public void send(JMSProducer producer, Car car){
        Gson gson = new Gson();       
        producer.sendMessage(gson.toJson(carToAdministrationCar(car)), "portugal");
    }
    
    private administrationDomain.Car carToAdministrationCar (Car car){
        administrationDomain.Car newCar = new administrationDomain.Car();
        newCar.setLicencePlate(car.getLicencePlate());
        newCar.setPositions(car.getPositions());
        
        administrationDomain.Address newAddress = new administrationDomain.Address();
        newAddress.setCity(car.getDriverAddress().getCity());
        newAddress.setCountry(car.getDriverAddress().getCountry());
        newAddress.setStreet(car.getDriverAddress().getStreet());
        newAddress.setStreetNr(car.getDriverAddress().getStreetNr());
        newAddress.setZipCode(car.getDriverAddress().getZipcode());
        
        administrationDomain.Driver newDriver = new administrationDomain.Driver();
        newDriver.setFirstName(car.getDriver().getFirstName());
        newDriver.setLastName(car.getDriver().getLastName());
        
        administrationDomain.Ownership newOwnership = new administrationDomain.Ownership();
        
        newDriver.setAddress(newAddress);
        newOwnership.setDriver(newDriver);
        newCar.setCurrentOwnership(newOwnership);
        return newCar;
    }
}
