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

    public void validateAge(Date birthdate) throws RentACarException{
        java.sql.Date date = Date.valueOf(new Date(System.currentTimeMillis()).toLocalDate().minusYears(18));
        if(birthdate==null || birthdate.after(date)){
            throw new RentACarException("You must be 18+ in order to make an account");
        }
    }

    public User add(User user) throws RentACarException {
        if (user.getId() != 0){
            throw new RentACarException("Can't add User with ID. ID is autogenerated");
        }
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        validateAge(user.getBirthdate());
        try{
            return DaoFactory.userDao().add(user);
        }catch (RentACarException e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new RentACarException("User with same username already exists");
            }
            throw e;
        }
    }

    public void delete(int categoryId) throws RentACarException{
        DaoFactory.userDao().delete(categoryId);
    }

    public User update(User user) throws RentACarException{
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        validateAge(user.getBirthdate());
        try{
            return DaoFactory.userDao().update(user);
        }catch (RentACarException e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new RentACarException("User with same username already exists");
            }
            throw e;
        }
    }

    public List<User> getAll() throws RentACarException{
        return DaoFactory.userDao().getAll();
    }
}
