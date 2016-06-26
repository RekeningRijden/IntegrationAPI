package service;

import domain.Car;
import org.junit.Test;
import request.MovementsRequest;
import request.StolenRequest;

import static org.junit.Assert.*;

/**
 * Created by martijn on 26-6-2016.
 */
public class CarServiceTest {

    @Test
    public void testRequest() {
        CarService carService = new CarService();
        MovementsRequest movementsRequest = new MovementsRequest();
        StolenRequest stolenRequest = new StolenRequest();

        /* Movement Request to Car */
        Car car1 = carService.movementRequestToCar(movementsRequest,1L);
        assertSame(movementsRequest.getCarIdentifier(), car1.getCarIdentifier());
        assertSame(1L, car1.getCartrackerId());

        Car car2 = carService.movementRequestToCar(movementsRequest,car1);
        assertSame(movementsRequest.getCarIdentifier(), car2.getCarIdentifier());
        assertSame(1L, car2.getCartrackerId());

        /* Stolen Request to Car */
        Car car3 = carService.stolenRequestToCar(stolenRequest,2L);
        assertSame(stolenRequest.getCarIdentifier(), car3.getCarIdentifier());
        assertSame(2L, car3.getCartrackerId());
    }
}
