package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentDaoSQLImpl implements RentDao{
    private Connection connection;

    public RentDaoSQLImpl() {
        try {
            this.connection=DBConnection.getInstance().getConnection();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rent getById(int id) {
        String query = "SELECT * FROM Rents WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Rent rent = new Rent();
                rent.setId(rs.getInt("id"));
                rent.setCar(new CarDaoSQLImpl().getById(rs.getInt("car_id")));
                rent.setUser(new UserDaoSQLImpl().getById(rs.getInt("user_id")));
                rent.setReturned(rs.getBoolean("returned"));
                rent.setStartDate(rs.getDate("start"));
                rent.setEndDate(rs.getDate("end"));
                rs.close();
                return rent;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    /**
     * returns next id for adding rents to database
     *
     * @return int value of next id
     */
    private int getMaxId(){
        int id=1;
        String query = "SELECT MAX(id)+1 FROM Rents";
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
    public Rent add(Rent item) {
        int id = getMaxId();
        String query = "INSERT INTO Rents VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query );
            item.setId(id);
            stmt.setInt(1, item.getId());
            stmt.setInt(2,item.getCar().getId());
            stmt.setInt(3,item.getUser().getId());
            stmt.setBoolean(4, item.isReturned());
            stmt.setDate(5,item.getStartDate());
            stmt.setDate(6, item.getEndDate());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Rent update(Rent item) {
        String query = "UPDATE Rents SET car_id=?, user_id=?, returned=?, start=?, end=? WHERE id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,item.getCar().getId());
            stmt.setInt(2,item.getUser().getId());
            stmt.setBoolean(3, item.isReturned());
            stmt.setDate(4,item.getStartDate());
            stmt.setDate(5, item.getEndDate());
            stmt.setInt(6, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Rents WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Rent> getAll() {
        String query = "SELECT * FROM Rents";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Rent> rentsLista = new ArrayList<>();
            while (rs.next()) {
                Rent rent = new Rent();
                rent.setId(rs.getInt("id"));
                rent.setCar(new CarDaoSQLImpl().getById(rs.getInt("car_id")));
                rent.setUser(new UserDaoSQLImpl().getById(rs.getInt("user_id")));
                rent.setReturned(rs.getBoolean("returned"));
                rent.setStartDate(rs.getDate("start"));
                rent.setEndDate(rs.getDate("end"));
                rentsLista.add(rent);
            }
            rs.close();
            return rentsLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Rent> getByDateRange(Date start, Date end) {
        String query = "SELECT * FROM Rents WHERE start BETWEEN ? and ? AND end BETWEEN ? and ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setDate(1, start);
            stmt.setDate(2, end);
            stmt.setDate(3, start);
            stmt.setDate(4, end);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Rent> rentsLista = new ArrayList<>();
            while (rs.next()) {
                Rent rent = new Rent();
                rent.setId(rs.getInt("id"));
                rent.setCar(new CarDaoSQLImpl().getById(rs.getInt("car_id")));
                rent.setUser(new UserDaoSQLImpl().getById(rs.getInt("user_id")));
                rent.setReturned(rs.getBoolean("returned"));
                rent.setStartDate(rs.getDate("start"));
                rent.setEndDate(rs.getDate("end"));
                rentsLista.add(rent);
            }
            rs.close();
            return rentsLista;
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }
}
