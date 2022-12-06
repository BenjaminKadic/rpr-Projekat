package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rent;

import java.util.Date;
import java.util.List;

public interface RentDao extends Dao<Rent>{
    List<Rent> getByDateRange(Date start, Date end);
}
