package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Color;
import ba.unsa.etf.rpr.domain.Fuel;
import ba.unsa.etf.rpr.domain.Transmission;

import java.sql.*;
import java.util.ArrayList;
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

    private Car createCar(ResultSet rs){
        Car car = new Car();
        try{
            car.setId(rs.getInt("id"));
            car.setMake(rs.getString("make"));
            car.setModel(rs.getString("model"));
            car.setColor(Color.valueOf(rs.getString("color").toUpperCase()));
            car.setRegistration(rs.getString("registration"));
            car.setMakeYear(rs.getInt("make_year"));
            car.setPrice(rs.getInt("price"));
            car.setRented(rs.getBoolean("rented"));
            car.setFuel(Fuel.valueOf(rs.getString("fuel").toUpperCase()));
            car.setTransmission(Transmission.valueOf(rs.getString("transmission").toUpperCase()));
            car.setMileage(rs.getInt("mileage"));
            car.setHorsepower(rs.getInt("horsepower"));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    private List<Car> returnSearched(String query, Object parameter){
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setObject(1, parameter);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> carLista = new ArrayList<>();
            while (rs.next()) {
                Car car = createCar(rs);
                carLista.add(car);
            }
            return carLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> searchByTransmission(Transmission transmission) {
        String query = "SELECT * FROM quotes WHERE UPPER(transmission) = ?";
        return returnSearched(query, String.valueOf(transmission));
    }

    @Override
    public List<Car> searchByColor(Color color) {
        String query = "SELECT * FROM quotes WHERE UPPER(color) = ?";
        return returnSearched(query, String.valueOf(color));
    }

    @Override
    public List<Car> searchByMakeYearRange(int start, int end) {
        String query = "SELECT * FROM Cars WHERE make_year BETWEEN ? and ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, start);
            stmt.setInt(2, end);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> carLista = new ArrayList<>();
            while (rs.next()) {
                Car car = createCar(rs);
                carLista.add(car);
            }
            return carLista;
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }


    @Override
    public List<Car> searchByHorsepower(int horsepower) {
        String query = "SELECT * FROM quotes WHERE horsepower = ?";
        return returnSearched(query, horsepower);
    }

    @Override
    public List<Car> searchByFuel(Fuel fuel) {
        String query = "SELECT * FROM quotes WHERE UPPER(fuel) = ?";
        return returnSearched(query, String.valueOf(fuel));
    }

    @Override
    public List<Car> searchByMake(String make) {
        String query = "SELECT * FROM quotes WHERE UPPER(make) LIKE concat('%', ?, '%')";
        return returnSearched(query, make.toUpperCase());
    }

    @Override
    public List<Car> searchByModel(String model) {
        String query = "SELECT * FROM quotes WHERE UPPER(model) LIKE concat('%', ?, '%')";
        return returnSearched(query, model.toUpperCase());
    }

    @Override
    public List<Car> searchByPriceRange(int min, int max) {
        return null;
    }


    @Override
    public Car getById(int id) {
        String query = "SELECT * FROM Cars WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Car car = createCar(rs);
                rs.close();
                return car;
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
