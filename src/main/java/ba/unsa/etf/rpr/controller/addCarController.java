package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.CarManager;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;


/**
 * JavaFX controller for adding a car
 *
 * @author Benjamin Kadic
 */
public class addCarController extends MainController implements Initializable {

    private final CarManager carManager=new CarManager();
    private CarModel model =new CarModel();
    public TextField tf_make;
    public TextField tf_model;
    public TextField tf_color;
    public TextField tf_registration;
    public Spinner<Integer> spinner_price;
    public Button button_add;
    public Button button_cancel;
    public BorderPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(30,1000);
        valueFactory.setValue(50);
        spinner_price.setValueFactory(valueFactory);
        tf_make.textProperty().bindBidirectional(model.make);
        tf_model.textProperty().bindBidirectional(model.model);
        tf_color.textProperty().bindBidirectional(model.color);
        tf_registration.textProperty().bindBidirectional(model.registration);
        spinner_price.getValueFactory().valueProperty().bindBidirectional(model.price.asObject());
    }

    /**
     * event handler for adding a new car to DB
     */
    public void addCar() {
        try{
            carManager.add(model.toCar());
            button_add.getScene().getWindow().hide();
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * event handler for closing add a car window
     */
    public void closeWindow() {
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        stage.close();
    }
}
