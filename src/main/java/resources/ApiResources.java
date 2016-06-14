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
import request.DeleteStolenRequest;
import request.MovementsRequest;
import request.StolenRequest;
import service.ApiKeyService;
import service.CarService;

@Path("/")
@Named
public class ApiResources {

    @Inject
    private CarService carService;
    @Inject
    private ApiKeyService apiKeyService;

    /**
     * update a car
     *
     * @param apiKey key for access
     * @param movementsRequest
     */
    @POST
    @Path("/movements")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCar(@QueryParam("api_key") String apiKey, MovementsRequest movementsRequest) {
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            if (carService.getCarByIdentifier(movementsRequest.getCarIdentifier()) != null) {
                Car c = carService.getCarByIdentifier(movementsRequest.getCarIdentifier());
                c.getDriver().setFirstName(movementsRequest.getDriver().getFirstName());
                c.getDriver().setLastName(movementsRequest.getDriver().getLastName());
                c.getDriverAddress().setCity(movementsRequest.getDriverAddress().getCity());
                c.getDriverAddress().setCountry(movementsRequest.getDriverAddress().getCountry());
                c.getDriverAddress().setStreet(movementsRequest.getDriverAddress().getStreet());
                c.getDriverAddress().setStreetNr(movementsRequest.getDriverAddress().getStreetNr());
                c.getDriverAddress().setZipcode(movementsRequest.getDriverAddress().getZipcode());
                c.setPositions(movementsRequest.getPositions());
                carService.update(c);
            } else {
                Car c = new Car();
                c.setCarIdentifier(movementsRequest.getCarIdentifier());
                c.setLicencePlate(movementsRequest.getLicencePlate());
                c.getDriver().setFirstName(movementsRequest.getDriver().getFirstName());
                c.getDriver().setLastName(movementsRequest.getDriver().getLastName());
                c.getDriverAddress().setCity(movementsRequest.getDriverAddress().getCity());
                c.getDriverAddress().setCountry(movementsRequest.getDriverAddress().getCountry());
                c.getDriverAddress().setStreet(movementsRequest.getDriverAddress().getStreet());
                c.getDriverAddress().setStreetNr(movementsRequest.getDriverAddress().getStreetNr());
                c.getDriverAddress().setZipcode(movementsRequest.getDriverAddress().getZipcode());
                c.setPositions(movementsRequest.getPositions());
                carService.create(c);
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

    /**
     * Gets random positions
     *
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
     *
     * @param apiKey key for access
     * @param stolenRequest
     */
    @POST
    @Path("/stolen")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void setCarAsStolen(@QueryParam("api_key") String apiKey, StolenRequest stolenRequest) {
        Car car = carService.getCarByIdentifier(stolenRequest.getCarIdentifier());
        if (car != null) {
            car.setStolen(true);
            carService.update(car);
        }
    }

    /**
     * Set car as unstolen
     *
     * @param apiKey key for access
     * @param stolenRequest
     */
    @DELETE
    @Path("/stolen")
    @Produces(MediaType.APPLICATION_JSON)
    public void setCarAsStolen(@QueryParam("api_key") String apiKey, DeleteStolenRequest stolenRequest) {
        Car car = carService.getCarByIdentifier(stolenRequest.getCarIdentifier());
        if (car != null) {
            car.setStolen(false);
            carService.update(car);
        }
    }

}
