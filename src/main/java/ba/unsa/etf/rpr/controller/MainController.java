package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.domain.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.sql.Date;

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
    protected class UserModel {
        public SimpleStringProperty username = new SimpleStringProperty("");
        public SimpleStringProperty firstName = new SimpleStringProperty("");
        public SimpleStringProperty lastName = new SimpleStringProperty("");
        public SimpleStringProperty password = new SimpleStringProperty("");
        public SimpleObjectProperty<Date> birthdate = new SimpleObjectProperty<>();

        public void fromQuote(User user){
            this.username.set(user.getUsername());
            this.firstName.set(user.getFirstName());
            this.lastName.set(user.getLastName());
            this.password.set(user.getPassword());
            this.birthdate.set(user.getBirthdate());
        }

        public User toUser(){
            User user = new User();
            user.setUsername(this.username.getValue());
            user.setFirstName(this.firstName.getValue());
            user.setLastName(this.lastName.getValue());
            user.setPassword(this.password.getValue());
            user.setBirthdate(this.birthdate.getValue());
            return user;
        }
    }
}
