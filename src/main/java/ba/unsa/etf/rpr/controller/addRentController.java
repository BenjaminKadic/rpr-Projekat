package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class addRentController extends MainController implements Initializable {
    public BorderPane pane;
    public ComboBox<User> cb_user;
    public ComboBox<Car> cb_car;
    public DatePicker dp_start;
    public DatePicker dp_end;
    public Button button_add;
    public Button button_cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addRent() {
    }

    public void closeWindow() {
    }


}
