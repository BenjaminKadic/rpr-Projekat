package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.sql.*;
import java.util.List;

public class UserDaoSQLImpl implements UserDao{
    private Connection connection;
    public UserDaoSQLImpl() {
        try {
            this.connection= DriverManager.getConnection("", "", "");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getById(int id) {
        String query = "SELECT * FROM Users WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                User user = new User(rs);
                rs.close();
                return user;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public User add(User item) {
        return null;
    }

    @Override
    public User update(User item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
