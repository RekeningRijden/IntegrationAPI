package service;

import dao.ApiKeyDao;
import domain.ApiKey;
import javax.ejb.Stateless;

import java.io.Serializable;

/**
 * @author Sam
 */
@Stateless
public class ApiKeyService extends ApiKeyDao implements Serializable {

    @Override
    protected Class<ApiKey> getEntityClass() {
        return ApiKey.class;
    }
}
