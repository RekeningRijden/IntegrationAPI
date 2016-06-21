package service;

import dao.CarDao;
import domain.Car;
import javax.ejb.Stateless;

import java.io.Serializable;
import request.MovementsRequest;

/**
 * @author Sam
 */
@Stateless
public class CarService extends CarDao implements Serializable {

    @Override
    protected Class<Car> getEntityClass() {
        return Car.class;
    }

    public Car movementRequestToCar(MovementsRequest movementsRequest, long cartrackerId) {
        Car car = new Car();
        car.setCartrackerId(cartrackerId);
        car.setCarIdentifier(movementsRequest.getCarIdentifier());
        car.setLicencePlate(movementsRequest.getLicencePlate());
        car.getDriver().setFirstName(movementsRequest.getDriver().getFirstName());
        car.getDriver().setLastName(movementsRequest.getDriver().getLastName());
        car.getDriverAddress().setCity(movementsRequest.getDriverAddress().getCity());
        car.getDriverAddress().setCountry(movementsRequest.getDriverAddress().getCountry());
        car.getDriverAddress().setStreet(movementsRequest.getDriverAddress().getStreet());
        car.getDriverAddress().setStreetNr(movementsRequest.getDriverAddress().getStreetNr());
        car.getDriverAddress().setZipcode(movementsRequest.getDriverAddress().getZipcode());
        car.setPositions(movementsRequest.getPositions());
        return car;
    }
    
    public Car movementRequestToCar(MovementsRequest movementsRequest, Car car) {
        car.getDriver().setFirstName(movementsRequest.getDriver().getFirstName());
        car.getDriver().setLastName(movementsRequest.getDriver().getLastName());
        car.getDriverAddress().setCity(movementsRequest.getDriverAddress().getCity());
        car.getDriverAddress().setCountry(movementsRequest.getDriverAddress().getCountry());
        car.getDriverAddress().setStreet(movementsRequest.getDriverAddress().getStreet());
        car.getDriverAddress().setStreetNr(movementsRequest.getDriverAddress().getStreetNr());
        car.getDriverAddress().setZipcode(movementsRequest.getDriverAddress().getZipcode());
        car.setPositions(movementsRequest.getPositions());
        return car;
    }
}
