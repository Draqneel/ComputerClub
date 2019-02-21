package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.models.Computer;
import sample.models.User;
import sample.modules.ComputerModule;
import sample.modules.UserModule;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class AdminPanelController {

    private User user;

    private ArrayList<Computer> computers;
    private ArrayList<User> users;
    private ArrayList<String> computersList;
    private ObservableList<String> computerObservableList;
    private ArrayList<String> usersList;
    private ObservableList<String> userObservableList;

    private Computer currentComputer;
    private User currentUser;

    @FXML
    private ListView<String> computerListView;

    @FXML
    private ListView<String> userListView;


    public void setUser(User user) {
        this.user = user;
        this.computers = ComputerModule.loadData();
        this.users = UserModule.loadData();
        this.computersList = ComputerModule.loadDescriptions(this.computers);
        this.computerObservableList = FXCollections.observableArrayList(this.computersList);
        this.computerListView.setItems(this.computerObservableList);
        this.computerListView.setOnMouseClicked(event -> {
            this.currentComputer = this.computers.get(this.computerListView.getSelectionModel().getSelectedIndex());
        });

        this.usersList = UserModule.loadDescriptions(this.users);
        this.userObservableList = FXCollections.observableArrayList(this.usersList);
        this.userListView.setItems(this.userObservableList);
        this.userListView.setOnMouseClicked(event -> {
            this.currentUser = this.users.get(this.userListView.getSelectionModel().getSelectedIndex());
        });
    }


    public void addUser(ActionEvent event) throws IOException {
        editUser(event, null);
    }

    public void editUser(ActionEvent event) throws IOException {
        editUser(event, currentUser);
    }

    public void deleteUser(ActionEvent event) {
        UserModule.delete(currentUser.getId());
        this.users = UserModule.loadData();
        this.usersList = UserModule.loadDescriptions(users);
        userObservableList = FXCollections.observableArrayList(this.usersList);
        this.userListView.setItems(this.userObservableList);
    }

    private void editUser(ActionEvent event, User user) throws IOException {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Список компьютеров");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/AdminUserEditView.fxml"));
        Parent root = loader.load();
        AdminUserEditController controller = loader.getController();
        controller.setUser(user, this.user);
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    public void addComputer(ActionEvent event) throws IOException {
        editComputer(event, null);
    }

    public void editComputer(ActionEvent event) throws IOException {
        editComputer(event, currentComputer);
    }

    public void deleteComputer(ActionEvent event) {
        ComputerModule.delete(currentComputer.getId());
        this.computers = ComputerModule.loadData();
        this.computersList = ComputerModule.loadDescriptions(computers);
        this.computerObservableList = FXCollections.observableArrayList(this.computersList);
        this.computerListView.setItems(this.computerObservableList);
    }

    private void editComputer(ActionEvent event, Computer computer) throws IOException {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/AdminComputerEditView.fxml"));
        Parent root = loader.load();
        AdminComputerEditController controller = loader.getController();
        controller.setComputer(computer,user);
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы находитесь в списке компьютеров");
        alert.setContentText(this.user.toString());
        alert.showAndWait();
    }

    public void seeLog(ActionEvent event) throws IOException{
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Логи");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/AdmnLogView.fxml"));
        Parent root = loader.load();
        AdminLogController controller = loader.getController();
        controller.setUser(user);
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }
    public void back(ActionEvent event) throws IOException{
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/SignInView.fxml"));
        Parent root = loader.load();
        SignInController controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

}
