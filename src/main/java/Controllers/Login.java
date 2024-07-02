package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.MyConfig;

import javax.swing.*;

public class Login implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField PasswordText;
    @FXML
    private CheckBox textShow;


    @FXML
    private Button btnLog;

    @FXML
    private Button btnSign;

    @FXML
    private Label labelShow;

    @FXML
    private TextField userNameText;

    @FXML
    private Button textForget;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void ForgetPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SendEmail.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    void Login(ActionEvent event) throws IOException{
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
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/VisitorPage.fxml")));
                stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Login Error", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void ShowPassword(ActionEvent event) {
        if (textShow.isSelected()){
            labelShow.setVisible(true);
            labelShow.textProperty().bind(Bindings.concat(PasswordText.getText()));
            textShow.setText("Hide");
        }else{
            labelShow.setVisible(false);
            textShow.setText(("show"));
        }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelShow.setVisible(false);
    }



}