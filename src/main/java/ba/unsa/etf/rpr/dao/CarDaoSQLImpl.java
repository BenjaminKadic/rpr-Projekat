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
        String query = "SELECT * FROM quotes WHERE color = ?";
        return returnSearched(query, color.value);
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
    public List<Car> searchAvailable() {
        String query = "SELECT * FROM car c WHERE c.id NOT IN (SELECT r.car_id FROM Rents WHERE r.returned = 0)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Car> carLista = new ArrayList<>();
            while (rs.next()) {
                Car car = new Car(rs);
                carLista.add(car);
            }
            return carLista;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns next id for adding cars to database
     *
     * @return int value of next id
     */
    private int getMaxId(){
        int id=1;
        String query = "SELECT MAX(id)+1 FROM Cars";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return id;
    }
    @Override
    public Car add(Car item) {
        int id = getMaxId();
        String query = "INSERT INTO Cars VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query );
            item.setId(id);
            stmt.setInt(1, item.getId());
            stmt.setString(2,item.getMake());
            stmt.setString(3,item.getModel());
            stmt.setString(4, item.getColor().value);
            stmt.setString(5,item.getRegistration());
            stmt.setInt(6, item.getPrice());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Car update(Car item) {
        String query = "UPDATE Cars SET make=?, model=?, color=?, registration=?, price=? WHERE id=?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,item.getMake());
            stmt.setString(2,item.getModel());
            stmt.setString(3, item.getColor().value);
            stmt.setString(4,item.getRegistration());
            stmt.setInt(5, item.getPrice());
            stmt.setInt(6, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
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
