package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Models.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BConfirm;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void Confirm(ActionEvent event) throws SQLException {
        User user = new User(nameTextField.getText(),emailTextField.getText(),passwordTextField.getText());
        UserService userService = new UserService();
        {
            try {
                userService.ajouter(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("L'utilisateur a été ajouté avec succés");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoleName.fxml"));
            try {
                Parent root = loader.load();
                RoleName userInfo = loader.getController();


                nameTextField.getScene().setRoot(root);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }



    }

    @FXML
    void initialize() {
    }

}