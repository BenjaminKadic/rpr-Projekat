package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.CarManager;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class adminCarsController extends MainController{
    private final CarManager carManager=new CarManager();
    public MenuItem mi_logout;
    public MenuItem mi_close;
    public MenuItem mi_cars;
    public MenuItem mi_users;
    public MenuItem mi_rents;
    public TextField tf_search;
    public TableView<Car> tv_cars;
    public Button button_return;
    public Button button_rent;
    public Button button_rented;
    public Button button_add;
    public Button button_edit;
    public Button button_delete;
    public TableColumn<Car, Integer> tc_id ;
    public TableColumn<Car, String> tc_make;
    public TableColumn<Car, String> tc_model;
    public TableColumn<Car, String> tc_color;
    public TableColumn<Car, String> tc_registration;
    public TableColumn<Car, Integer> tc_price;
    public ObservableList<Car> carObservableList = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        tv_cars.setFocusTraversable(false);
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_make.setCellValueFactory(new PropertyValueFactory<>("make"));
        tc_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        tc_color.setCellValueFactory(new PropertyValueFactory<>("color"));
        tc_registration.setCellValueFactory(new PropertyValueFactory<>("registration"));
        tc_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            carObservableList.addAll(carManager.getAll());
            tv_cars.setItems(carObservableList);
            tv_cars.refresh();
            FilteredList<Car> carFilteredList = new FilteredList<>(carObservableList);
            tf_search.textProperty().addListener((observable, oldValue, newValue)-> carFilteredList.setPredicate(car->{
                if(newValue.isEmpty() || newValue.isBlank()) return true;
                String search = newValue.toLowerCase();
                if(car.getMake().toLowerCase().contains(search)) return true;
                if (car.getModel().toLowerCase().contains(search)) return true;
                return false;
            }));
            SortedList<Car> carSortedList=new SortedList<>(carFilteredList);
            carSortedList.comparatorProperty().bind(tv_cars.comparatorProperty());
            tv_cars.setItems(carSortedList);
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

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
