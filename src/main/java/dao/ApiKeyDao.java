/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.ApiKey;
import javax.persistence.TypedQuery;


public abstract class ApiKeyDao extends AbstractDao<ApiKey> {

    public ApiKey getApiKeyByKey(String apiKey){
        TypedQuery<ApiKey> q = getEntityManager().createQuery("SELECT ak FROM ApiKey ak WHERE ak.apiKey = :apiKey", ApiKey.class)
                .setParameter("apiKey", apiKey);

        return oneResult(q);
    }
}
