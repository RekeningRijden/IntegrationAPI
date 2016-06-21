/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maikel
 */
@Entity
@Table(name = "Car")
@NamedQueries({
    @NamedQuery(name = "Car.min", query = "SELECT MIN(d.cartrackerId) FROM Car d") 
})
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long cartrackerId;
    private String carIdentifier;
    private String licencePlate;
    private Boolean stolen;
    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;
    @OneToOne(cascade = CascadeType.ALL)
    private DriverAddress driverAddress;
    @Transient
    private List<Position> positions;
    @Transient
    private Position lastPosition;

    public Car() {
        driver = new Driver();
        driverAddress = new DriverAddress();
        stolen = false;
        positions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverAddress getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(DriverAddress driverAddress) {
        this.driverAddress = driverAddress;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Boolean getStolen() {
        return stolen;
    }

    public void setStolen(Boolean stolen) {
        this.stolen = stolen;
    }

    public Long getCartrackerId() {
        return cartrackerId;
    }

    public void setCartrackerId(Long cartrackerId) {
        this.cartrackerId = cartrackerId;
    }
    
    public administrationDomain.Car carToAdministrationCar (List<Position> positions){
        administrationDomain.Car newCar = new administrationDomain.Car();
        newCar.setLicencePlate(this.getLicencePlate());
        newCar.setPositions(positions);
        newCar.setCartrackerId(this.getCartrackerId());
        
        administrationDomain.Address newAddress = new administrationDomain.Address();
        newAddress.setCity(this.getDriverAddress().getCity());
        newAddress.setCountry(this.getDriverAddress().getCountry());
        newAddress.setStreet(this.getDriverAddress().getStreet());
        newAddress.setStreetNr(this.getDriverAddress().getStreetNr());
        newAddress.setZipCode(this.getDriverAddress().getZipcode());
        
        administrationDomain.Driver newDriver = new administrationDomain.Driver();
        newDriver.setFirstName(this.getDriver().getFirstName());
        newDriver.setLastName(this.getDriver().getLastName());
        
        administrationDomain.Ownership newOwnership = new administrationDomain.Ownership();
        
        newDriver.setAddress(newAddress);
        newOwnership.setDriver(newDriver);
        newCar.setCurrentOwnership(newOwnership);
        return newCar;
    }
    
    public policeDomain.Car carToPoliceCar (Position position){
        policeDomain.Car newCar = new policeDomain.Car();
        newCar.setLicencePlate(licencePlate);
        newCar.setStolen(stolen);
        newCar.setLastPosition(position);
        return newCar;
    }
    
    public policeDomain.Car carToPoliceCar (){
        policeDomain.Car newCar = new policeDomain.Car();
        newCar.setLicencePlate(licencePlate);
        newCar.setStolen(stolen);        
        return newCar;
    }
    
}
