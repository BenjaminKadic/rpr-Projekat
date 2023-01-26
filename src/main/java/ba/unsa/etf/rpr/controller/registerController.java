package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controller for register page
 *
 * @author Benjamin Kadic
 */

public class registerController extends MainController implements Initializable {
    private final UserManager userManager = new UserManager();
    private final UserModel model = new UserModel();
    public TextField tf_registerUsername;
    public TextField tf_registerFirstName;
    public TextField tf_registerLastName;
    public PasswordField pf_registerPassword;
    public DatePicker dp_registerBirthdate;
    public Button button_register;
    public Button button_toLogin;
    public Text text_error;

    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tf_registerUsername.setFocusTraversable(true);
        tf_registerFirstName.setFocusTraversable(true);
        tf_registerLastName.setFocusTraversable(true);
        pf_registerPassword.setFocusTraversable(true);
        dp_registerBirthdate.setFocusTraversable(true);
        button_register.setFocusTraversable(true);
        button_register.setDefaultButton(true);
        button_toLogin.setFocusTraversable(true);
        dp_registerBirthdate.setConverter(
        new StringConverter<>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, dateFormatter)
                        : null;
            }
        });
    }

    /**
     * event handler for user registering
     */
    public void userRegister() {
        if(tf_registerUsername.getText().isBlank())  text_error.setText("Username can't be empty!");
        else if(tf_registerFirstName.getText().isBlank()) text_error.setText("First Name can't be empty!");
        else if(tf_registerLastName.getText().isBlank()) text_error.setText("Last Name can't be empty");
        else if(pf_registerPassword.getText().isBlank()) text_error.setText("Password can't be empty!");
        else if(dp_registerBirthdate.getEditor().getText().isBlank()) text_error.setText("Birthdate can't be empty!");
        else{
            try{
                tf_registerUsername.textProperty().bindBidirectional(model.username);
                tf_registerFirstName.textProperty().bindBidirectional(model.firstName);
                tf_registerLastName.textProperty().bindBidirectional(model.lastName);
                pf_registerPassword.textProperty().bindBidirectional(model.password);
                dp_registerBirthdate.valueProperty().bindBidirectional(model.birthdate);
                userManager.add(model.toUser());
                //openDialog("Home", "/fxml/user_home.fxml", new userHomeController());
            }catch (Exception e){
                text_error.setText(e.getMessage());
            }
        }
    }

    /**
     * event handler for switching to user login
     */
    public void toLoginPane() {
        Stage stage = (Stage) button_toLogin.getScene().getWindow();
        switchScene("Login", "/fxml/login.fxml", stage);
    }
}
