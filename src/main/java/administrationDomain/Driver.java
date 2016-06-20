package administrationDomain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Sam
 */
public class Driver{

    private Long id;

    private String firstName;
    private String lastName;

    private String email;

    private Address address;

    private Ownership ownership;

    public Driver() {
        this.ownership = new Ownership();
        this.firstName = "";
        this.lastName = "";
        this.address = new Address();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnerships(Ownership ownership) {
        this.ownership = ownership;
    }

    //</editor-fold>

}
