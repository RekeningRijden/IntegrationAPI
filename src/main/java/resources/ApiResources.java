package resources;

import domain.Car;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import service.CarService;

@Path("/")
@Named
public class ApiResources {

    @Inject
    private CarService carService;

    @POST
    @Path("/movements")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateCar(@QueryParam("api_key") String apiKey, Car car) {
        if (apiKey.equals("testKey")) {
            if (carService.getCarByIdentifierId(car.getCarIdentifier()) != null) {

            } else {
                carService.create(car);
            }
        }else{
            
        }
    }

}
