package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MainController {
    protected void openDialog(String title, String file){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    protected void switchScene(String title, String file, Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    protected class CarModel {
        public SimpleStringProperty make = new SimpleStringProperty("");
        public SimpleStringProperty model = new SimpleStringProperty("");
        public SimpleStringProperty color = new SimpleStringProperty("");
        public SimpleStringProperty registration = new SimpleStringProperty("");
        public SimpleIntegerProperty price = new SimpleIntegerProperty();
        public void fromCar(Car car){
            this.make.set(car.getMake());
            this.model.set(car.getModel());
            this.color.set(car.getColor());
            this.registration.set(car.getRegistration());
            this.price.set(car.getPrice());
        }
        public Car toCar(){
            Car car = new Car();
            car.setMake(this.make.getValue());
            car.setModel(this.model.getValue());
            car.setColor(this.color.getValue());
            car.setRegistration(this.registration.getValue());
            car.setPrice(this.price.getValue());
            return car;
        }
    }

    protected class UserModel {
        public SimpleStringProperty username = new SimpleStringProperty("");
        public SimpleStringProperty firstName = new SimpleStringProperty("");
        public SimpleStringProperty lastName = new SimpleStringProperty("");
        public SimpleStringProperty password = new SimpleStringProperty("");
        public SimpleObjectProperty<LocalDate> birthdate = new SimpleObjectProperty<>();

        public void fromUser(User user){
            this.username.set(user.getUsername());
            this.firstName.set(user.getFirstName());
            this.lastName.set(user.getLastName());
            this.password.set(user.getPassword());
            this.birthdate.set(user.getBirthdate().toLocalDate());
        }

        public User toUser(){
            User user = new User();
            user.setUsername(this.username.getValue());
            user.setFirstName(this.firstName.getValue());
            user.setLastName(this.lastName.getValue());
            user.setPassword(this.password.getValue());
            user.setBirthdate(Date.valueOf(this.birthdate.getValue()));
            return user;
        }
    }
}
