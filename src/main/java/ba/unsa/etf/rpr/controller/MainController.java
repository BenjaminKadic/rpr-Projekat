package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.domain.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Date;
import java.time.LocalDate;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Main controller that contains models and functions
 * that are needed in multiple Controllers
 *
 * @author Benjamin Kadic
 */

public class MainController {

    /**
     * opens a new window
     */
    protected void openWindow(String title, String file) {
        openWindow(title, file, false);
    }

    /**
     * opens a new window
     * @param title title of new window
     * @param file fxml file that contains new window
     */
    protected void openWindow(String title, String file, boolean borderless){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Stage stage = new Stage();
            if(borderless) stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.getIcons().add(new Image("/img/car-rent.png"));
            stage.setTitle(title);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * switches scenes in same stage
     * @param title to be displayed when switch occurs
     * @param file fxml file that contains a new scene
     * @param stage on which scene is to be switched
     */

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


    /**
     * Helper Model class that supports 2 way data binding with form for Car management
     */
    protected static class CarModel {
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

    /**
     * Helper Model class that supports 2 way data binding with form for User management
     */
    protected static class UserModel {
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

        /**
         * Helper Model class that supports 2 way data binding with form for Rent management
         */

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

    protected static class RentModel{

        public SimpleObjectProperty<Car> car = new SimpleObjectProperty<>();
        public SimpleObjectProperty<User> user = new SimpleObjectProperty<>();
        public SimpleObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
        public SimpleObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();

        public void fromRent(Rent rent){
            this.car.set(rent.getCar());
            this.user.set(rent.getUser());
            this.startDate.set(rent.getStartDate().toLocalDate());
            this.endDate.set(rent.getEndDate().toLocalDate());
        }

        public Rent toRent(){
            Rent rent = new Rent();
            rent.setCar(this.car.getValue());
            rent.setUser(this.user.getValue());
            rent.setStartDate(Date.valueOf(this.startDate.getValue()));
            rent.setEndDate(Date.valueOf(this.endDate.getValue()));
            return rent;
        }
    }
}
