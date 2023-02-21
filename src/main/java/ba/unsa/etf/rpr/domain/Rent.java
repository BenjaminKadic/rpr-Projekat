package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Holds history of all previous and current rents
 *
 * @author Benjamin Kadic
 */

public class Rent implements Idable, Comparable<Rent>{
    private int id;
    private Car car;
    private User user;
    private Date startDate;
    private Date endDate;
    public Rent(){}

    public Rent(Car car, User user, Date startDate, Date endDate) {
        this.car = car;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return user + " rented " + car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, user, startDate, endDate);
    }

    @Override
    public int compareTo(Rent o) {
        return this.toString().compareTo(o.toString());
    }

    /**
     * function that calculates ammount of money to be paid by a customer
     * for renting the given car in the given time frame
     * @return price of rent
     */
    public int getRentPrice(){
        long result=DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
        return (int) result*this.car.getPrice();
    }
}
