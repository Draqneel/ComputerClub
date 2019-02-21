package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.models.User;
import sample.modules.UserModule;

import java.io.IOException;

/**
 * Created by mezkresh on 17.02.2019.
 */
public class AdminUserEditController {
    private User user;
    private User adminUser;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private Text title;
    @FXML
    private Button editButton;

    public void setUser(User user,User adminUser){
        this.user = user;
        this.adminUser = adminUser;
        if(user==null){
            title.setText("Добавление пользователя");
            editButton.setText("Добавить");
        }else{
            nameField.setText(this.user.getName());
            loginField.setText(this.user.getLogin());
            passwordField.setText(this.user.getPassword());
            title.setText("Изменение пользователя");
            editButton.setText("Изменить");
        }
    }

    public void editBtnAction(ActionEvent event) throws IOException{
        User user;
        if(this.user!=null){
             user = new User(
                    this.user.getId(),
                    nameField.getText(),
                    loginField.getText(),
                    passwordField.getText()
            );
        }
        else {
            user = new User(
                    nameField.getText(),
                    loginField.getText(),
                    passwordField.getText()
            );
        }
        UserModule.saveData(user);
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/AdminPanelView.fxml"));
        Parent root = loader.load();
        AdminPanelController controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
        controller.setUser(user);
    }
    public void helpBtnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Вы находитесь в списке компьютеров");
        alert.setContentText(this.user.toString());
        alert.showAndWait();
    }

}
