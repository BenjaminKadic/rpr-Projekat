package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Benjamin Kadic
 */
public class CarDaoSQLImpl extends AbstractDao<Car> implements CarDao{
    private static CarDaoSQLImpl instance=null;
    public CarDaoSQLImpl(){
        super("Cars");
    }

    public static CarDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new CarDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Car row2object(ResultSet rs) throws RentACarException {
        try {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setMake(rs.getString("make"));
            car.setModel(rs.getString("model"));
            car.setColor(rs.getString("color"));
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
        item.put("color", object.getColor());
        item.put("registration", object.getRegistration());
        item.put("price", object.getPrice());
        return item;
    }


    @Override
    public List<Car> searchByColor(String color) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE color = ?";
        return executeQuery(query, new Object[]{color});
    }

    @Override
    public List<Car> searchByMake(String make) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE UPPER(make) LIKE concat('%', ?, '%')";
        return executeQuery(query, new Object[]{make.toUpperCase()});
    }

    @Override
    public List<Car> searchByModel(String model) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE UPPER(model) LIKE concat('%', ?, '%')";
        return executeQuery(query, new Object[]{model.toUpperCase()});
    }

    @Override
    public List<Car> searchByPriceRange(int min, int max) throws RentACarException {
        String query = "SELECT * FROM Cars WHERE price BETWEEN ? and ?";
        return executeQuery(query, new Object[]{min,max});
    }

    @Override
    public List<Car> searchAvailable() throws RentACarException {
        String query = "SELECT * FROM car c WHERE c.id NOT IN (SELECT r.car_id FROM Rents WHERE r.returned = 0)";
        return executeQuery(query, new Object[]{});
    }
}
