package domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by martijn on 26-6-2016.
 */
public class DriverAddressTest {

    @Test
    public void testGettersAndSetters() {
        DriverAddress driverAddress = new DriverAddress();

        /* Id */
        driverAddress.setId(1L);
        assertSame(1L, driverAddress.getId());

        /* Street */
        driverAddress.setStreet("street");
        assertSame("street", driverAddress.getStreet());

        /* Street Number */
        driverAddress.setStreetNr("1");
        assertSame("1", driverAddress.getStreetNr());

        /* Zipcode */
        driverAddress.setZipcode("1343BA");
        assertSame("1343BA", driverAddress.getZipcode());

        /* City */
        driverAddress.setCity("Porto");
        assertSame("Porto", driverAddress.getCity());

        /* Country */
        driverAddress.setCountry("Portugal");
        assertSame("Portugal", driverAddress.getCountry());
    }
}
