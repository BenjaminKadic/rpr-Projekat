package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.RentManager;
import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for rents management
 *
 * @author Benjamin Kadic
 */
public class adminRentsController extends MainController{
    private final RentManager rentManager = new RentManager();
    public MenuItem mi_close;
    public MenuItem mi_cars;
    public MenuItem mi_users;
    public MenuItem mi_rents;
    public TextField tf_search;

    public TableView<Rent> tv_rents;
    public TableColumn<Rent, Integer> tc_id;
    public TableColumn<Rent, String> tc_make;
    public TableColumn<Rent, String> tc_model;
    public TableColumn<Rent, String> tc_license;
    public TableColumn<Rent, String> tc_lastName;
    public TableColumn<Rent, Date> tc_start;
    public TableColumn<Rent, Date> tc_end;
    public Button button_add;
    public Button button_current;
    public ObservableList<Rent> rentsObservableList = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        tv_rents.setFocusTraversable(false);
        tv_rents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_make.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCar().getMake()));
        tc_model.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCar().getModel()));
        tc_license.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUser().getLicense()));
        tc_lastName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUser().getLastName()));
        tc_start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tc_start.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    this.setText(new SimpleDateFormat("dd.MM.yyyy.").format(item));
                }
            }
        });
        tc_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tc_end.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    this.setText(new SimpleDateFormat("dd.MM.yyyy.").format(item));
                }
            }
        });
        try {
            rentsObservableList.addAll(rentManager.getAll());
            tv_rents.setItems(rentsObservableList);
            tv_rents.refresh();
            FilteredList<Rent> rentsFilteredList = new FilteredList<>(rentsObservableList);
            tf_search.textProperty().addListener((observable, oldValue, newValue)-> rentsFilteredList.setPredicate(rent->{
                if(newValue.isEmpty() || newValue.isBlank()) return true;
                String search = newValue.toLowerCase();
                if(rent.getUser().getLicense().toLowerCase().contains(search)) return true;
                if (rent.getUser().getLastName().toLowerCase().contains(search)) return true;
                if(rent.getCar().getMake().toLowerCase().contains(search)) return true;
                if (rent.getCar().getModel().toLowerCase().contains(search)) return true;
                return false;
            }));
            SortedList<Rent> rentsSortedList=new SortedList<>(rentsFilteredList);
            rentsSortedList.comparatorProperty().bind(tv_rents.comparatorProperty());
            tv_rents.setItems(rentsSortedList);
        } catch (RentACarException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
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
        switchScene("Users", "/fxml/admin_users.fxml", stage);
    }

    /**
     * event handler for opening rents admin window
     */
    public void goToRents() {
        Stage stage = (Stage) button_add.getScene().getWindow();
        switchScene("Rents", "/fxml/admin_rents.fxml", stage);
    }

    /**
     * event handler for opening current rents window
     */
    public void openCurrent() {
        openWindow("Current Rents","/fxml/return_car.fxml");
    }

    /**
     * event handler for deleting a rent
     */
    public void deleteRent(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && tv_rents.getSelectionModel().getSelectedItem()!=null)
        {
            int id =tv_rents.getSelectionModel().getSelectedItem().getId();
            Rent rent = tv_rents.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete rent");
            alert.setHeaderText("You're about to delete "+ rent);
            alert.setContentText("Are you sure you want to do this?");

            if(alert.showAndWait().get()==ButtonType.OK){
                try {
                    rentManager.delete(id);
                    tf_search.clear();
                    refreshRents();
                } catch (RentACarException e) {
                    new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
                }
            }
        }
    }

    /**
     * fetch rented cars from DB
     */
    private void refreshRents(){
        rentsObservableList.clear();
        initialize();
    }

    /**
     * event handler for opening a form for adding a new rent
     */
    public void openAddForm () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_rent.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Rent a car");
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)button_add.getScene().getWindow()).show();
                refreshRents();
            });
        } catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
