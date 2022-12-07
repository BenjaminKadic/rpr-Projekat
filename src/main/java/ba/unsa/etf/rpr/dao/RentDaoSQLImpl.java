package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;

public class RentDaoSQLImpl implements RentDao{
    private Connection connection;
    public RentDaoSQLImpl() {
        try {
            this.connection= DriverManager.getConnection("", "", "");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Rent getById(int id) {
        return null;
    }

    @Override
    public Rent add(Rent item) {
        return null;
    }

    @Override
    public Rent update(Rent item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Rent> getAll() {
        return null;
    }

    @Override
    public List<Rent> getByDateRange(Date start, Date end) {
        return null;
    }
}
