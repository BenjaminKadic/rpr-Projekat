package ba.unsa.etf.rpr.controller;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class adminHomeController extends MainController {
    public MenuItem mi_close;
    public MenuItem mi_cars;
    public MenuItem mi_users;
    public MenuItem mi_rents;
    public Button button_cars;    
    public Button button_users;
    public Button button_rents;
    public Button button_returnCar;


    /**
     * event handler for closing current window
     */
    public void closeWindow() {
        Stage stage = (Stage) button_cars.getScene().getWindow();
        stage.close();
    }

    public void goToCars() {
        Stage stage = (Stage) button_cars.getScene().getWindow();
        switchScene("Cars", "/fxml/admin_cars.fxml", stage);
    }

    public void goToUsers() {
        Stage stage = (Stage) button_cars.getScene().getWindow();
        switchScene("Cars", "/fxml/admin_users.fxml", stage);
    }

    public void goToRents() {
        Stage stage = (Stage) button_cars.getScene().getWindow();
        switchScene("Cars", "/fxml/admin_rents.fxml", stage);
    }

    public void returnCar() {
        openWindow("Return car", "/fxml/return_car.fxml");
    }

}
