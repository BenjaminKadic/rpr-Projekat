package ba.unsa.etf.rpr.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class DBConnection {
    private final Properties props = new Properties();


    {
        try {
            FileInputStream in;
            in = new FileInputStream("C:\\Users\\Thinkpad\\IdeaProjects\\rpr-Projekat\\src\\main\\resources\\DBConnection.properties");
            props.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problemi pri pristupu file-u");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String url = props.getProperty("url");
    private final String username = props.getProperty("username");
    private final String password = props.getProperty("password");


    public Connection connect() {
        Connection connection;
        try{
            connection= DriverManager.getConnection(url, username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
