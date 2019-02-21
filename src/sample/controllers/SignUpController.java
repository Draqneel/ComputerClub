package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.User;
import sample.modules.UserModule;

import java.io.IOException;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class SignUpController {


    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nameField;

    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы находитесь на стадии Регистриации");
        alert.setContentText("На этой страницей вы можете зарегистрировать свою учетную запись");
        alert.showAndWait();
    }

    private void dataIncorrect() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Не все данные введены");
        alert.setContentText("Проверьте введённые Вами данные.");
        alert.showAndWait();
    }

    public void registrationBtnAction(ActionEvent actionEvent) throws IOException{
        String login = loginField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();

        if(login.equals("") || password.equals("") || name.equals("")) {
            dataIncorrect();
            return;
        }

        User user = new User(name,login,password);
        UserModule.saveData(user);

        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Список компьютеров");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/ComputersListView.fxml"));
        Parent root = loader.load();
        ComputersListViewController controller = loader.getController();
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
