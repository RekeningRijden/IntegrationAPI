/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Maikel
 */
@Entity
@Table(name = "Car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String carIdentifier;
    private String licencePlate;
    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;
    @OneToOne(cascade = CascadeType.ALL)
    private DriverAddress driverAddress;

    public Car() {
        driver = new Driver();
        driverAddress = new DriverAddress();
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
    
}
