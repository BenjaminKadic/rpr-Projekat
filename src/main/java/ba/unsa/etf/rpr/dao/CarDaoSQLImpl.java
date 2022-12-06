package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Color;
import ba.unsa.etf.rpr.domain.Fuel;
import ba.unsa.etf.rpr.domain.Transmission;

import java.util.List;

public class CarDaoSQLImpl implements CarDao{
    @Override
    public List<Car> searchByTransmission(Transmission transmission) {
        return null;
    }

    @Override
    public List<Car> searchByColor(Color color) {
        return null;
    }

    @Override
    public List<Car> searchByMakeYearRange(int start, int end) {
        return null;
    }

    @Override
    public List<Car> searchByHorsepower(int horsepower) {
        return null;
    }

    @Override
    public List<Car> searchByFuel(Fuel fuel) {
        return null;
    }

    @Override
    public List<Car> searchByMake(String make) {
        return null;
    }

    @Override
    public List<Car> searchByModel(String model) {
        return null;
    }

    @Override
    public List<Car> searchByPriceRange(int min, int max) {
        return null;
    }

    @Override
    public boolean checkIfRented(Car car) {
        return false;
    }

    @Override
    public Car getById(int id) {
        return null;
    }

    @Override
    public Car add(Car item) {
        return null;
    }

    @Override
    public Car update(Car item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Car> getAll() {
        return null;
    }
}
