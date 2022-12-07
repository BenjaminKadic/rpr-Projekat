package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rent;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class RentDaoSQLImpl implements RentDao{
    private Connection connection;
    public RentDaoSQLImpl() {
        try {
            this.connection= DriverManager.getConnection("", "", "");
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

    @Override
    public Rent add(Rent item) {
        return null;
    }

    @Override
    public Rent update(Rent item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Rent> getAll() {
        return null;
    }

    @Override
    public List<Rent> getByDateRange(Date start, Date end) {
        return null;
    }
}
