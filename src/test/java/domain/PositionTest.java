package domain;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by martijn on 26-6-2016.
 */
public class PositionTest {

    @Test
    public void testGettersAndSetters() {
        Position position = new Position();
        Date date = new Date();

        /* Date */
        position.setDate(date);
        assertSame(date, position.getDate());

        /* Longtitude */
        position.setLongitude(Double.valueOf("1.0"));
        assertEquals(Double.valueOf("1.0"), Double.valueOf(position.getLongitude().toString()));

        /* Latitude */
        position.setLatitude(Double.valueOf("1.0"));
        assertEquals(Double.valueOf("1.0"), Double.valueOf(position.getLatitude().toString()));
    }

    @Test
    public void testConstructor() {
        Date date = new Date();
        Position position = new Position(date, Double.parseDouble("1.0"), Double.parseDouble("1.0"));

        assertSame(date, position.getDate());
        assertEquals(Double.parseDouble("1.0"), Double.parseDouble(position.getLongitude().toString()), 0.1);
        assertEquals(Double.parseDouble("1.0"), Double.parseDouble(position.getLatitude().toString()), 0.1);
    }
}
