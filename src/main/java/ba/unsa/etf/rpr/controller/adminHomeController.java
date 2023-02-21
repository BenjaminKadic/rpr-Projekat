package ba.unsa.etf.rpr.controller;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * JavaFX controller for home panel
 *
 * @author Benjamin Kadic
 */
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

    /**
     * event handler for opening car admin window
     */
    public void goToCars() {
        Stage stage = (Stage) button_cars.getScene().getWindow();
        switchScene("Cars", "/fxml/admin_cars.fxml", stage);
    }

    /**
     * event handler for opening user admin window
     */
    public void goToUsers() {
        Stage stage = (Stage) button_cars.getScene().getWindow();
        switchScene("Users", "/fxml/admin_users.fxml", stage);
    }

    /**
     * event handler for opening rents admin window
     */
    public void goToRents() {
        Stage stage = (Stage) button_cars.getScene().getWindow();
        switchScene("Rents", "/fxml/admin_rents.fxml", stage);
    }

    /**
     * event handler for opening current rents/return a car window
     */
    public void returnCar() {
        openWindow("Return car", "/fxml/return_car.fxml");
    }

}
