package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Color;
import ba.unsa.etf.rpr.domain.Transmission;

import java.util.List;

public interface CarDao extends Dao<Car>{
    List<Car> searchByTransmission(Transmission transmission);
    List<Car> searchByColor(Color color);
    List<Car> searchByMakeYear(int start, int end);
    List<Car> searchByHorsepower(int horsepower);

}
