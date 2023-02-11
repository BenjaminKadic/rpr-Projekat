package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class editUserController extends MainController implements Initializable {
    private final UserManager userManager=new UserManager();
    private UserModel userModel= new UserModel();
    public BorderPane pane;
    public ChoiceBox<User> cb_userList;
    public TextField tf_firstName;
    public TextField tf_lastName;
    public TextField tf_license;
    public DatePicker dp_birthdate;
    public Button button_apply;
    public Button button_cancel;

    final ObservableList<User> options = FXCollections.observableArrayList();
    int userId;

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
        try {
            options.addAll(userManager.getAll());
            options.sort(null);
            cb_userList.setItems(options);
            cb_userList.setOnAction(e -> {
                User user = cb_userList.getSelectionModel().getSelectedItem();
                tf_firstName.setText(user.getFirstName());
                tf_lastName.setText(user.getLastName());
                tf_license.setText(user.getLicense());
                dp_birthdate.setValue(user.getBirthdate().toLocalDate());
                userId = user.getId();
                tf_firstName.textProperty().bindBidirectional(userModel.firstName);
                tf_lastName.textProperty().bindBidirectional(userModel.lastName);
                tf_license.textProperty().bindBidirectional(userModel.license);
                dp_birthdate.valueProperty().bindBidirectional(userModel.birthdate);
            });
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void applyEdit() {
        try {
            User user=userModel.toUser();
            user.setId(userId);
            userManager.update(user);
            button_apply.getScene().getWindow().hide();
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void closeWindow() {
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        stage.close();
    }
}
