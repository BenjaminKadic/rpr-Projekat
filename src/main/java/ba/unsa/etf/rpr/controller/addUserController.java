package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * JavaFX controller for adding a user
 *
 * @author Benjamin Kadic
 */
public class addUserController extends MainController implements Initializable {

    private final UserManager userManager = new UserManager();
    private final UserModel model = new UserModel();
    public BorderPane pane;
    public TextField tf_firstName;
    public TextField tf_lastName;
    public TextField tf_license;
    public DatePicker dp_birthdate;
    public Button button_add;
    public Button button_cancel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        dp_birthdate.setConverter(
            new StringConverter<>() {
                final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
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
            }
        );
        tf_firstName.textProperty().bindBidirectional(model.firstName);
        tf_lastName.textProperty().bindBidirectional(model.lastName);
        tf_license.textProperty().bindBidirectional(model.license);
        dp_birthdate.valueProperty().bindBidirectional(model.birthdate);
    }

    /**
     * event handler for adding a new user to DB
     */
    public void addUser() {
        try{
            userManager.add(model.toUser());
            button_add.getScene().getWindow().hide();
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * event handler for closing add a user window
     */
    public void closeWindow() {
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        stage.close();
    }
}
