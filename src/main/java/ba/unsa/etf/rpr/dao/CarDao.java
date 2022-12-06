package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.*;

import java.util.List;

public interface CarDao extends Dao<Car>{
    List<Car> searchByTransmission(Transmission transmission);
    List<Car> searchByColor(Color color);
    List<Car> searchByMakeYearRange(int start, int end);
    List<Car> searchByHorsepower(int horsepower);
    List<Car> searchByFuel(Fuel fuel);

}
