package ba.unsa.etf.rpr.business;


import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import java.sql.Date;
import java.util.List;

import static ba.unsa.etf.rpr.dao.DaoFactory.*;

/**
 * Business Logic Layer for management of Rents
 *
 * @author Benjamin Kadic
 */
public class RentManager {
    /**
     * helping method that checks if list of rents contains a rent
     * with given car
     * @param rents list of rents to check
     * @param car to check
     * @return truth if list contains car
     */
    boolean RentsContainCar(List<Rent> rents, Car car){
        for(Rent rent : rents) if(rent.getCar().equals(car)) return true;
        return false;
    }

    public void validateRentDate(Car car, Date start, Date end) throws RentACarException {
        List<Rent> rents = rentDao().getByDateRange(start, end);
        rents.addAll(rentDao().getRentedCars(start));
        if (car == null || RentsContainCar(rents, car)) {
            throw new RentACarException("Car already rented in given date range");
        }
        if(start.after(end)) {
            throw new RentACarException("End date cannot be before start date");
        }
    }

    public Rent add(Rent rent) throws RentACarException {
        if (rent.getId() != 0){
            throw new RentACarException("Can't add rent with ID. ID is autogenerated");
        }
        validateRentDate(rent.getCar(), rent.getStartDate(), rent.getEndDate());
        return DaoFactory.rentDao().add(rent);
    }

    public void delete(int rentId) throws RentACarException{
        DaoFactory.rentDao().delete(rentId);
    }

    public Rent update(Rent rent) throws RentACarException{
        return DaoFactory.rentDao().update(rent);
    }

    public List<Rent> getAll() throws RentACarException{
        return DaoFactory.rentDao().getAll();
    }

    public List<Rent> getRentedCars() throws RentACarException{
        return DaoFactory.rentDao().getRentedCars(new Date(System.currentTimeMillis()));
    }

    public Rent getById(int rentId) throws RentACarException{
        return DaoFactory.rentDao().getById(rentId);
    }
}