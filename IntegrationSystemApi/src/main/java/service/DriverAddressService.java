package service;

import dao.DriverAddressDao;
import domain.DriverAddress;
import javax.ejb.Stateless;

import java.io.Serializable;

/**
 * @author Sam
 */
@Stateless
public class DriverAddressService extends DriverAddressDao implements Serializable {

    @Override
    protected Class<DriverAddress> getEntityClass() {
        return DriverAddress.class;
    }
}
