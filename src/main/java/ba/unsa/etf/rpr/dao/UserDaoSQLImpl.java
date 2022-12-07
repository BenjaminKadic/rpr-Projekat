package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.User;

import java.sql.*;
import java.util.List;

public class UserDaoSQLImpl implements UserDao{
    private Connection connection;
    public UserDaoSQLImpl() {
        try {
            this.connection= DriverManager.getConnection("", "", "");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User add(User item) {
        return null;
    }

    @Override
    public User update(User item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
