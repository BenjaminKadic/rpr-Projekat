package ba.unsa.etf.rpr.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String password;

    public User(){}
    public User(ResultSet rs){
        try{
            this.setId(rs.getInt("id"));
            this.setUsername(rs.getString("username"));
            this.setFirstName(rs.getString("first_name"));
            this.setLastName(rs.getString("last_name"));
            this.setBirthdate(rs.getDate("birthdate"));
            this.setPassword(rs.getString("password"));
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
