package Controllers;

import Models.Formation;
import Services.ServiceFormation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FormationController {
    @FXML
    private TextField titreField;
    @FXML
    private Button textAdd;

    @FXML
    private Button textDelete;

    @FXML
    private TextField descriptionField;
    @FXML
    private TableView<Formation> formationTable;
    @FXML
    private TableColumn<Formation, Integer> idColumn;
    @FXML
    private TableColumn<Formation, String> titreColumn;
    @FXML
    private TableColumn<Formation, String> descriptionColumn;

    private ServiceFormation serviceFormation;
    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterCoursAdmin.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }@FXML
    public void goToUsers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UserCRUD.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ManageArticle.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goToFormation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FormationController.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomeToEv.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuizz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/QUIZZview.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AdminDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToRole(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/RoleCRUD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setServiceFormation(ServiceFormation serviceFormation) {
        this.serviceFormation = serviceFormation;
        loadFormations();
    }

    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        loadFormations();
    }

    private void loadFormations() {
        if (serviceFormation != null) {
            formationTable.setItems(FXCollections.observableArrayList(serviceFormation.getAllFormations()));
        }
    }

    @FXML
    private void handleAddFormation() {
        Formation formation = new Formation();
        formation.setTitre(titreField.getText());
        formation.setDescription(descriptionField.getText());
        serviceFormation.addFormation(formation);
        loadFormations();
    }

    @FXML
    private void handleDeleteFormation() {
        Formation selectedFormation = formationTable.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            serviceFormation.deleteFormation(selectedFormation.getId());
            loadFormations();
        }
    }
}
