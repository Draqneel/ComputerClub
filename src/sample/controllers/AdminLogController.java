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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.models.Computer;
import sample.models.Log;
import sample.models.User;
import sample.modules.ComputerModule;
import sample.modules.LogModule;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class AdminLogController {
    private User user;
    private ArrayList<String> logs;
    private ObservableList<String> logObservableList;

    @FXML
    private ListView<String> logListView;


    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы нахо дитесь в списке компьютеров");
        alert.setContentText(this.user.toString());
        alert.showAndWait();
    }


    public void setUser(User user){
        this.user = user;
        this.logs = LogModule.loadData();
        this.logObservableList = FXCollections.observableArrayList(this.logs);
        this.logListView.setItems(this.logObservableList);

    }
    public void back(ActionEvent event) throws IOException{
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/AdminPanelView.fxml"));
        Parent root = loader.load();
        AdminPanelController controller = loader.getController();
        controller.setUser(this.user);
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }


}
