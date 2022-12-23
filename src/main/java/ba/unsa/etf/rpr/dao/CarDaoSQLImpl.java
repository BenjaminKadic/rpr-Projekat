package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Color;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Benjamin Kadic
 */
public class CarDaoSQLImpl extends AbstractDao<Car> implements CarDao{
    public CarDaoSQLImpl(){
        super("Cars");
    }

    @Override
    public Car row2object(ResultSet rs) throws RentACarException {
        try {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setMake(rs.getString("make"));
            car.setModel(rs.getString("model"));
            car.setColor(Color.valueOf(rs.getString("color").toUpperCase()));
            car.setRegistration(rs.getString("registration"));
            car.setPrice(rs.getInt("price"));
            return car;
        } catch (Exception e) {
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Car object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("make", object.getMake());
        item.put("model", object.getModel());
        item.put("color", object.getColor().value);
        item.put("registration", object.getRegistration());
        item.put("price", object.getPrice());
        return item;
    }


    @Override
    public List<Car> searchByColor(Color color) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE color = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, color.value);ResultSet rs = stmt.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(row2object(rs));
            }
            rs.close();
            return cars;
        } catch (SQLException e) {
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public List<Car> searchByMake(String make) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE UPPER(make) LIKE concat('%', ?, '%')";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, make.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(row2object(rs));
            }
            rs.close();
            return cars;
        } catch (SQLException e) {
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public List<Car> searchByModel(String model) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE UPPER(model) LIKE concat('%', ?, '%')";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, model.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(row2object(rs));
            }
            rs.close();
            return cars;
        } catch (SQLException e) {
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public List<Car> searchByPriceRange(int min, int max) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE price BETWEEN ? and ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(row2object(rs));
            }
            rs.close();
            return cars;
        } catch (SQLException e) {
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public List<Car> searchAvailable() throws RentACarException {
        String query = "SELECT * FROM car c WHERE c.id NOT IN (SELECT r.car_id FROM Rents WHERE r.returned = 0)";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(row2object(rs));
            }
            rs.close();
            return cars;
        } catch (SQLException e) {
            throw new RentACarException(e.getMessage(), e);
        }
    }
}
