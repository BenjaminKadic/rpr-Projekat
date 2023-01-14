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



public class returnController extends MainController {
    final private RentManager rentManager = new RentManager();
    public TableView<Rent> tv_cars;
    public TableColumn<Rent, Integer> tc_id;
    public TableColumn<Rent, String> tc_make;
    public TableColumn<Rent, String> tc_model;
    public TableColumn<Rent, String> tc_username;



    @FXML
    public void initialize(){
        tv_cars.setFocusTraversable(false);
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_make.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCar().getMake()));
        tc_model.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCar().getModel()));
        tc_username.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUser().getUsername()));
        refreshRented();
    }

    public void returnCarOnClick(MouseEvent event)
    {
        if (event.getClickCount() == 2)
        {
            Rent rent=new Rent();
            rent.setId(tv_cars.getSelectionModel().getSelectedItem().getId());
            rent.setCar(tv_cars.getSelectionModel().getSelectedItem().getCar());
            rent.setUser(tv_cars.getSelectionModel().getSelectedItem().getUser());
            rent.setStartDate(tv_cars.getSelectionModel().getSelectedItem().getStartDate());
            rent.setEndDate(new Date(System.currentTimeMillis()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Return car");
            alert.setHeaderText("You're about to return a car!");
            alert.setContentText("Price: "+ rent.getRentPrice());

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
