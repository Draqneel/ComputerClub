package sample.controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
import sample.models.User;
import sample.modules.ComputerModule;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class ComputersListViewController {
    private User user;
    private ArrayList<Computer> computers;
    private ObservableList<String> computerObservableList;
    private ArrayList<String> computersList;
    private Computer currentComputer;
    @FXML
    private ListView<String> computerListView;

    @FXML
    private Label label;



    public void setUser(User user){
        this.user = user;
        this.computers = ComputerModule.loadData();
        this.computersList = ComputerModule.loadDescriptions(this.computers);
        this.computerObservableList = FXCollections.observableArrayList(this.computersList);
        this.computerListView.setItems(this.computerObservableList);
        this.computerListView.setOnMouseClicked(event -> {
            this.currentComputer = this.computers.get(this.computerListView.getSelectionModel().getSelectedIndex());
            this.label.setText(currentComputer.toString(true));
        });
    }

    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы находитесь в списке компьютеров");
        alert.setContentText(this.user.toString());
        alert.showAndWait();
    }

    public void chooseComputer(ActionEvent actionEvent) throws IOException{
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/ComputerView.fxml"));
        Parent root = loader.load();
        ComputerController controller = loader.getController();
        controller.setData(this.user,this.currentComputer);
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
