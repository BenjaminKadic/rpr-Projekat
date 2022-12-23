package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.Date;
import java.util.List;

/**
 * Dao interface for Rent domain bean
 *
 * @author Benjamin Kadic
 */

public interface RentDao extends Dao<Rent>{
    /**
     * Search rents in database based on date range
     * @param start start date
     * @param end end date
     * @return List of rents from rents table
     */
    List<Rent> getByDateRange(Date start, Date end) throws RentACarException;
}
