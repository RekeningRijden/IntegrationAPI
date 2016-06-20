package administrationDomain;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam
 */
public class Car{

    private Long id;

    private Long cartrackerId;
    private String licencePlate;

    private Ownership currentOwnership;
    private List<Position> positions;

    public Car() {
        positions = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ownership getCurrentOwnership() {
        return currentOwnership;
    }

    public void setCurrentOwnership(Ownership currentOwnership) {
        this.currentOwnership = currentOwnership;
    }

    public Long getCartrackerId() {
        return cartrackerId;
    }

    public void setCartrackerId(Long cartrackerId) {
        this.cartrackerId = cartrackerId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }    
    
    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
    //</editor-fold>


}
