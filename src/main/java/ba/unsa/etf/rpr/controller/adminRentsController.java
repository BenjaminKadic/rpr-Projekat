package ba.unsa.etf.rpr.controller;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class adminRentsController extends MainController{
    public MenuItem mi_logout;
    public MenuItem mi_close;
    public MenuItem mi_cars;
    public MenuItem mi_users;
    public MenuItem mi_rents;
    public TextField tf_search;
    public Button button_search;
    public TableView tv_rents;
    public Button button_add;
    public Button button_current;
    public Button button_edit;
    public Button button_delete;


    /**
     * event handler for logging out
     */
    public void logOut() {
        closeWindow();
        openWindow("Login","/fxml/login.fxml");
    }

    /**
     * event handler for closing current window
     */
    public void closeWindow() {
        Stage stage = (Stage) button_add.getScene().getWindow();
        stage.close();
    }

    /**
     * event handler for opening car admin window
     */
    public void goToCars() {
        Stage stage = (Stage) button_add.getScene().getWindow();
        switchScene("Cars", "/fxml/admin_cars.fxml", stage);
    }

    /**
     * event handler for opening user admin window
     */
    public void goToUsers() {
        Stage stage = (Stage) button_add.getScene().getWindow();
        switchScene("Cars", "/fxml/admin_users.fxml", stage);
    }

    /**
     * event handler for opening rents admin window
     */
    public void goToRents() {
        Stage stage = (Stage) button_add.getScene().getWindow();
        switchScene("Cars", "/fxml/admin_rents.fxml", stage);
    }
}
