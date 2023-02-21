package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.CarManager;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX controller for car editing
 *
 * @author Benjamin Kadic
 */
public class editCarController extends MainController implements Initializable {

    private final CarManager carManager=new CarManager();
    private CarModel carModel=new CarModel();
    public BorderPane pane;
    public Text text_error;
    public ChoiceBox<Car> cb_carList;
    public TextField tf_make;
    public TextField tf_model;
    public TextField tf_color;
    public TextField tf_registration;
    public Spinner<Integer> spinner_price;
    public Button button_apply;
    public Button button_cancel;

    final ObservableList<Car> options = FXCollections.observableArrayList();
    int carId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(30,1000);
        try{
            options.addAll(carManager.getAll());
            options.sort(null);
            cb_carList.setItems(options);
            cb_carList.setOnAction(e -> {
                Car car=cb_carList.getSelectionModel().getSelectedItem();
                tf_make.setText(car.getMake());
                tf_model.setText(car.getModel());
                tf_color.setText(car.getColor());
                tf_registration.setText(car.getRegistration());
                valueFactory.setValue(car.getPrice());
                spinner_price.setValueFactory(valueFactory);
                carId=car.getId();
                tf_make.textProperty().bindBidirectional(carModel.make);
                tf_model.textProperty().bindBidirectional(carModel.model);
                tf_color.textProperty().bindBidirectional(carModel.color);
                tf_registration.textProperty().bindBidirectional(carModel.registration);
                spinner_price.getValueFactory().valueProperty().bindBidirectional(carModel.price.asObject());
            });
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * event handler for editing a car in DB
     */
    public void applyEdit() {
        try {
            Car car=carModel.toCar();
            car.setId(carId);
            carManager.update(car);
            button_apply.getScene().getWindow().hide();
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * event handler for closing current window
     */
    public void closeWindow() {
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        stage.close();
    }
}
