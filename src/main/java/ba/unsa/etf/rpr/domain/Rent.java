package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

public class Rent {
    private int id;
    private Car car;
    private User user;
    private boolean returned;
    private Date startDate;
    private Date endDate;

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

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
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
        return "Rent{" +
                "car=" + car +
                ", user=" + user +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
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
        return Objects.hash(id, car, user, returned, startDate, endDate);
    }
}
