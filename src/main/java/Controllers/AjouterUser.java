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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AjouterUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField MotDePasseTextField;

    @FXML
    private TextField NomTextField;
    @FXML
    private TableView<?> tableView;

    @FXML
    void AjouterUser(ActionEvent event) {
        User user = new User(NomTextField.getText(),EmailTextField.getText(),MotDePasseTextField.getText());
        UserService userService = new UserService();
        try {
            userService.ajouter(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("L'utilisateur a été ajouté avec succés");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserInfo.fxml"));
            try {
                Parent root = loader.load();
                UserInfo userInfo = loader.getController();
                userInfo.setEmail(EmailTextField.getText());
                userInfo.setNom(NomTextField.getText());
                userInfo.setID(IDTextField.getText());
                userInfo.setMotDePasse(MotDePasseTextField.getText());

                NomTextField.getScene().setRoot(root);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }


    }

    @FXML
    void initialize() {

    }

}