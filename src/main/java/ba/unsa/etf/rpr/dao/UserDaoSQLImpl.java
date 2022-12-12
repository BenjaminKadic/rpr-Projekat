package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoSQLImpl implements UserDao{
    private Connection connection;

    public UserDaoSQLImpl() {
        try {
            this.connection=DBConnection.getInstance().getConnection();
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

    /**
     * returns next id for adding users to database
     *
     * @return int value of next id
     */
    private int getMaxId(){
        int id=1;
        String query = "SELECT MAX(id)+1 FROM Users";
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
    public User add(User item) {
        int id = getMaxId();
        String query = "INSERT INTO Users VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            item.setId(id);
            stmt.setInt(1, item.getId());
            stmt.setString(2,item.getUsername());
            stmt.setString(3,item.getFirstName());
            stmt.setString(4, item.getLastName());
            stmt.setDate(5,item.getBirthdate());
            stmt.setString(6, item.getPassword());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User item) {
        String query = "UPDATE Cars SET username=?, first_name=?, last_name=?, birthdate=?, password=? WHERE id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,item.getUsername());
            stmt.setString(2,item.getFirstName());
            stmt.setString(3, item.getLastName());
            stmt.setDate(4,item.getBirthdate());
            stmt.setString(5, item.getPassword());
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
        String query = "DELETE FROM Users WHERE id = ?";
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
    public List<User> getAll() {
        String query = "SELECT * FROM Cars";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> userLista = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs);
                userLista.add(user);
            }
            rs.close();
            return userLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
