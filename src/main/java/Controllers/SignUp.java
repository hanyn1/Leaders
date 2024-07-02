package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import Models.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUp {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BConfirm;

    @FXML
    private Button textBack;


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
    void back(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    @FXML
    void initialize() {
    }


}