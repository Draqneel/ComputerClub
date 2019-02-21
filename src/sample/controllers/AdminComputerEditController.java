package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.models.Computer;
import sample.models.User;
import sample.modules.ComputerModule;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by mezkresh on 17.02.2019.
 */
public class AdminComputerEditController {


    private Computer computer;
    private User adminUser;

    @FXML
    private TextField descriptionField;
    @FXML
    private TextField processorField;
    @FXML
    private TextField ramField;
    @FXML
    private TextField romField;
    @FXML
    private TextField videoCartField;
    @FXML
    private Text title;
    @FXML
    private Button editButton;

    public void setComputer(Computer computer, User adminUser) {
        this.computer = computer;
        this.adminUser = adminUser;
        if (computer == null) {
            title.setText("Добавление компьютера");
            editButton.setText("Добавить");
        } else {
            title.setText("Изменение компьютера");
            editButton.setText("Изменить");
            descriptionField.setText(computer.getDescription());
            processorField.setText(computer.getProccessor());
            ramField.setText(String.valueOf(computer.getRam()));
            romField.setText(String.valueOf(computer.getRom()));
            videoCartField.setText(computer.getVideoCart());
        }
    }

    private void dataIncorrect() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Введены не числовые значения в ОЗУ или памяти");
        alert.setContentText("Проверьте введённые Вами данные.");
        alert.showAndWait();
    }

    public void editBtnAction(ActionEvent event) throws IOException {
        try {
            if (computer == null) {
                computer = new Computer(
                        descriptionField.getText().replace(" ", ""),
                        processorField.getText().replace(" ", ""),
                        Integer.parseInt(ramField.getText().replace(" ", "")),
                        Integer.parseInt(romField.getText().replace(" ", "")),
                        videoCartField.getText().replace(" ", "")
                );
            } else {
                computer = new Computer(
                        computer.getId(),
                        descriptionField.getText().replace(" ", ""),
                        processorField.getText().replace(" ", ""),
                        Integer.parseInt(ramField.getText().replace(" ", "")),
                        Integer.parseInt(romField.getText().replace(" ", "")),
                        videoCartField.getText().replace(" ", "")
                );
            }
        }catch (IllegalArgumentException e){
            dataIncorrect();
            return;
        }
        ComputerModule.saveData(computer);
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/AdminPanelView.fxml"));
        Parent root = loader.load();
        AdminPanelController controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
        controller.setUser(this.adminUser);
    }

    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы находитесь в списке компьютеров");
        alert.showAndWait();
    }

}


