package util;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.naming.CommunicationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Eric on 17-06-16.
 */
//@Stateless
//@Startup
public class RoutingKeyFilter {

    private HashMap<String, List<String>> indexedCountries;


    //@PostConstruct
    public void init() {
        try {
            indexedCountries = new HashMap<>();
            String jsonString = getJsonFromUrl("http://restcountries.eu/rest/v1/all");

            Gson gson = new Gson();
            List<Country> countries = gson.fromJson(jsonString, new TypeToken<List<Country>>(){}.getType());
            System.out.println("Nr of countries: " + countries.size());

            for(Country country : countries) {
                country.organize();
                indexedCountries.put(country.getAlpha2Code(), country.getCountryNames());
            }


        } catch (CommunicationException | IOException e) {
            e.printStackTrace();
        }
    }


    public String getJsonFromUrl(String url) throws IOException, CommunicationException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);

        HttpResponse response = httpClient.execute(get);
        checkResponse(response);

        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }

    public void checkResponse(HttpResponse response) throws CommunicationException {
        if (response == null) {
            throw new CommunicationException("Response was null");
        }

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new CommunicationException("Response did not have statusCode 200, instead the code was: " + statusCode);
        }
    }

    public HashMap<String, List<String>> getIndexedCountries() {
        return indexedCountries;
    }
}
