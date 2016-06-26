package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by martijn on 26-6-2016.
 */
public class ApiKeyTest {

    @Test
    public void testGettersAndSetters() {
        ApiKey apiKey = new ApiKey();

        /* Id */
        apiKey.setId(1L);
        assertSame(1L, apiKey.getId());

        /* Country */
        apiKey.setCountry("Portugal");
        assertSame("Portugal", apiKey.getCountry());

        /* Abbreviation */
        apiKey.setAbbreviation("Test");
        assertSame("Test", apiKey.getAbbreviation());

        /* ApiKey */
        apiKey.setApiKey("key");
        assertSame("key", apiKey.getApiKey());
    }

    @Test
    public void testConstructor() {
        ApiKey apiKey = new ApiKey("Portugal", "key", "test");

        assertSame("Portugal", apiKey.getCountry());
        assertSame("test", apiKey.getAbbreviation());
        assertSame("key", apiKey.getApiKey());
    }
}
