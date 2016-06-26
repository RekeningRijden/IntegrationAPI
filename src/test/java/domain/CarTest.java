package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by martijn on 26-6-2016.
 */
public class CarTest {

    @Test
    public void testGettersAndSetters() {
        Car car = new Car();
        Driver driver = new Driver();
        DriverAddress driverAddress = new DriverAddress();
        List<Position> positions = new ArrayList<>();
        Position position = new Position();

        /* Id */
        car.setId(1L);
        assertSame(1L, car.getId());

        /* CartrackerId */
        car.setCartrackerId(2L);
        assertSame(2L, car.getCartrackerId());

        /* Car Identifier */
        car.setCarIdentifier("identifier");
        assertSame("identifier", car.getCarIdentifier());

        /* License Plate */
        car.setLicencePlate("33-BB-33");
        assertSame("33-BB-33", car.getLicencePlate());

        /* Stolen */
        car.setStolen(true);
        assertTrue(car.getStolen());

        /* Driver */
        car.setDriver(driver);
        assertSame(driver, car.getDriver());

        /* Driver Address */
        car.setDriverAddress(driverAddress);
        assertSame(driverAddress, car.getDriverAddress());

        /* positions */
        positions.add(position);

        car.setPositions(positions);
        assertSame(1, car.getPositions().size());
        assertSame(position, car.getPositions().get(0));

        /* Last position */
        car.setLastPosition(position);
        assertSame(position, car.getLastPosition());
    }

    @Test
    public void testConstructor() {
        Car car = new Car();

        assertNotNull(car.getDriver());
        assertNotNull(car.getDriverAddress());
        assertNotNull(car.getPositions());

        assertFalse(car.getStolen());
    }
}
