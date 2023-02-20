package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * bean for Car
 *
 * @author Benjamin Kadic
 */

public class Car implements Idable, Comparable<Car> {
    private int id;
    private String make;
    private String model;
    private String color;
    private String registration;
    private int price;
    public Car(){}

    public Car(int id, String make, String model, String color, String registration, int price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.registration = registration;
        this.price = price;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return make + " " + model + " registration: " + registration;
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
        return Objects.hash(id, make, model, color, registration, price);
    }

    @Override
    public int compareTo(Car o) {
        return this.toString().compareTo(o.toString());
    }

}
