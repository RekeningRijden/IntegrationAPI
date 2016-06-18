package util;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric on 17-06-16.
 */

/**
 * Helper class for getting information about countries (e.g. abbreviation for routing keys)
 */
public class RoutingKeyFilter {

    public RoutingKeyFilter() {}

    /**
     * Gets the routingkey based on the alpha2Code of a country (e.g. Belgium has alpha2Code BE)
     * @param countryName The name of the country
     * @return The alpha2Code based on the countryName or a String if no alpha2Code is found
     */
    public String getRoutingKeyForCountry(String countryName) {
        for(Map.Entry<String, List<String>> entry : CountryObtainer.getInstance().getIndexedCountries().entrySet()) {
            if (entry.getValue().contains(countryName)) {
                return entry.getKey();
            }
        }

        //Message is discarded, no routing key found
        return "not_existing_routingkey";
    }
}
