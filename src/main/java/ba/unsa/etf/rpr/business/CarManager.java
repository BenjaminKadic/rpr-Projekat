package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.util.List;

/**
 * Business Logic Layer for management of Cars
 *
 * @author Benjamin Kadic
 */
public class CarManager {

    public void validateRegistration(String registration) throws RentACarException{
        if (registration == null || registration.length() != 7){
            throw new RentACarException("Registration must contain 7 characters");
        }
    }
    public List<Car> getAll() throws RentACarException {
        return DaoFactory.carDao().getAll();
    }

    public void delete(int id) throws RentACarException{
        try{
            DaoFactory.carDao().delete(id);
        }catch (RentACarException e){
            if (e.getMessage().contains("foreign")){
                throw new RentACarException("Can't delete cars that have been rented. Remove previous rents first!");
            }
            throw e;
        }

    }

    public Car getById(int carId) throws RentACarException{
        return DaoFactory.carDao().getById(carId);
    }

    public Car update(Car car) throws RentACarException{
        validateRegistration(car.getRegistration());
        try{
            return DaoFactory.carDao().update(car);
        }catch (RentACarException e){
            if (e.getMessage().contains("registration_UNIQUE")){
                throw new RentACarException("Car with same registration already exists");
            }
            throw e;
        }
    }

    public Car add(Car car) throws RentACarException{
        validateRegistration(car.getRegistration());
        try{
            return DaoFactory.carDao().add(car);
        }catch (RentACarException e){
            if (e.getMessage().contains("registration_UNIQUE")){
                throw new RentACarException("Car with same registration already exists");
            }
            throw e;
        }
    }

}
