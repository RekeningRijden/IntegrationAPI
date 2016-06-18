package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.naming.CommunicationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Eric on 18-06-16.
 */

/**
 * Class for getting and parsing the json containing the names of the countries in different languages
 */
public class CountryObtainer {

    private static CountryObtainer instance = null;

    private HashMap<String, List<String>> indexedCountries;

    private CountryObtainer() {
        // Exists only to defeat instantiation.
        indexedCountries = new HashMap<>();
        try {
            String jsonString = getJsonFromUrl("http://restcountries.eu/rest/v1/all");

            Gson gson = new Gson();
            List<Country> countries = gson.fromJson(jsonString, new TypeToken<List<Country>>(){}.getType());

            for(Country country : countries) {
                country.organize();
                indexedCountries.put(country.getAlpha2Code(), country.getCountryNames());
            }

            System.out.println("Size of map: " + indexedCountries.size());


        } catch (CommunicationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static CountryObtainer getInstance() {
        if(instance == null) {
            instance = new CountryObtainer();
        }
        return instance;
    }

    /**
     * Gets the json from the url
     * @param url The url where the json is located
     * @return The response in json/string format
     * @throws IOException
     * @throws CommunicationException
     */
    private String getJsonFromUrl(String url) throws IOException, CommunicationException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);

        HttpResponse response = httpClient.execute(get);
        checkResponse(response);

        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }

    /**
     * Checks if the response has statuscode 200 (OK)
     * @param response The response that gets checked
     * @throws CommunicationException
     */
    private void checkResponse(HttpResponse response) throws CommunicationException {
        if (response == null) {
            throw new CommunicationException("Response was null");
        }

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new CommunicationException("Response did not have statusCode 200, instead the code was: " + statusCode);
        }
    }

    /**
     *
     * @return Returns a HashMap based on the json
     */
    public HashMap<String, List<String>> getIndexedCountries() {
        return indexedCountries;
    }
}
