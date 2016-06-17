package util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ejb.Singleton;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Eric on 17-06-16.
 */
public class RoutingKeyFilter {

    private HashMap<String, List<String>> indexedCountries;


    public RoutingKeyFilter() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("main/webapp/countries.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Gson gson = new GsonBuilder().create();
            List<Country> countries = gson.fromJson(jsonObject.toJSONString(), new TypeToken<List<Country>>() {
            }.getType());
            for(Country country : countries) {
                System.out.println(country.getName().getCommon());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
