package resources;

import domain.Car;
import domain.Position;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCar(@QueryParam("api_key") String apiKey, MovementsRequest movementsRequest) {
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            if (movementsRequest.getCarIdentifier() != null && !movementsRequest.getCarIdentifier().equals("")) {
                if (carService.getCarByIdentifier(movementsRequest.getCarIdentifier()) != null) {
                    Car c = carService.getCarByIdentifier(movementsRequest.getCarIdentifier());
                    carService.update(carService.movementRequestToCar(movementsRequest, c));
                } else {
                    carService.create(carService.movementRequestToCar(movementsRequest));
                }
                throw new WebApplicationException(Response.Status.OK);
            } else {
                throw new WebApplicationException(Response.Status.CONFLICT);
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
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            //TODO
            List<Position> positions = new ArrayList<>();
            Random r = new Random();
            for (int i = 0; i < r.nextInt(100) + 5; i++) {
                positions.add(new Position(new Date(), (r.nextDouble() * -360.0) + 180, (r.nextDouble() * -180.0) + 90));
            }
            return positions;
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

    /**
     * Set a car as stolen
     *
     * @param apiKey key for access
     * @param stolenRequest
     */
    @POST
    @Path("/stolen")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public void setCarAsStolen(@QueryParam("api_key") String apiKey, StolenRequest stolenRequest) {
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            Car car = carService.getCarByIdentifier(stolenRequest.getCarIdentifier());
            if (car != null) {
                car.setStolen(true);
                carService.update(car);
                throw new WebApplicationException(Response.Status.OK);
            } else {
                throw new WebApplicationException(Response.Status.CONFLICT);
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
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
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public void setCarAsStolen(@QueryParam("api_key") String apiKey, DeleteStolenRequest stolenRequest) {
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            Car car = carService.getCarByIdentifier(stolenRequest.getCarIdentifier());
            if (car != null) {
                car.setStolen(false);
                carService.update(car);
                throw new WebApplicationException(Response.Status.OK);
            } else {
                throw new WebApplicationException(Response.Status.CONFLICT);
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

}
