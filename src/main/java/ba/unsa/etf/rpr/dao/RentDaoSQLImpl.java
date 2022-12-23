package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.exceptions.RentACarException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RentDaoSQLImpl extends AbstractDao<Rent> implements RentDao{

    public RentDaoSQLImpl() {
        super("Rents");
    }

    @Override
    public Rent row2object(ResultSet rs) throws RentACarException {
        try{
            Rent rent = new Rent();
            rent.setId(rs.getInt("id"));
            rent.setCar(new CarDaoSQLImpl().getById(rs.getInt("car_id")));
            rent.setUser(new UserDaoSQLImpl().getById(rs.getInt("user_id")));
            rent.setReturned(rs.getBoolean("returned"));
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
        item.put("returned", object.getReturned());
        item.put("start", object.getStartDate());
        item.put("end", object.getEndDate());
        return item;
    }

    @Override
    public List<Rent> getByDateRange(Date start, Date end) throws RentACarException{
        String query = "SELECT * FROM Rents WHERE start BETWEEN ? and ? AND end BETWEEN ? and ?";
        ArrayList<Rent> rents = new ArrayList<>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setObject(1, start);
            stmt.setObject(2, end);
            stmt.setObject(3, start);
            stmt.setObject(4, end);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rents.add(row2object(rs));
            }
            rs.close();
            return rents;
        }catch (SQLException e){
            throw new RentACarException(e.getMessage(),e);
        }
    }
}
