package ba.unsa.etf.rpr.controller;
import ba.unsa.etf.rpr.dao.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static ba.unsa.etf.rpr.dao.DaoFactory.userDao;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class loginController {
    public TextField tf_username;
    public PasswordField pf_password;
    public Button button_login;
    public Button button_toRegister;
    public Text text_error;
    public GridPane gp_login;
    public Button button_close;


    @FXML
    public void initialize(){
        tf_username.setFocusTraversable(true);
        pf_password.setFocusTraversable(true);
    }
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
    public void toRegisterPane(){
        closeWindow();
        openDialog("Register", "/fxml/register.fxml");
    }
    public void closeWindow() {
        Stage stage = (Stage) button_close.getScene().getWindow();
        stage.close();
    }
    private void openDialog(String title, String file){
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


}
