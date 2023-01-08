package ba.unsa.etf.rpr.controller;
import ba.unsa.etf.rpr.dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import static ba.unsa.etf.rpr.dao.DaoFactory.userDao;

/**
 * Controller for login page
 *
 * @author Benjamin Kadic
 */

public class loginController extends MainController{
    public Button focus;
    public TextField tf_username;
    public PasswordField pf_password;
    public Button button_login;
    public Button button_toRegister;
    public Text text_error;
    public GridPane gp_login;
    public Button button_close;


    @FXML
    public void initialize(){
        focus.setFocusTraversable(false);
        tf_username.setFocusTraversable(true);
        pf_password.setFocusTraversable(true);
        button_login.setFocusTraversable(true);
        button_toRegister.setFocusTraversable(true);
        button_close.setFocusTraversable(true);

    }

    /**
     * event handler for user login
     */
    public void userLogin(){
        if(tf_username.getText().isBlank() && pf_password.getText().isBlank()){
            text_error.setText("Please enter your username and password.");
        }
        else if(tf_username.getText().isBlank() && !pf_password.getText().isBlank()){
            text_error.setText("Please enter your username.");
        }
        else if(!tf_username.getText().isBlank() && pf_password.getText().isBlank()){
            text_error.setText("Please enter your password.");
        }
        else{
            String username = tf_username.getText();
            String password = pf_password.getText();
            UserDao u = userDao();

            boolean flag = u.checkUser(username, password);

            if(username.equals("admin") && password.equals("admin")){
                closeWindow();
                openDialog("Home", "/fxml/admin_home.fxml");
            }else if (!flag) {
                text_error.setText("Incorrect username or password, try again!");
                tf_username.clear();
                pf_password.clear();
            }
            /*else {
                openDialog("Home", "/fxml/user_home.fxml", new userHomeController());
            }*/

        }
    }

    /**
     * event handler for switching to user register
     */
    public void toRegisterPane(){
        Stage stage = (Stage) button_toRegister.getScene().getWindow();
        switchScene("Register", "/fxml/register.fxml", stage);
    }

    /**
     * event handler for closing current window
     */
    public void closeWindow() {
        Stage stage = (Stage) button_close.getScene().getWindow();
        stage.close();
    }

}
