package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import com.mysql.cj.util.StringUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Main controller that contains models and functions
 * that are needed in multiple Controllers
 *
 * @author Benjamin Kadic
 */

public class MainController {

    /**
     * opens a new window
     * @param title title of new window
     * @param file fxml file that contains new window
     */
    protected void openWindow(String title, String file){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.getIcons().add(new Image("/img/car-rent.png"));
            stage.setTitle(title);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * switches scenes in same stage
     * @param title to be displayed when switch occurs
     * @param file fxml file that contains a new scene
     * @param stage on which scene is to be switched
     */

    protected void switchScene(String title, String file, Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    /**
     * Helper Model class that supports 2 way data binding with form for Car management
     */
    protected static class CarModel {
        public SimpleStringProperty make = new SimpleStringProperty("");
        public SimpleStringProperty model = new SimpleStringProperty("");
        public SimpleStringProperty color = new SimpleStringProperty("");
        public SimpleStringProperty registration = new SimpleStringProperty("");
        public SimpleIntegerProperty price = new SimpleIntegerProperty();
        public Car toCar() throws RentACarException {
            if(StringUtils.isNullOrEmpty(this.model.getValue()) || StringUtils.isNullOrEmpty(this.make.getValue()) || StringUtils.isNullOrEmpty(this.color.getValue()) || StringUtils.isNullOrEmpty(this.registration.getValue()) || price==null)
                throw new RentACarException("Please fill in all fields");
            Car car = new Car();
            car.setMake(this.make.getValue());
            car.setModel(this.model.getValue());
            car.setColor(this.color.getValue());
            car.setRegistration(this.registration.getValue());
            car.setPrice(this.price.getValue());

            return car;
        }
    }

    /**
     * Helper Model class that supports 2 way data binding with form for User management
     */
    protected static class UserModel {
        public SimpleStringProperty license = new SimpleStringProperty("");
        public SimpleStringProperty firstName = new SimpleStringProperty("");
        public SimpleStringProperty lastName = new SimpleStringProperty("");
        public SimpleObjectProperty<LocalDate> birthdate = new SimpleObjectProperty<>();

        public User toUser() throws RentACarException {
            if(StringUtils.isNullOrEmpty(this.license.getValue()) || StringUtils.isNullOrEmpty(this.firstName.getValue()) || StringUtils.isNullOrEmpty(this.lastName.getValue()) || birthdate==null)
                throw new RentACarException("Please fill in all fields");
            User user = new User();
            user.setLicense(this.license.getValue());
            user.setFirstName(this.firstName.getValue());
            user.setLastName(this.lastName.getValue());
            user.setBirthdate(Date.valueOf(this.birthdate.getValue()));

            return user;
        }
    }
}
