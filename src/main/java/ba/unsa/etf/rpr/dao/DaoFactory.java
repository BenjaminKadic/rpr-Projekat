package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Benjamin Kadic
 */
public class DaoFactory {

    private static final CarDao carDao = CarDaoSQLImpl.getInstance();
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final RentDao rentDao = RentDaoSQLImpl.getInstance();

    private DaoFactory(){
    }

    public static CarDao carDao(){
        return carDao;
    }

    public static UserDao userDao(){
        return userDao;
    }

    public static RentDao rentDao(){
        return rentDao;
    }

}

