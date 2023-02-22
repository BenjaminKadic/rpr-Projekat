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
    public void validateLicense(String license) throws RentACarException {
        if (license == null || license.length() != 6){
            throw new RentACarException("License must contain 6 characters");
        }
    }
    public void validateAge(Date birthdate) throws RentACarException{
        java.sql.Date date = Date.valueOf(new Date(System.currentTimeMillis()).toLocalDate().minusYears(18));
        if(birthdate==null || birthdate.after(date)){
            throw new RentACarException("User must be 18+ in order to make an account");
        }
    }

    public User getById(int userId) throws RentACarException{
        return DaoFactory.userDao().getById(userId);
    }

    public User add(User user) throws RentACarException {
        if (user.getId() != 0){
            throw new RentACarException("Can't add User with ID. ID is autogenerated");
        }
        validateLicense(user.getLicense());
        validateAge(user.getBirthdate());
        try{
            return DaoFactory.userDao().add(user);
        }catch (RentACarException e){
            if (e.getMessage().contains("license_UNIQUE")){
                throw new RentACarException("User with same license already exists");
            }
            throw e;
        }
    }

    public void delete(int id) throws RentACarException{
        try{
            DaoFactory.userDao().delete(id);
        }catch (RentACarException e){
            if (e.getMessage().contains("foreign")){
                throw new RentACarException("Can't delete user who has rented cars. Remove previous rents first");
            }
            throw e;
        }
    }

    public User update(User user) throws RentACarException{
        validateLicense(user.getLicense());
        validateAge(user.getBirthdate());
        try{
            return DaoFactory.userDao().update(user);
        }catch (RentACarException e){
            if (e.getMessage().contains("license_UNIQUE")){
                throw new RentACarException("User with same license already exists");
            }
            throw e;
        }
    }

    public List<User> getAll() throws RentACarException{
        return DaoFactory.userDao().getAll();
    }
}
