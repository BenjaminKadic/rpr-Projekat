package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao{
    private static UserDaoSQLImpl instance=null;
    public UserDaoSQLImpl() {
        super("Users");
    }
    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    @Override
    public User row2object(ResultSet rs) throws RentACarException {
        try{
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLicense(rs.getString("license"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthdate(rs.getDate("birthdate"));
            return user;
        }catch (SQLException e){
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("license", object.getLicense());
        item.put("first_name", object.getFirstName());
        item.put("last_name", object.getLastName());
        item.put("birthdate", object.getBirthdate());
        return item;
    }


}
