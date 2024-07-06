package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import Models.Role;
import Services.RoleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class RoleName implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button TextWolcome;

    @FXML
    private ComboBox<String> textRole;

    @FXML
    private Button TextAdd;

    private String[] role = {"Student","Teacher"};
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void ADD(ActionEvent event) {

        String selectedRole = textRole.getSelectionModel().getSelectedItem();

        if (selectedRole == null || selectedRole.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un rôle.");
            alert.show();
            return;
        }

        Role role = new Role(textRole.getSelectionModel().getSelectedItem());
        RoleService roleService = new RoleService();
        try {
            roleService.ajouter(role);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Le role a été ajouté avec succés");
        alert.show();

    }

    @FXML
    void Welcome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/VisitorPage.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textRole.getItems().addAll(role);
        textRole.setOnAction(this::ADD);

    }
}