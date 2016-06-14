package resources;

import domain.Car;
import domain.Position;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.ApiKeyService;
import service.CarService;

@Path("/api")
@Named
public class ApiResources {

    @Inject
    private CarService carService;
    @Inject
    private ApiKeyService apiKeyService;

    /**
     * update a car
     * @param apiKey key for access
     * @param car to update
     */
    @POST
    @Path("/movements")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCar(@QueryParam("api_key") String apiKey, Car car) {
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            if (carService.getCarByIdentifier(car.getCarIdentifier()) != null) {
                Car c = carService.getCarByIdentifier(car.getCarIdentifier());
                c.setDriver(car.getDriver());
                c.setDriverAddress(car.getDriverAddress());
                c.setPositions(car.getPositions());
                carService.update(c);
            } else {
                carService.create(car);
            }
        }else{
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }
    
    /**
     * Gets random positions
     * @param apiKey key for access
     * @return list of positions
     */
    @GET
    @Path("/positions/_random")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Position> getPositions(@QueryParam("api_key") String apiKey) {
        return new ArrayList<>();
    }
    
    /**
     * Set a car as stolen
     * @param apiKey key for access
     * @param car which is stolen
     */
    @POST
    @Path("/stolen")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void setCarAsStolen(@QueryParam("api_key") String apiKey, Car car) {
        car.setStolen(true);
    }
    
    /**
     * Set car as unstolen
     * @param apiKey key for access
     * @param carIdentifier the identifier of the car
     */
    @DELETE
    @Path("/stolen")
    @Produces(MediaType.APPLICATION_JSON)
    public void setCarAsStolen(@QueryParam("api_key") String apiKey, @QueryParam("carIdentifier") String carIdentifier) {
        Car car = carService.getCarByIdentifier(carIdentifier);
        car.setStolen(false);
    }

}
