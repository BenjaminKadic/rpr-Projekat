package ba.unsa.etf.rpr.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBConnection {
    static Properties props = new Properties();
    FileInputStream in;

    {
        try {
            in = new FileInputStream("src/main/resources/DBConnection.properties");
            props.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problemi pri pristupu file-u");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
