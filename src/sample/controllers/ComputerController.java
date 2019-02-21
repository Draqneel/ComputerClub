package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.models.Computer;
import sample.models.Log;
import sample.models.User;
import sample.modules.LogModule;

import java.io.IOException;

/**
 * Created by mezkresh on 17.02.2019.
 */
public class ComputerController {

    private User user;
    private Computer computer;
    private Log log;

    public void setData(User user,Computer computer){
        this.user = user;
        this.computer = computer;
        this.log = new Log(user.getId(),computer.getId());
        log.startSession();
    }


    public void closeSession(ActionEvent actionEvent) throws IOException{
        log.closeSession();
        LogModule.saveData(log);
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/ComputersListView.fxml"));
        Parent root = loader.load();
        ComputersListViewController controller = loader.getController();
        controller.setUser(user);
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }
    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы находитесь в списке компьютеров");
        alert.setContentText(this.user.toString());
        alert.showAndWait();
    }

}
