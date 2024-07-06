package Controllers;

import Models.User;
import Services.UserService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private Label labelShow;


    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;


    @FXML
    private CheckBox textShow;


    @FXML
    private PasswordField passwordTextField;

    @FXML
    void Confirm(ActionEvent event) throws SQLException {

        String name = nameTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String password = passwordTextField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.");
            alert.show();
            return;
        }

        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Format d'email incorrect.");
            alert.show();
            return;
        }

        if (password.length() < 6) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Le mot de passe doit comporter au moins 6 caractères.");
            alert.show();
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());


        User user = new User(name,email,hashedPassword);
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
    void ShowPassword(ActionEvent event) {
        if (textShow.isSelected()) {
            labelShow.setVisible(true);
            labelShow.textProperty().bind(Bindings.concat(passwordTextField.getText()));
            textShow.setText("Hide");
        } else {
            labelShow.setVisible(false);
            textShow.setText("show");
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