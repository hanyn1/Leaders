package Controllers;

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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EnterNewPassword implements Initializable {

    Connection instance = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField textVerify;

    @FXML
    private PasswordField textNew;

    @FXML
    private Button textRest;

    @FXML
    private Label labelShow;

    @FXML
    private Label labelShow1;

    @FXML
    private CheckBox textShow;

    @FXML
    private CheckBox textShow1;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private String email;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void Reset(ActionEvent event) throws IOException {
        String newPassword = textNew.getText();
        String confirmPassword = textVerify.getText();

        if (!validatePassword(newPassword)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Password", "Password must be at least 8 characters long and contain one digit, one lowercase, and one uppercase letter.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords do not match. Please try again.");
            return;
        }

        Connection instance = MyConfig.getInstance().getConnection();
        PreparedStatement st = null;

        try {
            st = instance.prepareStatement("UPDATE utilisateurs SET motDePasse = ? WHERE email = ?");
            st.setString(1, newPassword);
            st.setString(2, email);
            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated == 0) {
                showAlert(Alert.AlertType.CONFIRMATION, "Password Reset Successful", "Your password has been reset successfully.");

                // Navigate to login scene
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to reset the password. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(st, instance);
        }

    }

    private boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeResources(PreparedStatement st, Connection connection) {
        try {
            if (st != null) st.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

    @FXML
    void ShowPassword(ActionEvent event) {
        if (textShow.isSelected()) {
            labelShow.setVisible(true);
            labelShow.textProperty().bind(Bindings.concat(textVerify.getText()));
            textShow.setText("Hide");
        } else {
            labelShow.setVisible(false);
            textShow.setText("show");
        }

    }

    @FXML
    void ShowPassword1(ActionEvent event) {
        if (textShow1.isSelected()) {
            labelShow1.setVisible(true);
            labelShow1.textProperty().bind(Bindings.concat(textNew.getText()));
            textShow1.setText("Hide");
        } else {
            labelShow1.setVisible(false);
            textShow1.setText("show");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
