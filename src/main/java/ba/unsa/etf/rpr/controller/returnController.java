package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.RentManager;
import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Date;

/**
 * JavaFX controller for car returning
 *
 * @author Benjamin Kadic
 */
public class returnController extends MainController {
    final private RentManager rentManager = new RentManager();
    public TableView<Rent> tv_cars;
    public TableColumn<Rent, Integer> tc_id;
    public TableColumn<Rent, String> tc_make;
    public TableColumn<Rent, String> tc_model;
    public TableColumn<Rent, String> tc_license;



    @FXML
    public void initialize(){
        tv_cars.setFocusTraversable(false);
        tv_cars.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_make.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCar().getMake()));
        tc_model.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCar().getModel()));
        tc_license.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUser().getLicense()));
        refreshRented();
    }

    /**
     * event handler for returning a car that is currently rented
     */
    public void returnCarOnClick(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount() == 2)
        {
            Rent rent=new Rent();
            rent.setId(tv_cars.getSelectionModel().getSelectedItem().getId());
            rent.setCar(tv_cars.getSelectionModel().getSelectedItem().getCar());
            rent.setUser(tv_cars.getSelectionModel().getSelectedItem().getUser());
            rent.setStartDate(tv_cars.getSelectionModel().getSelectedItem().getStartDate());
            rent.setEndDate(tv_cars.getSelectionModel().getSelectedItem().getEndDate());
            int previousRent=rent.getRentPrice();
            rent.setEndDate(new Date(System.currentTimeMillis()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Return car");
            alert.setHeaderText("You're about to return a car!");
            alert.setContentText("You should return " + (previousRent-rent.getRentPrice()) + " to customer!");

            if(alert.showAndWait().get()==ButtonType.OK){
                try {
                    rentManager.update(rent);
                    refreshRented();
                } catch (RentACarException e) {
                    new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
                }

            }
        }
    }

    /**
     * fetch rented cars from DB
     */
    private void refreshRented(){
        try {
            tv_cars.setItems(FXCollections.observableList(rentManager.getRentedCars()));
            tv_cars.refresh();
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
