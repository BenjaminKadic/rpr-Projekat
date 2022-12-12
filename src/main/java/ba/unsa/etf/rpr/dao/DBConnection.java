package ba.unsa.etf.rpr.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Class that is used to read database credentials from a .properties file
 * and create a singleton connection
 *
 * @author Benjamin Kadic
 */
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
    private final Connection connection;
    private static DBConnection instance;
    private DBConnection() {
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        try{
            connection= DriverManager.getConnection(url, username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public static DBConnection getInstance() throws SQLException{
        if(instance == null){
            instance = new DBConnection();
        }else if(instance.getConnection().isClosed()){
            instance=new DBConnection();
        }
        return instance;
    }

}
