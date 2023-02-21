package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;
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
 * JavaFX controller for users management
 *
 * @author Benjamin Kadic
 */
public class adminUsersController extends MainController{
    private final UserManager userManager=new UserManager();
    public MenuItem mi_close;
    public MenuItem mi_cars;
    public MenuItem mi_users;
    public MenuItem mi_rents;

    public Button button_add;
    public Button button_edit;
    public TextField tf_search;
    public TableView<User> tv_users;
    public TableColumn<User, Integer> tc_id;
    public TableColumn<User, String> tc_license;
    public TableColumn<User, String> tc_firstName;
    public TableColumn<User, String> tc_lastName;
    public TableColumn<User, Date> tc_birthdate;
    public ObservableList<User> userObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        tv_users.setFocusTraversable(false);
        tv_users.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tc_license.setCellValueFactory(new PropertyValueFactory<>("license"));
        tc_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        try {
            userObservableList.addAll(userManager.getAll());
            tv_users.setItems(userObservableList);
            tv_users.refresh();
            FilteredList<User> userFilteredList = new FilteredList<>(userObservableList);
            tf_search.textProperty().addListener((observable, oldValue, newValue)-> userFilteredList.setPredicate(user->{
                if(newValue.isEmpty() || newValue.isBlank()) return true;
                String search = newValue.toLowerCase();
                if(user.getLicense().toLowerCase().contains(search)) return true;
                if (user.getFirstName().toLowerCase().contains(search)) return true;
                if (user.getLastName().toLowerCase().contains(search)) return true;
                return false;
            }));
            SortedList<User> userSortedList=new SortedList<>(userFilteredList);
            userSortedList.comparatorProperty().bind(tv_users.comparatorProperty());
            tv_users.setItems(userSortedList);
            tc_birthdate.setCellFactory(column -> new TableCell<>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            });
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
     * event handler for deleting a user from DB
     */
    public void deleteUser(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2)
        {
            int id =tv_users.getSelectionModel().getSelectedItem().getId();
            User user = tv_users.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete user");
            alert.setHeaderText("You're about to delete "+ user);
            alert.setContentText("Are you sure you want to do this?");

            if(alert.showAndWait().get()==ButtonType.OK){
                try {
                    userManager.delete(id);
                    tf_search.clear();
                    refreshUsers();
                } catch (RentACarException e) {
                    new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
                }

            }
        }
    }

    /**
     * fetch users from DB
     */
    private void refreshUsers(){
        userObservableList.clear();
        initialize();
    }

    /**
     * event handler for opening a form to add a user to DB
     */
    public void openAddForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_user.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Add a car");
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)button_add.getScene().getWindow()).show();
                refreshUsers();
            });
        } catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * event handler for opening a form to edit a user from DB
     */
    public void openEditForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_user.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Edit a user");
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)button_add.getScene().getWindow()).show();
                refreshUsers();
            });
        } catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
