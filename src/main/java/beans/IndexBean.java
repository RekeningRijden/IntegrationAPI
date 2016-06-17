/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import domain.ApiKey;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.ApiKeyService;

/**
 *
 * @author maikel
 */
@Named
@ViewScoped
public class IndexBean implements Serializable {
    
    @Inject
    private ApiKeyService apiKeyService;
    
    public void init() {
        if(apiKeyService.getAll().isEmpty()){
            apiKeyService.create(new ApiKey("Belgie", "118c7fe4b4fa71b9d4a37f39666411f2", "BE"));
            apiKeyService.create(new ApiKey("Nederland", "4846339f0926558a03ae223e9635a5e6", "NL"));
            apiKeyService.create(new ApiKey("Luxemburg", "860bbaefa1c880cfbf8f33d5799a718a", "LUX"));
            apiKeyService.create(new ApiKey("Duitsland", "40878cf16cf4dfb681ce4e15665c75b7", "DUI"));
            apiKeyService.create(new ApiKey("Portugal", "s63a", "POR"));
        }
    }

}
