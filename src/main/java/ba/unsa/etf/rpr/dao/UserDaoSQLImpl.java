package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao{

    public UserDaoSQLImpl() {
        super("users");
    }

    @Override
    public User row2object(ResultSet rs) throws RentACarException {
        try{
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthdate(rs.getDate("birthdate"));
            user.setPassword(rs.getString("password"));
            return user;
        }catch (SQLException e){
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("username", object.getUsername());
        item.put("first_name", object.getFirstName());
        item.put("last_name", object.getLastName());
        item.put("birthdate", object.getBirthdate());
        item.put("password", object.getPassword());
        return item;
    }

    @Override
    public List<User> searchByUsername(String username) throws RentACarException {
        String query = "SELECT * FROM Users WHERE UPPER(username) LIKE concat('%', ?, '%')";
        return executeQuery(query, new Object[]{username});
    }
}
