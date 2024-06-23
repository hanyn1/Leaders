package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Models.Cours;
import Services.ServiceCours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AjouterCours {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBTN;

    @FXML
    private TableColumn<Cours, String> colTitre;

    @FXML
    private TableColumn<Cours, String> coldesc;

    @FXML
    private TableColumn<Cours, String> colVideo;

    @FXML
    private TextField descTF;

    @FXML
    private TextField titreF;

    @FXML
    private TextField vidTF;

    @FXML
    private TableView<Cours> tableView;

    private ObservableList<Cours> observableList;
    private ServiceCours sc;

    @FXML
    void addCours(ActionEvent event) throws SQLException {
        String titre = titreF.getText().trim();
        String description = descTF.getText().trim();
        String video = vidTF.getText().trim();

        // Validate input fields
        if (titre.isEmpty() || description.isEmpty() || video.isEmpty()) {
            showAlert("Validation Error", "All fields must be filled out.");
            return;
        }

        Cours cours = new Cours(titre, description, video);
        sc.add(cours);

        // Clear the text fields after adding the course
        titreF.clear();
        descTF.clear();
        vidTF.clear();

        // Update the ObservableList and refresh the TableView
        observableList.add(cours);
        tableView.refresh();
    }

    @FXML
    void initialize() {
        sc = new ServiceCours();
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVideo.setCellValueFactory(new PropertyValueFactory<>("video"));

        // Initialize the ObservableList and load data into it
        updateTableView();
    }

    private void updateTableView() {
        List<Cours> cs = sc.getAll();
        observableList = FXCollections.observableList(cs);
        tableView.setItems(observableList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
