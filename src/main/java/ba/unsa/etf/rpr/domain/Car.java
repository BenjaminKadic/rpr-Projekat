package ba.unsa.etf.rpr.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Car {
    private int id;
    private String make;
    private String model;
    private Color color;
    private String registration;
    private int makeYear;
    private int price;
    private boolean rented;
    private Fuel fuel;
    private Transmission transmission;
    private int horsepower;
    public Car(){}
    public Car(ResultSet rs){
        try{
            this.setId(rs.getInt("id"));
            this.setMake(rs.getString("make"));
            this.setModel(rs.getString("model"));
            this.setColor(Color.valueOf(rs.getString("color").toUpperCase()));
            this.setRegistration(rs.getString("registration"));
            this.setMakeYear(rs.getInt("make_year"));
            this.setPrice(rs.getInt("price"));
            this.setRented(rs.getBoolean("rented"));
            this.setFuel(Fuel.valueOf(rs.getString("fuel").toUpperCase()));
            this.setTransmission(Transmission.valueOf(rs.getString("transmission").toUpperCase()));
            this.setHorsepower(rs.getInt("horsepower"));
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getMakeYear() {
        return makeYear;
    }

    public void setMakeYear(int makeYear) {
        this.makeYear = makeYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", make_year=" + makeYear +
                ", price=" + price +
                ", fuel='" + fuel + '\'' +
                ", transmission='" + transmission + '\'' +
                ", horsepower=" + horsepower +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
