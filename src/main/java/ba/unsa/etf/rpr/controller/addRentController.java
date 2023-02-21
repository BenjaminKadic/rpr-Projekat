package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.CarManager;
import ba.unsa.etf.rpr.business.RentManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Rent;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * JavaFX controller for adding a rent
 *
 * @author Benjamin Kadic
 */
public class addRentController extends MainController implements Initializable {

    private final CarManager carManager=new CarManager();
    private final UserManager userManager= new UserManager();
    private final RentManager rentManager= new RentManager();

    public BorderPane pane;
    public ComboBox<User> cb_user;
    public ComboBox<Car> cb_car;
    public DatePicker dp_start;
    public DatePicker dp_end;
    public Button button_add;
    public Button button_cancel;
    final ObservableList<Car> carOptions = FXCollections.observableArrayList();
    final ObservableList<User> userOptions = FXCollections.observableArrayList();
    Car car=new Car();
    User user=new User();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        dp_start.setConverter(
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
        dp_end.setConverter(
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
            carOptions.addAll(carManager.getAll());
            carOptions.sort(null);
            userOptions.addAll(userManager.getAll());
            userOptions.sort(null);
            cb_car.setItems(carOptions);
            cb_user.setItems(userOptions);
            cb_car.setOnAction(e -> car=cb_car.getSelectionModel().getSelectedItem());
            cb_user.setOnAction(e -> user=cb_user.getSelectionModel().getSelectedItem());
            dp_start.setValue(LocalDate.now());
            dp_end.setValue(LocalDate.now());
        } catch (RentACarException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * event handler for adding a new Rent to DB
     */
    public void addRent() {
        Rent rent = new Rent(car,user,Date.valueOf(dp_start.getValue()), Date.valueOf(dp_end.getValue()));
        if(dp_end.getValue().isAfter(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Rent a car");
            alert.setHeaderText("You're about to rent a car!");
            alert.setContentText("Price: "+ rent.getRentPrice());

            if(alert.showAndWait().get()==ButtonType.OK){
                try {
                    rentManager.add(rent);
                    button_add.getScene().getWindow().hide();
                } catch (RentACarException e) {
                    new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
                }
            }
        }else try{
            rentManager.add(rent);
            button_add.getScene().getWindow().hide();
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * event handler for closing add a rent window
     */
    public void closeWindow() {
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        stage.close();
    }


}
