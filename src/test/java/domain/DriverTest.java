package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by martijn on 26-6-2016.
 */
public class DriverTest {

    @Test
    public void testGettersAndSetters() {
        Driver driver = new Driver();

        /* Id */
        driver.setId(1L);
        assertSame(1L, driver.getId());

        /* Firstname */
        driver.setFirstName("testFirstname");
        assertSame("testFirstname", driver.getFirstName());

        /* Lastname */
        driver.setLastName("testLastname");
        assertSame("testLastname", driver.getLastName());
    }
}
