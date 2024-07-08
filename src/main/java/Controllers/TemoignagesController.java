package Controllers;
import Services.UserService;
import Models.Temoignage;
import Services.ServiceTemoignage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.SQLException;

import java.time.LocalDateTime;

public class TemoignagesController {
    @FXML
    private TextField utilisateurIdField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField contenuField;
    @FXML
    private TableView<Temoignage> temoignageTable;
    @FXML
    private TableColumn<Temoignage, Integer> idColumn;
    @FXML
    private TableColumn<Temoignage, Integer> utilisateurIdColumn;
    @FXML
    private TableColumn<Temoignage, String> contenuColumn;
    @FXML
    private TableColumn<Temoignage, LocalDateTime> dateCreationColumn;

    private ServiceTemoignage serviceTemoignage;

    public void setServiceTemoignage(ServiceTemoignage serviceTemoignage) {
        this.serviceTemoignage = serviceTemoignage;
        loadTemoignages();
    }

    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        utilisateurIdColumn.setCellValueFactory(cellData -> cellData.getValue().utilisateurIdProperty().asObject());
        contenuColumn.setCellValueFactory(cellData -> cellData.getValue().contenuProperty());
        dateCreationColumn.setCellValueFactory(cellData -> cellData.getValue().dateCreationProperty());
        loadTemoignages();
    }

    private void loadTemoignages() {
        if (serviceTemoignage != null) {
            temoignageTable.setItems(FXCollections.observableArrayList(serviceTemoignage.getAllTemoignages()));
        }
    }
    @FXML
    private void handleAddTemoignage() {
        try {
            int utilisateurId = Integer.parseInt(utilisateurIdField.getText());
            UserService userService = new UserService(); // Instantiate UserService
            if (!userService.utilisateurExists(utilisateurId)) {
                System.err.println("Utilisateur with ID " + utilisateurId + " does not exist.");
                return; // Optionally show a message to the user
            }

            Temoignage temoignage = new Temoignage();
            temoignage.setUtilisateurId(utilisateurId);
            temoignage.setContenu(contenuField.getText());
            serviceTemoignage.addTemoignage(temoignage);
            loadTemoignages();
        } catch (NumberFormatException e) {
            System.err.println("Invalid utilisateur ID format: " + e.getMessage());
            // Optionally show an error message to the user
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage()); // Correctly handle SQLException
            // Optionally show an error message to the user
        }
    }


    @FXML
    private void handleDeleteTemoignage() {
        Temoignage selectedTemoignage = temoignageTable.getSelectionModel().getSelectedItem();
        if (selectedTemoignage != null) {
            serviceTemoignage.deleteTemoignage(selectedTemoignage.getId());
            loadTemoignages();
        }
    }
}
