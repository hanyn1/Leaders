package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.MyConfig;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PasswordText;

    @FXML
    private Button btnLog;

    @FXML
    private Button btnSign;

    @FXML
    private TextField userNameText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void Login(ActionEvent event) {
        Connection instance = MyConfig.getInstance().getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = instance.prepareStatement("SELECT * FROM utilisateurs WHERE email = ? AND motDePasse = ?");
            st.setString(1,userNameText.getText());
            st.setString(2,PasswordText.getText());
            rs = st.executeQuery();
            if (rs.next()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login SuccessFully", ButtonType.OK);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Login Error", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void SignUp(ActionEvent event) {

    }

    @FXML
    void initialize() {
    }
    @FXML
    public void goToSignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SignUp.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}