package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Benjamin Kadic
 */
public class DaoFactory {

    private static final CarDao carDao = new CarDaoSQLImpl();
    private static final UserDao userDao = new UserDaoSQLImpl();
    private static final RentDao rentDao = new RentDaoSQLImpl();

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

