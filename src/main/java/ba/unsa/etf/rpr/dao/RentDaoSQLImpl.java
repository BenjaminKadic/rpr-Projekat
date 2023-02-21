package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RentDaoSQLImpl extends AbstractDao<Rent> implements RentDao{
    private static RentDaoSQLImpl instance=null;
    public RentDaoSQLImpl() {
        super("Rents");
    }
    public static RentDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new RentDaoSQLImpl();
        return instance;
    }

    @Override
    public Rent row2object(ResultSet rs) throws RentACarException {
        try{
            Rent rent = new Rent();
            rent.setId(rs.getInt("id"));
            rent.setCar(DaoFactory.carDao().getById(rs.getInt("car_id")));
            rent.setUser(DaoFactory.userDao().getById(rs.getInt("user_id")));
            rent.setStartDate(rs.getDate("start"));
            rent.setEndDate(rs.getDate("end"));
            return rent;
        }catch (SQLException e){
            throw new RentACarException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Rent object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("car_id", object.getCar().getId());
        item.put("user_id", object.getUser().getId());
        item.put("start", object.getStartDate());
        item.put("end", object.getEndDate());
        return item;
    }

    @Override
    public List<Rent> getByDateRange(Date start, Date end) throws RentACarException{
        String query = "SELECT * FROM Rents WHERE start BETWEEN ? and ? OR end BETWEEN ? and ?";
        return executeQuery(query, new Object[]{start, end, start, end});
    }

    @Override
    public List<Rent> getRentedCars(Date date) throws RentACarException{
        String query = "SELECT * FROM Rents WHERE start <= ? and end > ?";
        return executeQuery(query, new Object[]{date, date});
    }
}
