package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.util.List;

/**
 * Dao interface for User domain bean
 *
 * @author Benjamin Kadic
 */
public interface UserDao extends Dao<User>{
    /**
     * Returns all users whose username contains param.
     *
     * @param username search make for cars
     * @return list of Users
     */
    List<User> searchByUsername(String username) throws RentACarException;
    boolean checkUser(String username, String password);
}
