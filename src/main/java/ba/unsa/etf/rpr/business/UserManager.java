package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.Date;
import java.util.List;

/**
 * Business Logic Layer for management of Users
 *
 * @author Benjamin Kadic
 */
public class UserManager {
    public void validateUsername(String username) throws RentACarException {
        if (username == null || username.length() > 45 || username.length() < 4){
            throw new RentACarException("Username must contain between 4 and 45 characters");
        }
    }

    public void validatePassword(String password) throws RentACarException {
        if (password == null || password.length() > 45 || password.length() < 8){
            throw new RentACarException("Password must contain at least 8 characters");
        }
    }


}
