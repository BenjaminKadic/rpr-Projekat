package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

/**
 * bean for User
 *
 * @author Benjamin Kadic
 */

public class User implements Idable, Comparable<User>{
    private int id;
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private Date birthdate;

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    @Override
    public String toString() {
        return firstName + " " + lastName + " license: "+ licenseNumber;
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
        return Objects.hash(id, licenseNumber, firstName, lastName, birthdate);
    }

    @Override
    public int compareTo(User o) {
        return this.toString().compareTo(o.toString());
    }
}
