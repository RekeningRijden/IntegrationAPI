package service;

import dao.ApiKeyDao;
import domain.ApiKey;
import javax.ejb.Stateless;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author Sam
 */
@Stateless
public class ApiKeyService extends ApiKeyDao implements Serializable {

    @Override
    protected Class<ApiKey> getEntityClass() {
        return ApiKey.class;
    }

    public HashMap<String, String> getCountriesWithAbbrs() {
        HashMap<String, String> resultMap = new HashMap<>();
        List<ApiKey> keys = this.getAll();
        for(ApiKey key : keys) {
            if(!key.getCountry().toLowerCase().equals("portugal")) {
                resultMap.put(key.getCountry().toLowerCase(), key.getAbbreviation().toUpperCase());
            }
        }
        return resultMap;
    }
}
