package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Color;
import ba.unsa.etf.rpr.domain.Fuel;
import ba.unsa.etf.rpr.domain.Transmission;
import jdk.jfr.Category;

import java.sql.*;
import java.util.List;

public class CarDaoSQLImpl implements CarDao{
    private Connection connection;
    public CarDaoSQLImpl() {
        try {
            this.connection= DriverManager.getConnection("", "", "");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
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
        String query = "SELECT * FROM Cars WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setMake(rs.getString("make"));
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
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
