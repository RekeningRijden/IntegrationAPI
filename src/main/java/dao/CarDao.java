/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Car;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 * @author Sam
 */
public abstract class CarDao extends AbstractDao<Car> {

    /**
     * Find the Car object in the database which has the given carIdentifier.
     * @param carIdentifier to look for.
     * @return a Car object.
     */
    public Car getCarByIdentifier(String carIdentifier) {
        TypedQuery<Car> q = getEntityManager().createQuery("SELECT c FROM Car c WHERE c.carIdentifier = :carIdentifier", Car.class)
                .setParameter("carIdentifier", carIdentifier);

        return oneResult(q);
    }

    /**
     * Find the Car object in the database which has the given licencePlate.
     * @param licencePlate to look for.
     * @return a Car object.
     */
    public Car getCarByLicencePlate(String licencePlate){
        TypedQuery<Car> q = getEntityManager().createQuery("SELECT c FROM Car c WHERE c.licencePlate = :licencePlate", Car.class)
                .setParameter("licencePlate", licencePlate);

        return oneResult(q);
    }
    
    public int min() {
        Query query = getEntityManager().createNamedQuery(getEntityClass().getSimpleName() + ".min", getEntityClass());
        
        return query.getSingleResult() == null ? 0 : (int) (long) query.getSingleResult();
    }
}
