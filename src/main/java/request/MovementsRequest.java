/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maikel
 */
public class MovementsRequest {
    private String carIdentifier;
    private String licencePlate;
    private DriverRequest driver;
    private DriverAddressRequest driverAddress;
    private List<Position> positions;

    public MovementsRequest() {
        driver = new DriverRequest();
        driverAddress = new DriverAddressRequest();
        positions = new ArrayList<>();
    }

    public String getCarIdentifier() {
        return carIdentifier;
    }

    public void setCarIdentifier(String carIdentifier) {
        this.carIdentifier = carIdentifier;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public DriverRequest getDriver() {
        return driver;
    }

    public void setDriver(DriverRequest driver) {
        this.driver = driver;
    }

    public DriverAddressRequest getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(DriverAddressRequest driverAddress) {
        this.driverAddress = driverAddress;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
    
    
}
