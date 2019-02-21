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
import sample.exceptions.UserDataIncorrect;
import sample.exceptions.UserNotFoundException;
import sample.models.User;
import sample.modules.UserModule;

import java.io.IOException;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class SignInController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы находитесь на стадии авторизации");
        alert.setContentText("На этой страницей вы можете зайти в свою учетную запись " +
                "или зарегестрировать нового пользователя");
        alert.showAndWait();
    }

    public void registrationBtnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Регистрация");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/SignUpView.fxml"));
        Parent root = loader.load();
        SignUpController controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));

    }

    private void userNotFound() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Пользователь не найден");
        alert.setContentText("Проверьте введённые Вами данные.");
        alert.showAndWait();
    }

    private void passwordIncorrect() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Пароль не совпадает");
        alert.setContentText("Проверьте введённые Вами данные.");
        alert.showAndWait();
    }

    public void btnSignIn(ActionEvent actionEvent) throws IOException {
        try {
            User user = UserModule.getUser(loginField.getText(), passwordField.getText());
            if(user.getId()==1){
                Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
                stage.setTitle("Личный кабинет");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/AdminPanelView.fxml"));
                Parent root = loader.load();
                AdminPanelController controller = loader.getController();
                stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
                controller.setUser(user);

            }
            Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/ComputersListView.fxml"));
            Parent root = loader.load();
            ComputersListViewController controller = loader.getController();
            controller.setUser(user);
            stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
        } catch (UserNotFoundException e) {
            userNotFound();
        }catch (UserDataIncorrect e){
            passwordIncorrect();
        }

    }

}
