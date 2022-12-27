package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarManager {
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

    public void update(Car q) throws RentACarException{
        DaoFactory.carDao().update(q);
    }

    public Car add(Car q) throws RentACarException{
        return DaoFactory.carDao().add(q);
    }

}
