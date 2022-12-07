package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Color;

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


    private List<Car> returnSearched(String query, Object parameter){
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setObject(1, parameter);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> carLista = new ArrayList<>();
            while (rs.next()) {
                Car car = new Car(rs);
                carLista.add(car);
            }
            return carLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Car> searchByColor(Color color) {
        String query = "SELECT * FROM quotes WHERE UPPER(color) = ?";
        return returnSearched(query, String.valueOf(color));
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
        String query = "SELECT * FROM Cars WHERE price BETWEEN ? and ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> carLista = new ArrayList<>();
            while (rs.next()) {
                Car car = new Car(rs);
                carLista.add(car);
            }
            return carLista;
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
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
                Car car = new Car(rs);
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
