package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Car> searchCars(String text) throws RentACarException {
        Set<Car> cars=new HashSet<>();
        cars.addAll(DaoFactory.carDao().searchByModel(text));
        cars.addAll(DaoFactory.carDao().searchByMake(text));
        return cars;
    }

    public void delete(int id) throws RentACarException{
        DaoFactory.carDao().delete(id);
    }

    public Car getById(int carId) throws RentACarException{
        return DaoFactory.carDao().getById(carId);
    }

    public Car update(Car car) throws RentACarException{
        validateRegistration(car.getRegistration());
        try{
            return DaoFactory.carDao().update(car);
        }catch (RentACarException e){
            if (e.getMessage().contains("UQ_registration")){
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
            if (e.getMessage().contains("UQ_registration")){
                throw new RentACarException("Car with same registration already exists");
            }
            throw e;
        }
    }

}
