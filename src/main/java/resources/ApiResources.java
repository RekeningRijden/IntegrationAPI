package resources;

import domain.Car;
import domain.Position;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;

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
import jms.JMSInit;
import jms.JMSProducer;
import request.DeleteStolenRequest;
import request.MovementsRequest;
import request.StolenRequest;
import send.SendMovements;
import service.ApiKeyService;
import service.CarService;
import util.PositionHelper;

@Path("/")
@Named
public class ApiResources {

    @Inject
    private CarService carService;
    @Inject
    private ApiKeyService apiKeyService;
    @EJB
    private JMSInit jmsInit;

    /**
     * update a car
     *
     * @param apiKey key for access
     * @param movementsRequest a request for an invoice by the movements of a
     * car
     */
    @POST
    @Path("/movements")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCar(@QueryParam("api_key") String apiKey, MovementsRequest movementsRequest) {
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            if (movementsRequest.getCarIdentifier() != null && !movementsRequest.getCarIdentifier().equals("")) {
                Car car = null;
                if (carService.getCarByIdentifier(movementsRequest.getCarIdentifier()) != null) {
                    car = carService.getCarByIdentifier(movementsRequest.getCarIdentifier());
                    carService.update(carService.movementRequestToCar(movementsRequest, car));
                } else {
                    car = carService.create(carService.movementRequestToCar(movementsRequest));
                }
                JMSProducer producer = jmsInit.findProducer("portugal_foreign_movement");
                SendMovements sendMovements = new SendMovements();
                sendMovements.send(producer, car);
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
            positions.addAll(PositionHelper.createPositions());
            return positions;
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

    /**
     * Set a car as stolen
     *
     * @param apiKey key for access
     * @param stolenRequest a request to set a car as stolen
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
     * @param deleteStolenRequest the request to set a car unstolen
     */
    @DELETE
    @Path("/stolen")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public void setCarAsStolen(@QueryParam("api_key") String apiKey, DeleteStolenRequest deleteStolenRequest) {
        if (apiKeyService.getApiKeyByKey(apiKey) != null) {
            Car car = carService.getCarByIdentifier(deleteStolenRequest.getCarIdentifier());
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
