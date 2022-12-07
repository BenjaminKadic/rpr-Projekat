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
     * Returns all cars with given color.
     *
     * @param color search color for cars
     * @return list of cars
     */
    List<Car> searchByColor(Color color);
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

    /**
     * Returns all cars that are not rented
     *
     * @return list of cars
     */
    List<Car> searchAvailable();

}
