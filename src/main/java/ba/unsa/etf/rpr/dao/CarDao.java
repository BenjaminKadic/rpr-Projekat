package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.*;

import java.util.List;
/**
 * Dao interface for Quote domain bean
 *
 * @author Benjamin Kadic
 */
public interface CarDao extends Dao<Car>{
    /**
     * Returns all cars with given transmission.
     *
     * @param transmission search transmission for cars
     * @return list of cars
     */
    List<Car> searchByTransmission(Transmission transmission);
    /**
     * Returns all cars with given color.
     *
     * @param color search color for cars
     * @return list of cars
     */
    List<Car> searchByColor(Color color);
    /**
     * Returns all cars produced in given year range
     *
     * @param start start year
     * @param end end year
     * @return list of cars
     */
    List<Car> searchByMakeYearRange(int start, int end);
    /**
     * Returns all cars with given horsepower.
     *
     * @param horsepower search horsepower for cars
     * @return list of cars
     */
    List<Car> searchByHorsepower(int horsepower);
    /**
     * Returns all cars with given fuel.
     *
     * @param fuel search fuel for cars
     * @return list of cars
     */
    List<Car> searchByFuel(Fuel fuel);
    /**
     * Returns all cars of given make.
     *
     * @param make search make for cars
     * @return list of cars
     */
    List<Car> searchByMake(String make);
    /**
     * Returns all cars of given model.
     *
     * @param model search model for cars
     * @return list of cars
     */
    List<Car> searchByModel(String model);
    /**
     * Returns all cars in given price range.
     *
     * @param min minimal price for search
     * @param max maximum price for search
     * @return list of cars
     */
    List<Car> searchByPriceRange(int min, int max);

}
